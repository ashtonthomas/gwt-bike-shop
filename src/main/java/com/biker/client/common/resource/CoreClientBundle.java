package com.biker.client.common.resource;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

public interface CoreClientBundle extends ClientBundle {
	
	static final CoreClientBundle INSTANCE = GWT.create(CoreClientBundle.class);
	
	@Source("styles/core.gss")
	CoreStyle coreStyle();
	
	@Source("images/trnp75.png")
	@ImageOptions(repeatStyle=RepeatStyle.Both)
	ImageResource trnp75();
	
	@Source("images/trnp25.png")
	@ImageOptions(repeatStyle=RepeatStyle.Both)
	ImageResource trnp25();
	
	@Source("images/trnpGrey90.png")
	@ImageOptions(repeatStyle=RepeatStyle.Both)
	ImageResource trnpGrey90();
}
