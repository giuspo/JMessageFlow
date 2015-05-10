package org.nocompany.jmsgflowlib;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by giulio on 10/05/15.
 */
public class BrokerSys extends UntypedActor
{
	private final Map<String, List<ActorRef>> _rgtEventSubscriberMap = new HashMap<String, List<ActorRef>>();

	@Override
	public void onReceive(Object objMsg) throws Exception
	{
		if(objMsg instanceof SubscriberMsg)
		{
			SubscriberMsg tSubMsg = (SubscriberMsg)objMsg;
			boolean bIskeyFind = _rgtEventSubscriberMap.containsKey(tSubMsg.getEventName());

			if(!bIskeyFind)
			{
				// SG: Event doesn't exist
				ArrayList<ActorRef> rgtActorList = new ArrayList<ActorRef>();

				// SG: Create new Actor Subscriber List for this Event
				rgtActorList.add(getSender());
				// SG: Add new Event and Actor to Dictionary
				_rgtEventSubscriberMap.put(tSubMsg.getEventName(), rgtActorList);
				// SG: Watch this Actor
				getContext().watch(getSender());
			}
			else
			{
				// SG: Event already exists
				List<ActorRef> rgtActorList = _rgtEventSubscriberMap.get(tSubMsg.getEventName());
				boolean bIsActorFind = rgtActorList.contains(getSender());

				if(!bIsActorFind)
				{
					// SG: Actor doesn't exist into Subscriber List
					rgtActorList.add(getSender());
					// SG: Watch this Actor
					getContext().watch(getSender());
				}
			}
		}
		else if(objMsg instanceof UnSubscribeMsg)
		{
			UnSubscribeMsg tUnSubMsg = (UnSubscribeMsg)objMsg;
			boolean bIskeyFind = _rgtEventSubscriberMap.containsKey(tUnSubMsg.getEventName());

			if(bIskeyFind)
			{
				// SG: Event already exists
				List<ActorRef> rgtActorList = _rgtEventSubscriberMap.get(tUnSubMsg.getEventName());
				boolean bIsActorFind = rgtActorList.remove(getSender());

				if(bIsActorFind)
				{
					getContext().unwatch(getSender());

					if(0 >= rgtActorList.size())
					{
						_rgtEventSubscriberMap.remove(tUnSubMsg.getEventName());
					}
				}
			}
		}
	}
}
