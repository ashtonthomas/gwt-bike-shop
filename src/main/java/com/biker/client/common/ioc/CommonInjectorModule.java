package com.biker.client.common.ioc;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;
import com.biker.client.common.activity.AsyncActivityManager;
import com.biker.client.common.ioc.provider.AsyncActivityManagerProvider;
import com.biker.client.common.ioc.provider.CommonPlaceControllerProvider;
import com.biker.client.common.view.Shell;

public abstract class CommonInjectorModule extends AbstractGinModule {
	
	@Override
	protected void configure(){
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(Shell.class).in(Singleton.class);
		bind(PlaceController.class).toProvider(CommonPlaceControllerProvider.class).in(Singleton.class);
		bind(AsyncActivityManager.class).toProvider(AsyncActivityManagerProvider.class).in(Singleton.class);
		//LoadingHelper
		//Shared Views
		
		configureModule();
		
	}
	
	protected abstract void configureModule();
	

}
