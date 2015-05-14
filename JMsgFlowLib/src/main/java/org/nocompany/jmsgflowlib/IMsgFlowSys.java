package org.nocompany.jmsgflowlib;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * Created by giulio on 14/05/15.
 */
public interface IMsgFlowSys
{
	ActorRef getBrokerSys();
	ActorSystem getActorSystem();
	void Publish(String strEvn, Object objData);
	void Publish(ActorRef tActor, String strEvn, Object objData);
}
