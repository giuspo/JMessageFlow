package org.nocompany.jmsgflowlib;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.pattern.PromiseActorRef;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.List;

/**
 * Created by giulio on 10/05/15.
 */
public final class MsgFlowSys extends AMsgFlowSys
{
	private final ActorSystem _tActorSys;

	private final ActorRef _tBrokerSys;

	@Override
	protected ActorRef getBrokerSys()
	{
		return _tBrokerSys;
	}

	@Override
	protected ActorSystem getActorSystem()
	{
		return _tActorSys;
	}

	@Override
	public void Publish(String strEvn, Object objData)
	{
		Inbox tInbox = Inbox.create(_tActorSys);
		Object objDataTmp[] = { objData };

		tInbox.send(_tBrokerSys, new EventMsg(strEvn, objDataTmp));
	}

	@Override
	public void Publish(ActorRef tActor, String strEvn, Object objData)
	{
		Object objDataTmp[] = { objData };
		_tBrokerSys.tell(new EventMsg(strEvn, objDataTmp), tActor);
	}

	@Override
	public Future<Object> Subscribe(String strEvn)
	{
		FutureAct tFutureAct = new FutureAct(this);
		ActorRef tActor = _tActorSys.actorOf(Props.create(FutureActImpl.class));

		return Patterns.ask(tActor, new InitFutureActMsg(tFutureAct, strEvn),
			1000);
	}

	@Override
	public ActorRef Subscribe2(String strEvn, FiniteDuration tDuration)
	{
		TmpSubscriberAct tSub = new TmpSubscriberAct();
		ActorRef tActor = _tActorSys.actorOf(Props.create(
			TmpSubscriberActImpl.class));

		tSub.setActorImpl(tActor);
		tSub.setMsgFlowSys(this);

		Inbox tInbox = Inbox.create(_tActorSys);

		tInbox.send(tActor, new InitTmpSubscriberMsg(tSub, strEvn));
		Object tObj = tInbox.receive(tDuration);

		return tActor;
	}

	@Override
	public List<EventMsg> GetEvn(ActorRef tSubActor, FiniteDuration tDuration)
	{
		Inbox tInbox = Inbox.create(_tActorSys);

		tInbox.send(tSubActor, new GetAllEvnMsg());
		return (List<EventMsg>)tInbox.receive(tDuration);
	}

	@Override
	public void LinkMsgFlowAct(AMsgFlowAct tMsgFlowAct)
	{
		ActorRef tActor = _tActorSys.actorOf(Props.create(MsgFlowActImpl.class));

		tMsgFlowAct.setActorImpl(tActor);
		tMsgFlowAct.setMsgFlowSys(this);

		Inbox tInbox = Inbox.create(_tActorSys);

		tInbox.send(tActor, new InitMsgFlowActMsg(tMsgFlowAct));
	}

	public MsgFlowSys(String strName)
	{
		_tActorSys = ActorSystem.create(strName);
		_tBrokerSys = _tActorSys.actorOf(Props.create(BrokerSys.class), "BrokerSys");
	}
}
