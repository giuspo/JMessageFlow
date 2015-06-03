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
		AMsgFlowSys tMsgFlowSys = new MsgFlowSys("MsgFlowSys");

		tMsgFlowSys.LinkMsgFlowAct(new PongTest());

		ActorRef tSubActor = tMsgFlowSys.Subscribe2("Pong");

		tMsgFlowSys.Publish("Ping", new PingMsg(0));

		List<EventMsg> rgtEvn;
		for(;;)
		{
			long lMilli = System.currentTimeMillis();

			rgtEvn = tMsgFlowSys.GetEvn(tSubActor, FiniteDuration.create
				(1000, TimeUnit.MILLISECONDS));

			if(!rgtEvn.isEmpty())
			{
				break;
			}

			if()
			{

			}

			Thread.sleep(1);
		}

		Assert.assertFalse(rgtEvn.isEmpty());
		Assert.assertTrue(rgtEvn.get(0).getEvent().equals("Pong"));
	}
}
