package com.biker.client.common.fast;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Event;

public class PressEvent extends GwtEvent<PressHandler> {
	private static final Type<PressHandler> TYPE = new Type<PressHandler>();

	public PressEvent(Event event) {
	}

	/**
	 * Static method to get event Type
	 * 
	 * @return
	 */
	public static Type<PressHandler> getType() {
		return TYPE;
	}

	@Override
	public Type<PressHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PressHandler handler) {
		handler.onPress(this);
	}
}
