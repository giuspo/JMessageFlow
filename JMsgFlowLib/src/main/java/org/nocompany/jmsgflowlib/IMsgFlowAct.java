package org.nocompany.jmsgflowlib;

/**
 * Created by giulio on 08/05/15.
 */
public interface IMsgFlowAct
{
	void Init();
	void OnMsgFlowReceive(EventMsg tEventMsg);
}
