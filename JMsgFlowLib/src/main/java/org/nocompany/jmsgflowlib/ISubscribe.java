package org.nocompany.jmsgflowlib;

import akka.actor.ActorRef;
import scala.concurrent.Future;

/**
 * Created by giulio on 25/05/15.
 */
interface ISubscribe
{
	Future<Object> Subscribe(String strEvn);

	ActorRef Subscribe2(String strEvn);
}
