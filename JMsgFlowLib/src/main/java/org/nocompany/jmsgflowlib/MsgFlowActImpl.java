package org.nocompany.jmsgflowlib;

import akka.actor.Cancellable;
import akka.actor.UntypedActor;
import scala.concurrent.duration.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Created by giulio on 10/05/15.
 */
public class MsgFlowActImpl extends UntypedActor
{
	private AMsgFlowAct _tMsgFLowAct;

	private boolean _bInitOk = false;

	private Cancellable _tTickCancel;

	private final String _strTick = "Tick";

	private Cancellable SetTick(Duration tDuration)
	{
		return getContext().system().scheduler().schedule(Duration.Zero(),
			Duration.create(100, TimeUnit.MILLISECONDS), getSelf(), _strTick,
			getContext().system().dispatcher(), null);
	}

	@Override
	public void preStart() throws
		Exception
	{
		super.preStart();

		_tTickCancel = SetTick(Duration.create(100, TimeUnit.MILLISECONDS));
	}

	@Override
	public void onReceive(Object objMsg) throws
		Exception
	{
		if(objMsg instanceof InitMsgFlowActMsg)
		{
			InitMsgFlowActMsg tInitMsg = (InitMsgFlowActMsg)objMsg;

			_tMsgFLowAct = tInitMsg.getMsgFlowAct();
			_tMsgFLowAct.InitMsgFlow();
			_bInitOk = true;
		}
		else if(objMsg instanceof SetTickMsg)
		{
			SetTickMsg tTickMsg = (SetTickMsg)objMsg;

			_tTickCancel.cancel();
			_tTickCancel = SetTick(tTickMsg.getDuration());
		}
		else if(_bInitOk)
		{
			if(objMsg instanceof EventMsg)
			{
				EventMsg tEventMsg = (EventMsg)objMsg;

				_tMsgFLowAct.OnMsgFlowReceive(tEventMsg.getEvent(),
					((Object[])(tEventMsg.getData()))[0]);
			}
			else if(objMsg instanceof String)
			{
				String strTick = (String)objMsg;

				if(strTick.equals(_strTick))
				{
					_tMsgFLowAct.OnTick();
				}
			}
		}
		else
		{
			unhandled(objMsg);
		}
	}
}
