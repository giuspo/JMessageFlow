package org.nocompany.jmsgflowlib;

import akka.actor.ActorRef;

/**
 * Created by giulio on 10/05/15.
 */
public class InitMsgFlowActMsg
{
	private final AMsgFlowAct _tMsgFlowAct;

	public AMsgFlowAct getMsgFlowAct()
	{
		return _tMsgFlowAct;
	}

	public InitMsgFlowActMsg(AMsgFlowAct tMsgFlowAct)
	{
		_tMsgFlowAct = tMsgFlowAct;
	}
}
