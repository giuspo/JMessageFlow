package org.nocompany.jmsgflowlib;

/**
 * Created by giulio on 10/05/15.
 */
public class UnSubscribeMsg
{
	private final String _strEventName;

	public String getEventName()
	{
		return _strEventName;
	}

	public UnSubscribeMsg(String strEventName)
	{
		this._strEventName = strEventName;
	}
}
