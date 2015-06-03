package org.nocompany.jmsgflowlib;

import akka.actor.ActorRef;
import scala.util.parsing.combinator.testing.Str;

/**
 * Created by giulio on 24/05/15.
 */
class InitFutureActMsg
{
	private final FutureAct _tFutureAct;

	private final String _strEvn;

	public FutureAct getFutureAct()
	{
		return _tFutureAct;
	}

	public String getEvn()
	{
		return _strEvn;
	}

	public InitFutureActMsg(FutureAct tFutureAct, String strEvn)
	{
		_tFutureAct = tFutureAct;
		_strEvn = strEvn;
	}
}
