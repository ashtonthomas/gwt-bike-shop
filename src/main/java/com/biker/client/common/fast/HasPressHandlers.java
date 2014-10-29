package com.biker.client.common.fast;

import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

public interface HasPressHandlers extends HasHandlers {
  HandlerRegistration addPressHandler(PressHandler handler);
}
