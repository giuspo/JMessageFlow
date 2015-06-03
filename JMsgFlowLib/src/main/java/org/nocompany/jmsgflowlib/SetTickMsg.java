package org.nocompany.jmsgflowlib;

import scala.concurrent.duration.Duration;

/**
 * Created by giulio on 13/05/15.
 */
class SetTickMsg
{
	private final Duration _tDuration;

	public Duration getDuration()
	{
		return _tDuration;
	}

	public SetTickMsg(Duration tDuration)
	{
		_tDuration = tDuration;
	}
}
