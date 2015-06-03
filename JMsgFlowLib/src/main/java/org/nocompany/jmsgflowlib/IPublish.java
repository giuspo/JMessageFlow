package org.nocompany.jmsgflowlib;

import akka.actor.ActorRef;

/**
 * Created by giulio on 15/05/15.
 */
interface IPublish
{
	void Publish(String strEvn, Object objData);

	void Publish(ActorRef tActor, String strEvn, Object objData);
}
