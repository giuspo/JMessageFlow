package org.nocompany.jmsgflowlib;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;

/**
 * Created by giulio on 10/05/15.
 */
public final class MsgFlowSys implements IMsgFlowSys
{
	private final ActorSystem _tActorSys;
	private final ActorRef _tBrokerSys;

	public MsgFlowSys(String strName)
	{
		_tActorSys = ActorSystem.create(strName);
		_tBrokerSys = _tActorSys.actorOf(Props.create(BrokerSys.class), "BrokerSys");
	}

	@Override
	public ActorRef getBrokerSys()
	{
		return _tBrokerSys;
	}

	@Override
	public ActorSystem getActorSystem()
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


	public void LinkMsgFlowAct(AMsgFlowAct tMsgFlowAct)
	{
		ActorRef tActor = _tActorSys.actorOf(Props.create(MsgFlowActImpl.class));

		tMsgFlowAct.setActorImpl(tActor);
		tMsgFlowAct.setMsgFlowSys(this);

		Inbox tInbox = Inbox.create(_tActorSys);

		tInbox.send(tActor, new InitActMsg(tMsgFlowAct, _tBrokerSys));
	}
}
