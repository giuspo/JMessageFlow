package org.nocompany.jmsgflowlib;

/**
 * Created by giulio on 10/05/15.
 */
public class InitMsgFlowActMsg
{
	private final IMsgFlowAct _tMsgFlowAct;

	public IMsgFlowAct getMsgFlowAct()
	{
		return _tMsgFlowAct;
	}

	public InitMsgFlowActMsg(IMsgFlowAct tMsgFlowAct)
	{
		_tMsgFlowAct = tMsgFlowAct;
	}
}
