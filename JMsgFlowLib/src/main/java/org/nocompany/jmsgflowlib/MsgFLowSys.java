package org.nocompany.jmsgflowlib;

import akka.actor.ActorSystem;
import scala.util.parsing.combinator.testing.Str;

/**
 * Created by giulio on 10/05/15.
 */
public class MsgFLowSys
{
	private final ActorSystem _tActorSys;

	public MsgFLowSys(String strName)
	{
		_tActorSys = ActorSystem.create(strName);
	}

	public void CreateAct(IMsgFlowAct tMsgFlowAct)
	{

	}
}
