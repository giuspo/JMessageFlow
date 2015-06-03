package org.nocompany.jmsgflowlib;

/**
 * Created by giulio on 10/05/15.
 */
class InitTmpSubscriberMsg
{
	private final AMsgFlowAct _tMsgFlowAct;

	private final String _strEvn;

	public AMsgFlowAct getMsgFlowAct()
	{
		return _tMsgFlowAct;
	}

	public String getEvn()
	{
		return _strEvn;
	}

	public InitTmpSubscriberMsg(AMsgFlowAct tMsgFlowAct,
		String strEvn)
	{
		_tMsgFlowAct = tMsgFlowAct;
		_strEvn = strEvn;
	}
}
