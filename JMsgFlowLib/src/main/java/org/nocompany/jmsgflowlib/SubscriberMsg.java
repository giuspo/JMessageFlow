package org.nocompany.jmsgflowlib;

/**
 * Created by giulio on 10/05/15.
 */
public class SubscriberMsg
{
	private final String _strEventName;

	public String getEventName()
	{
		return _strEventName;
	}

	public SubscriberMsg(String strEventName)
	{
		_strEventName = strEventName;
	}
}
