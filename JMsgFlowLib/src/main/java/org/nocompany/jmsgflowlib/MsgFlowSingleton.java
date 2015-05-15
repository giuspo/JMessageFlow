package org.nocompany.jmsgflowlib;

/**
 * Created by giulio on 15/05/15.
 */
public class MsgFlowSingleton
{
	private static MsgFlowSys _tMsgFlowSys;

	public static MsgFlowSys getInstance()
	{
		return _tMsgFlowSys;
	}

	public MsgFlowSingleton(MsgFlowSys tMsgFlowSys)
	{
		_tMsgFlowSys = tMsgFlowSys;
	}
}
