package org.nocompany.jmsgflowlib;

import akka.actor.UntypedActor;

/**
 * Created by giulio on 10/05/15.
 */
public class MsgFlowActImpl extends UntypedActor
{
	private IMsgFlowAct _tMsgFLowAct;
	private boolean _bInitOk = false;

	@Override
	public void onReceive(Object objMsg) throws Exception
	{
		if(objMsg instanceof InitMsgFlowActMsg)
		{
			InitMsgFlowActMsg tInitMsg = (InitMsgFlowActMsg)objMsg;

			_tMsgFLowAct = tInitMsg.getMsgFlowAct();

			_tMsgFLowAct.Init();
			_bInitOk = true;
		}
		else if(_bInitOk)
		{
			if(objMsg instanceof EventMsg)
			{
				EventMsg tEventMsg = (EventMsg)objMsg;

				_tMsgFLowAct.OnMsgFlowReceive(tEventMsg);
			}
		}
		else
		{
			unhandled(objMsg);
		}
	}
}
