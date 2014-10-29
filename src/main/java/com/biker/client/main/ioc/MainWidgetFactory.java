package com.biker.client.main.ioc;

import com.biker.client.main.widgets.BikeWidget;
import com.google.inject.assistedinject.Assisted;

public interface MainWidgetFactory {
  BikeWidget bikeWidget(@Assisted("bikeId") Integer bikeId);
}
