package org.nocompany;

import org.nocompany.jmsgflowlib.AMsgFlowAct;
import org.nocompany.jmsgflowlib.EventMsg;

/**
 * Created by giulio on 11/05/15.
 */
public class PongTest extends AMsgFlowAct
{
	@Override
	public void InitMsgFlow()
	{
		Subscribe("Ping");
	}

	@Override
	public void OnMsgFlowReceive(EventMsg tEventMsg)
	{
		if(tEventMsg.getEvent().equals("Ping"))
		{
			getLog().info("Ping received!");

			Publish("Pong", null);
		}
	}
}
