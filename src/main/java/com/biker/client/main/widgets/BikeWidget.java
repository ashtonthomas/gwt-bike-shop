package com.biker.client.main.widgets;

import com.biker.client.common.resource.CoreClientBundle;
import com.biker.client.common.resource.CoreStyle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

public class BikeWidget extends Composite {

	private static BikeWidgetUiBinder uiBinder = GWT
			.create(BikeWidgetUiBinder.class);

	interface BikeWidgetUiBinder extends UiBinder<Widget, BikeWidget> {
	}
	
	@UiField(provided=true)
	CoreStyle css = CoreClientBundle.INSTANCE.coreStyle();

	private final Integer bikeId;
	
	@AssistedInject
	public BikeWidget(@Assisted("bikeId") Integer bikeId) {
		initWidget(uiBinder.createAndBindUi(this));
		this.bikeId = bikeId;
		setup();
		refresh();
	}
	
	private void setup(){
		// setup click handlers and all that jazz
	}
	
	private void refresh(){
		// request the bike info
		// We're gonna fake it!
		// But I could have request data and then called this method
		// in a callback given to some kind of cachClearHandler
		// This would accommodate some smooth data refresh
	}

}
