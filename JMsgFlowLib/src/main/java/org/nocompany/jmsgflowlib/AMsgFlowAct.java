package org.nocompany.jmsgflowlib;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.duration.Duration;

/**
 * Created by giulio on 08/05/15.
 */
public abstract class AMsgFlowAct
{
	private ActorRef _tBrokerSys;

	private ActorRef _tActor;

	private ActorSystem _tActorSys;

	private LoggingAdapter _tLog;

	public final void setActorImpl(ActorRef tActor)
	{
		_tActor = tActor;
	}

	public final void setBroker(ActorRef tBroker)
	{
		_tBrokerSys = tBroker;
	}

	public final void setActorSys(ActorSystem tActorSys)
	{
		_tActorSys = tActorSys;
		_tLog = Logging.getLogger(_tActorSys, this);
	}

	protected final LoggingAdapter getLog()
	{
		return _tLog;
	}

	protected final void Publish(String strEvn, Object objData)
	{
		Object objDataTmp[] = { objData };
		_tBrokerSys.tell(new EventMsg(strEvn, objDataTmp), _tActor);
	}

	protected final void Subscribe(String strEvn)
	{
		_tBrokerSys.tell(new SubscriberMsg(strEvn), _tActor);
	}

	protected final void UnSubscribe(String strEvn)
	{
		_tBrokerSys.tell((new UnSubscribeMsg(strEvn)), _tActor);
	}

	protected final void SetTick(Duration tDuration)
	{
		Inbox tInbox = Inbox.create(_tActorSys);
		tInbox.send(_tActor, new SetTickMsg(tDuration));
	}

	public void OnTick()
	{

	}

	protected abstract void InitMsgFlow();

	protected abstract void OnMsgFlowReceive(EventMsg tEventMsg);
}
