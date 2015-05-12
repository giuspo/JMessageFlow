package org.nocompany.jmsgflowlib;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Created by giulio on 08/05/15.
 */
public abstract class AMsgFlowAct
{
	private ActorRef _tBrokerSys;

	private ActorRef _tActor;

	public abstract void InitMsgFlow();

	public abstract void OnMsgFlowReceive(EventMsg tEventMsg);

	public void Init()
	{

	}

	/*
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
	*/
}
