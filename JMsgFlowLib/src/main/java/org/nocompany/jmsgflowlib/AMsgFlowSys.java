package org.nocompany.jmsgflowlib;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import scala.concurrent.duration.FiniteDuration;

import java.util.List;

/**
 * Created by giulio on 14/05/15.
 */
public abstract class AMsgFlowSys implements IPublish, ISubscribe
{
	public abstract void LinkMsgFlowAct(AMsgFlowAct tMsgFlowAct);

	public abstract List<EventMsg> GetEvn(ActorRef tSubActor, FiniteDuration
		tDuration);

	protected abstract ActorRef getBrokerSys();

	protected abstract ActorSystem getActorSystem();
}
