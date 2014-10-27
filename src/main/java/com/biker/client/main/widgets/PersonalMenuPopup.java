package com.biker.client.main.widgets;

import com.biker.client.common.resource.CoreClientBundle;
import com.biker.client.common.resource.CoreStyle;
import com.biker.client.common.widgets.SimplePopup;
import com.biker.client.main.event.ShowMenuEvent;
import com.biker.client.main.event.ShowMenuEvent.ShowMenuEventType;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

public class PersonalMenuPopup extends SimplePopup {
	
	CoreStyle css = CoreClientBundle.INSTANCE.coreStyle();

	private final PersonalMenu menu;
	private final EventBus eventBus;
	
	@Inject
	public PersonalMenuPopup(PersonalMenu menu, EventBus eventBus) {
		super(true);
		this.menu = menu;
		this.eventBus = eventBus;
		initialize();
	}
	
	private void initialize(){
		setWidget(menu);
		setStyleName(css.personalMenuPopup());
	}

	@Override
	public void hide(boolean autoClosed){
		super.hide(autoClosed);
		if(autoClosed){
			eventBus.fireEvent(new ShowMenuEvent(ShowMenuEventType.HIDE));
		}
	}
	
}
