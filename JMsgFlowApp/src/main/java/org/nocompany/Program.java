package org.nocompany;

import org.nocompany.jmsgflowlib.MsgFlowSingleton;
import org.nocompany.jmsgflowlib.MsgFlowSys;

/**
 * Created by giulio on 08/05/15.
 */
public class Program
{
	private MsgFlowSingleton _tMsgFlowSingleton;

	private void Init()
	{
		_tMsgFlowSingleton = new MsgFlowSingleton(new MsgFlowSys("MainSys"));

		MsgFlowSingleton.getInstance().LinkMsgFlowAct(new PingTest());
		MsgFlowSingleton.getInstance().LinkMsgFlowAct(new PongTest());
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
