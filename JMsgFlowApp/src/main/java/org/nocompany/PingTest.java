package org.nocompany;

import org.nocompany.jmsgflowlib.EventMsg;
import org.nocompany.jmsgflowlib.AMsgFlowAct;

/**
 * Created by giulio on 08/05/15.
 */
public class PingTest extends AMsgFlowAct
{
	@Override
	public void InitMsgFlow()
	{
		Subscribe("Pong");
	}

	@Override
	public void OnTick()
	{
		super.OnTick();

		Publish("Ping", null);
	}

	@Override
	public void OnMsgFlowReceive(EventMsg tEventMsg)
	{
		if(tEventMsg.getEvent().equals("Pong"))
		{
			getLog().info("Pong received!");
		}
	}
}
