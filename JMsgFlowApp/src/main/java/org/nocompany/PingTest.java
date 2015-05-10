package org.nocompany;

import org.nocompany.jmsgflowlib.EventMsg;
import org.nocompany.jmsgflowlib.IMsgFlowAct;

/**
 * Created by giulio on 08/05/15.
 */
public class PingTest implements IMsgFlowAct
{
	int _iValue;

	public PingTest(int ival)
	{
		_iValue = ival;
	}

	@Override
	public void Init()
	{

	}

	@Override
	public void OnMsgFlowReceive(EventMsg tEventMsg)
	{

	}
}
