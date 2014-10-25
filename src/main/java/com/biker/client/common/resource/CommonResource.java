package com.biker.client.common.resource;

import com.biker.client.common.fast.FastPress;

public class CommonResource {

	public static void injectStyle() {
		CoreClientBundle.INSTANCE.coreStyle().ensureInjected();
		FastPress.injectFastPressStyle();
	}
	
}
