package org.nocompany.jmsgflowtest;

import org.junit.Assert;
import org.junit.Test;
import org.nocompany.jmsgflowlib.AMsgFlowSys;
import org.nocompany.jmsgflowlib.EventMsg;
import org.nocompany.jmsgflowlib.MsgFlowSys;
import scala.concurrent.Await;
import scala.concurrent.Awaitable;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

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

		Object objData = Await.result(tFut, Duration.apply(1000000, TimeUnit.MILLISECONDS));

		Assert.assertTrue(objData instanceof EventMsg);

		EventMsg tEvnMsg = (EventMsg)objData;

		Assert.assertTrue(tEvnMsg.getEvent().equals("Pong"));
	}
}
