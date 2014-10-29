package com.biker.client.main.ioc;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.biker.client.main.MainApp;

@GinModules(MainInjectorModule.class)
public interface MainInjector extends Ginjector {
  MainApp getMainApp();
}
