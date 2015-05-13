package org.nocompany.jmsgflowlib;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;

/**
 * Created by giulio on 10/05/15.
 */
public class MsgFlowSys
{
	private final ActorSystem _tActorSys;
	private final ActorRef _tBrokerSys;

	public MsgFlowSys(String strName)
	{
		_tActorSys = ActorSystem.create(strName);
		_tBrokerSys = _tActorSys.actorOf(Props.create(BrokerSys.class), "BrokerSys");
	}

	public void LinkMsgFlowAct(AMsgFlowAct tMsgFlowAct)
	{
		ActorRef tActor = _tActorSys.actorOf(Props.create(MsgFlowActImpl.class));

		tMsgFlowAct.setActorImpl(tActor);
		tMsgFlowAct.setBroker(_tBrokerSys);
		tMsgFlowAct.setActorSys(_tActorSys);

		Inbox tInbox = Inbox.create(_tActorSys);
		tInbox.send(tActor, new InitActMsg(tMsgFlowAct, _tBrokerSys));
	}
}
