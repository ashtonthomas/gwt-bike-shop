package com.biker.client.common.ioc.provider;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.biker.client.common.activity.AsyncActivityManager;
import com.biker.client.common.activity.AsyncActivityMapper;

public class AsyncActivityManagerProvider implements Provider<AsyncActivityManager>{
	
	private final AsyncActivityMapper activityMapper;
	private final EventBus eventBus;

	@Inject
	public AsyncActivityManagerProvider(EventBus eventBus, AsyncActivityMapper activityMapper){
		this.eventBus = eventBus;
		this.activityMapper = activityMapper;
	}
	
	@Override
	public AsyncActivityManager get() {
		return new AsyncActivityManager(eventBus, activityMapper);
	}

}
