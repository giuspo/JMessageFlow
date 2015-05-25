package org.nocompany.jmsgflowtest;

import org.junit.Test;
import org.nocompany.jmsgflowlib.AMsgFlowSys;
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

		Awaitable<Object> tRes;

		for(int iCont = 0; iCont < 3; ++iCont)
		{
			tRes = Await.ready(tFut, Duration.apply(100, TimeUnit.MICROSECONDS));


		}


	}
}
