package org.nocompany.jmsgflowlib;

import akka.actor.ActorRef;

/**
 * Created by giulio on 10/05/15.
 */
public class InitActMsg
{
	private final AMsgFlowAct _tMsgFlowAct;
	private final ActorRef _tBrokerSys;

	public ActorRef getBrokerSys()
	{
		return _tBrokerSys;
	}

	public AMsgFlowAct getMsgFlowAct()
	{
		return _tMsgFlowAct;
	}

	public InitActMsg(AMsgFlowAct tMsgFlowAct, ActorRef tBroker)
	{
		_tMsgFlowAct = tMsgFlowAct;
		_tBrokerSys = tBroker;
	}
}
