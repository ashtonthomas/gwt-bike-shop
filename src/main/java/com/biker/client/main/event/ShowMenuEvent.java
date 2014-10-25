package com.biker.client.main.event;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class ShowMenuEvent extends GenericEvent {

	public enum ShowMenuEventType {
		MAIN, PERSONAL, HIDE;
	}
	
	private final ShowMenuEventType menuType;
	
	public ShowMenuEvent(ShowMenuEventType menuType){
		this.menuType = menuType;
	}
	
	public ShowMenuEventType getMenuType(){
		return menuType;
	}
}
