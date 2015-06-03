package org.nocompany.jmsgflowtest;

import org.nocompany.jmsgflowlib.AMsgFlowAct;

/**
 * Created by giulio on 11/05/15.
 */
public class PongTest extends AMsgFlowAct
{
	@Override
	protected void InitMsgFlow()
	{
		Subscribe("Ping");
	}

	@Override
	protected void OnMsgFlowReceive(String strEvn, Object objData)
	{
		if(strEvn.equals("Ping"))
		{
			int iVal = ((PingMsg)objData).getCount();

			getLog().info("Received Ping {}", iVal);
			Publish("Pong", new PongMsg(iVal));
		}
	}
}
