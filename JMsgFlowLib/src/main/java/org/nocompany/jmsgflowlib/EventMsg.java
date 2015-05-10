package org.nocompany.jmsgflowlib;

/**
 * Created by giulio on 10/05/15.
 */
public class EventMsg
{
	private final String _strEvent;
	private final Object _objData;

	public String getEvent()
	{
		return _strEvent;
	}

	public Object getData()
	{
		return _objData;
	}

	public EventMsg(String strEvent, Object objData)
	{
		this._strEvent = strEvent;
		this._objData = objData;
	}
}
