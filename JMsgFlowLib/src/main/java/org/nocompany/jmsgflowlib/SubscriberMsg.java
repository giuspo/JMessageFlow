package org.nocompany.jmsgflowlib;

/**
 * Created by giulio on 10/05/15.
 */
class SubscriberMsg
{
	private final String _strEventName;

	public String getEventName()
	{
		return _strEventName;
	}

	public SubscriberMsg(String strEvnName)
	{
		_strEventName = strEvnName;
	}
}
