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

	private String _strEvn;

	@Override
	public void preStart() throws Exception
	{
		super.preStart();
	}

	@Override
	public void postStop() throws Exception
	{
		super.postStop();
	}

	@Override
	public void onReceive(Object objMsg) throws
		Exception
	{
		if(objMsg instanceof InitFutureActMsg)
		{
			InitFutureActMsg tInitMsg = (InitFutureActMsg)objMsg;

			_tFutureAct = tInitMsg.getFutureAct();
			_strEvn = tInitMsg.getEvn();
			_tSenderAct = getSender();
			_tFutureAct.getMsgFlowSys().getBrokerSys().tell(objMsg, getSelf());
		}
		else if(objMsg instanceof EventMsg)
		{
			_tSenderAct.forward(objMsg, getContext());
		}
	}
}
