package com.tenpearls.android.service;


import android.content.Context;

/**
 * This class has to be overridden for Web service implementation
 * <br/> This class has the boiler plate code for initialization
 * and needs a {@link ServiceProtocol} subclass instance to work.
 */

public abstract class ServiceFactory {

	public ServiceFactory() {

	}

	/**
	 * Call this method to initialize the Factory.
	 * Call to this method is a must.
	 * @throws Exception when {@link ServiceFactory#getServiceProtocol()}
	 * returns {@code null}
	 *
	 * @see ServiceFactory#getServiceProtocol()
	 */

	public final void initialize (Context context) throws Exception {

		ServiceManager.getInstance().initialize(getServiceProtocol(), context);
	}

	/**
	 * Use this method to create and load your service interfaces
	 * @param serviceClass Class of Service that you want to load
	 *
	 * @return  Service implementation
	 */

	protected final <S> S loadService(Class<S> serviceClass) {

		return ServiceManager.getInstance().loadService(serviceClass);
	}



	/**
	 * Return a {@link ServiceProtocol} subclass in this method
	 *
	 */
	public abstract ServiceProtocol getServiceProtocol();

}
