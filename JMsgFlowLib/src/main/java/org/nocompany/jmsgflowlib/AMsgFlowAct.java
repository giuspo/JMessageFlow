package org.nocompany.jmsgflowlib;

import akka.actor.UntypedActor;

/**
 * Created by giulio on 08/05/15.
 */
public abstract class AMsgFlowAct
{
	public abstract void Init();

	public abstract void OnMsgFlowReceive(EventMsg tEventMsg);

	public UntypedActor Create()
	{
		return new UntypedActor()
		{
			@Override
			public void preStart() throws Exception
			{
				super.preStart();

				Init();
			}

			@Override
			public void onReceive(Object objMsg) throws Exception
			{
				if(objMsg instanceof EventMsg)
				{
					EventMsg tEventMsg = (EventMsg)objMsg;

					OnMsgFlowReceive(tEventMsg);
				}
				else
				{
					unhandled(objMsg);
				}
			}
		};
	}
}
