package org.nocompany;

import org.nocompany.jmsgflowlib.EventMsg;
import org.nocompany.jmsgflowlib.AMsgFlowAct;

/**
 * Created by giulio on 08/05/15.
 */
public class PingTest extends AMsgFlowAct
{
	private int _iVal = 0;

	@Override
	public void InitMsgFlow()
	{
		Subscribe("Pong");
	}

	@Override
	public void OnTick()
	{
		super.OnTick();

		getLog().info("Send Ping {}", _iVal);
		Publish("Ping", new PingMsg(_iVal));
		++_iVal;
	}

	@Override
	protected void OnMsgFlowReceive(String strEvn, Object objData)
	{
		if(strEvn.equals("Pong"))
		{
			getLog().info("Received Pong {}", ((PongMsg)objData).getCount());
		}
	}
}
