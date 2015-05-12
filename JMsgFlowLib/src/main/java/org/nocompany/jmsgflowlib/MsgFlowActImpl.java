package org.nocompany.jmsgflowlib;

import akka.actor.UntypedActor;

/**
 * Created by giulio on 10/05/15.
 */
public class MsgFlowActImpl extends UntypedActor
{
	private AMsgFlowAct _tMsgFLowAct;
	private boolean _bInitOk = false;

	@Override
	public void onReceive(Object objMsg) throws Exception
	{
		if(objMsg instanceof InitActMsg)
		{
			InitActMsg tInitMsg = (InitActMsg)objMsg;

			_tMsgFLowAct = tInitMsg.getMsgFlowAct();
			_tMsgFLowAct.InitMsgFlow();
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
