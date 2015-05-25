package org.nocompany.jmsgflowlib;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * Created by giulio on 14/05/15.
 */
public abstract class AMsgFlowSys implements IPublish, ISubscribe
{
	public abstract ActorRef getBrokerSys();
	public abstract ActorSystem getActorSystem();
	public abstract void LinkMsgFlowAct(AMsgFlowAct tMsgFlowAct);
}
