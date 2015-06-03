package org.nocompany.jmsgflowlib;

import akka.actor.ActorRef;
import akka.actor.DeadLetter;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Created by giulio on 10/05/15.
 */
class BrokerSys extends UntypedActor
{
	private final Map<String, List<ActorRef>> _rgtEventSubscriberMap = new HashMap<>();

	@Override
	public void onReceive(Object objMsg) throws
		Exception
	{
		if(objMsg instanceof SubscriberMsg)
		{
			SubscriberMsg tSubMsg = (SubscriberMsg)objMsg;
			boolean bIsKeyFind = _rgtEventSubscriberMap.containsKey(
				tSubMsg.getEventName());

			if(!bIsKeyFind)
			{
				// SG: Event doesn't exist
				ArrayList<ActorRef> rgtActorList = new ArrayList<>();

				// SG: Create new Actor Subscribe List for this Event
				rgtActorList.add(sender());
				// SG: Add new Event and Actor to Dictionary
				_rgtEventSubscriberMap.put(tSubMsg.getEventName(),
					rgtActorList);
				// SG: Watch this Actor
				context().watch(sender());
			}
			else
			{
				// SG: Event already exists
				List<ActorRef> rgtActorList = _rgtEventSubscriberMap.get(
					tSubMsg.getEventName());
				boolean bIsActorFind = rgtActorList.contains(sender());

				if(!bIsActorFind)
				{
					// SG: Actor doesn't exist into Subscribe List
					rgtActorList.add(sender());
					// SG: Watch this Actor
					context().watch(sender());
				}
			}
		}
		else if(objMsg instanceof UnSubscribeMsg)
		{
			UnSubscribeMsg tUnSubMsg = (UnSubscribeMsg)objMsg;
			boolean bIsKeyFind = _rgtEventSubscriberMap.containsKey(
				tUnSubMsg.getEventName());

			if(bIsKeyFind)
			{
				// SG: Event already exists
				List<ActorRef> rgtActorList = _rgtEventSubscriberMap.get(
					tUnSubMsg.getEventName());
				boolean bIsActorFind = rgtActorList.remove(sender());

				if(bIsActorFind)
				{
					context().unwatch(getSender());

					if(0 >= rgtActorList.size())
					{
						_rgtEventSubscriberMap.remove(tUnSubMsg.getEventName());
					}
				}
			}
		}
		else if(objMsg instanceof DeadLetter)
		{
			DeadLetter tDeadMsg = (DeadLetter)objMsg;

			for(List<ActorRef> rgtActor : _rgtEventSubscriberMap.values())
			{
				rgtActor.removeIf(Predicate.isEqual(tDeadMsg.sender()));
			}
		}
		else if(objMsg instanceof EventMsg)
		{
			EventMsg tEventMsg = (EventMsg)objMsg;
			boolean bIsKeyFind = _rgtEventSubscriberMap.containsKey(
				tEventMsg.getEvent());

			if(bIsKeyFind)
			{
				List<ActorRef> rgtActor = _rgtEventSubscriberMap.get(
					tEventMsg.getEvent());

				rgtActor.forEach(tActorRef -> tActorRef.forward(tEventMsg,
					context()));
			}
		}

	}
}
