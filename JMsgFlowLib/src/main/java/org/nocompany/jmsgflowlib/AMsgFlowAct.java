package org.nocompany.jmsgflowlib;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.duration.Duration;

import java.util.List;

/**
 * Created by giulio on 08/05/15.
 */
public abstract class AMsgFlowAct
{
	private ActorRef _tActor;

	private AMsgFlowSys _tMsgFlowSys;

	private LoggingAdapter _tLog;

	protected final void setActorImpl(ActorRef tActor)
	{
		_tActor = tActor;
	}

	public AMsgFlowSys getMsgFlowSys()
	{
		return _tMsgFlowSys;
	}

	protected final void setMsgFlowSys(AMsgFlowSys tMsgFlowSys)
	{
		_tMsgFlowSys = tMsgFlowSys;
		_tLog = Logging.getLogger(_tMsgFlowSys.getActorSystem(), this);
	}

	protected final LoggingAdapter getLog()
	{
		return _tLog;
	}

	protected final void Publish(String strEvn, Object objData)
	{
		_tMsgFlowSys.Publish(_tActor, strEvn, objData);
	}

	protected final void Subscribe(String strEvn)
	{
		_tMsgFlowSys.getBrokerSys().tell(new SubscriberMsg(strEvn), _tActor);
	}

	protected final void UnSubscribe(String strEvn)
	{
		_tMsgFlowSys.getBrokerSys().tell((new UnSubscribeMsg(strEvn)), _tActor);
	}

	protected final void SetTick(Duration tDuration)
	{
		_tActor.tell(new SetTickMsg(tDuration), _tActor);
	}

	protected void OnTick()
	{

	}

	protected void OnMsgFlowReceive(EventMsg tEvn)
	{

	}

	protected abstract void InitMsgFlow();

	protected abstract void OnMsgFlowReceive(String strEvn, Object objData);
}
