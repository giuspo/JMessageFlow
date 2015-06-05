package org.nocompany.jmsgflowtest;

import akka.actor.ActorRef;
import org.junit.Assert;
import org.junit.Test;
import org.nocompany.jmsgflowlib.AMsgFlowSys;
import org.nocompany.jmsgflowlib.EventMsg;
import org.nocompany.jmsgflowlib.MsgFlowSys;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class JMessageFlowTest
{
	@Test
	public void testPublish() throws
		Exception
	{
		AMsgFlowSys tMsgFlowSys = new MsgFlowSys("MsgFlowSys");

		tMsgFlowSys.LinkMsgFlowAct(new PongTest());

		Future<Object> tFut = tMsgFlowSys.Subscribe("Pong");

		tMsgFlowSys.Publish("Ping", new PingMsg(0));

		Object objData = Await.result(tFut, Duration.apply(1000, TimeUnit.MILLISECONDS));

		Assert.assertTrue(objData instanceof EventMsg);

		EventMsg tEvnMsg = (EventMsg)objData;

		Assert.assertTrue(tEvnMsg.getEvent().equals("Pong"));
	}

	@Test
	public void testPublish2() throws
		Exception
	{
		int iTimeout = 10000;
		AMsgFlowSys tMsgFlowSys = new MsgFlowSys("MsgFlowSys");

		tMsgFlowSys.LinkMsgFlowAct(new PongTest());

		ActorRef tSubActor = tMsgFlowSys.Subscribe2("Pong", FiniteDuration.create
			(iTimeout, TimeUnit.MILLISECONDS));

		tMsgFlowSys.Publish("Ping", new PingMsg(0));

		List<EventMsg> rgtEvn;
		long lMilli = System.currentTimeMillis();

		for(;;)
		{
			rgtEvn = tMsgFlowSys.GetEvn(tSubActor, FiniteDuration.create
				(iTimeout, TimeUnit.MILLISECONDS));

			if(!rgtEvn.isEmpty())
			{
				break;
			}

			if((System.currentTimeMillis() - lMilli) > (iTimeout * 2))
			{
				break;
			}

			Thread.sleep(1);
		}

		Assert.assertFalse(rgtEvn.isEmpty());
		Assert.assertTrue(rgtEvn.get(0).getEvent().equals("Pong"));
	}
}
