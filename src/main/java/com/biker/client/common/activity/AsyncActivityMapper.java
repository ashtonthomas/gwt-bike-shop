package com.biker.client.common.activity;

import com.biker.client.common.place.AppPlace;

@SuppressWarnings("rawtypes")
public abstract class AsyncActivityMapper<T extends AppPlace, A extends AppActivity> {
  abstract public void getActivity(T place, ActivityCallbackHandler<A> activityCallbackHandler);
}
