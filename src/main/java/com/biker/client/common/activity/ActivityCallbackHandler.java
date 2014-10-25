package com.biker.client.common.activity;

import com.google.gwt.activity.shared.Activity;
import com.biker.client.common.place.AppPlace;

public interface ActivityCallbackHandler<A extends Activity> {
	public void onRecieveActivity(A instance, AppPlace linkedPlace);
}
