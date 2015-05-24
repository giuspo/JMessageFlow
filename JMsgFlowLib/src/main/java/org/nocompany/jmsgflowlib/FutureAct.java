package org.nocompany.jmsgflowlib;

/**
 * Created by giulio on 24/05/15.
 */
public class FutureAct
{
	private AMsgFlowSys _tMsgFlowSys;

	public final AMsgFlowSys getMsgFlowSys()
	{
		return _tMsgFlowSys;
	}

	public FutureAct(AMsgFlowSys tMsgFlowSys)
	{
		_tMsgFlowSys = tMsgFlowSys;
	}
}
