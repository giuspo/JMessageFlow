package org.nocompany.jmsgflowlib;

import scala.concurrent.Future;

/**
 * Created by giulio on 25/05/15.
 */
public interface ISubscribe
{
	Future<Object> Subscribe(String strEvn);
}
