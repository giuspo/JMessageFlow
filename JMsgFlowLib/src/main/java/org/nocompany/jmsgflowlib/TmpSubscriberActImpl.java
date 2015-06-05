package org.nocompany.jmsgflowlib;

import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giulio on 10/05/15.
 */
class TmpSubscriberActImpl extends UntypedActor
{
	private List<EventMsg> _rgtEventMsg = new ArrayList<>();

	private AMsgFlowAct _tFlowAct;

	private boolean _bInitOk = false;

	@Override
	public void onReceive(Object objMsg) throws
		Exception
	{
		if(objMsg instanceof InitTmpSubscriberMsg)
		{
			InitTmpSubscriberMsg tInitMsg = (InitTmpSubscriberMsg)objMsg;

			_tFlowAct = tInitMsg.getMsgFlowAct();
			tInitMsg.getMsgFlowAct().getMsgFlowSys().getBrokerSys().tell(
				new SubscriberMsg(tInitMsg.getEvn()), self());
			_bInitOk = true;
			sender().tell(new OkTmpSubscribe(), self());
		}
		else if(_bInitOk)
		{
			if(objMsg instanceof EventMsg)
			{
				_rgtEventMsg.add((EventMsg)objMsg);
			}
			else if(objMsg instanceof GetAllEvnMsg)
			{
				sender().tell(new ArrayList<>(_rgtEventMsg), self());
				context().stop(self());
			}
		}
	}
}
