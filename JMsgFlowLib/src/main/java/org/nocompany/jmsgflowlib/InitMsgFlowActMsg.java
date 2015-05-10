package org.nocompany.jmsgflowlib;

import akka.actor.ActorRef;

/**
 * Created by giulio on 10/05/15.
 */
public class InitMsgFlowActMsg
{
	private final IMsgFlowAct _tMsgFlowAct;
	private final ActorRef _tBrokerSys;

	public ActorRef getBrokerSys()
	{
		return _tBrokerSys;
	}

	public IMsgFlowAct getMsgFlowAct()
	{
		return _tMsgFlowAct;
	}

	public InitMsgFlowActMsg(IMsgFlowAct tMsgFlowAct, ActorRef tBroker)
	{
		_tMsgFlowAct = tMsgFlowAct;
		_tBrokerSys = tBroker;
	}
}
