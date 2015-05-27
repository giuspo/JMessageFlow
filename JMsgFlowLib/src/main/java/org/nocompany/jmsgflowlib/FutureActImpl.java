package org.nocompany.jmsgflowlib;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 * Created by giulio on 24/05/15.
 */
public final class FutureActImpl extends UntypedActor
{
	private ActorRef _tSenderAct;

	private FutureAct _tFutureAct;

	@Override
	public void onReceive(Object objMsg) throws
		Exception
	{
		if(objMsg instanceof InitFutureActMsg)
		{
			InitFutureActMsg tInitMsg = (InitFutureActMsg)objMsg;

			_tFutureAct = tInitMsg.getFutureAct();
			_tSenderAct = getSender();
			_tFutureAct.getMsgFlowSys().getBrokerSys().tell(new SubscriberMsg(tInitMsg.getEvn()),
				getSelf());
		}
		else if(objMsg instanceof EventMsg)
		{
			_tSenderAct.tell(objMsg, getSelf());
		}
	}
}
