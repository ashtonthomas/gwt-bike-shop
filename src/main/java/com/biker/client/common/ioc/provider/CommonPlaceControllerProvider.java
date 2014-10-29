package com.biker.client.common.ioc.provider;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class CommonPlaceControllerProvider implements Provider<PlaceController> {

  private final EventBus eventBus;

  @Inject
  public CommonPlaceControllerProvider(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  @Override
  public PlaceController get() {
    return new PlaceController(eventBus);
  }

}
