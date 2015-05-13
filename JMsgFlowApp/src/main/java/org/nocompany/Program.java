package org.nocompany;

import org.nocompany.jmsgflowlib.MsgFlowSys;

/**
 * Created by giulio on 08/05/15.
 */
public class Program
{
	private MsgFlowSys _tMsgFlowSys;

	private void Init()
	{
		_tMsgFlowSys = new MsgFlowSys("MainSys");
		_tMsgFlowSys.LinkMsgFlowAct(new PingTest());
		_tMsgFlowSys.LinkMsgFlowAct(new PongTest());
	}

	private void Run()
	{
		String strInput = "";

		for(;!strInput.equals("q");)
		{
			strInput = System.in.toString();
		}
	}

	public static void main(String[] args)
	{
		Program tProg = new Program();

		tProg.Init();
		tProg.Run();
	}
}
