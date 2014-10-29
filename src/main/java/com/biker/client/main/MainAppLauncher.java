package com.biker.client.main;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Command;
import com.biker.client.common.utility.Butter;
import com.biker.client.main.ioc.MainInjector;

public class MainAppLauncher implements EntryPoint {

  @Override
  public void onModuleLoad() {
    // Yield to the browser before firing up the application
    Butter.go(new Command() {
      @Override
      public void execute() {
        injectApp();
      }
    });
  }

  private void injectApp() {
    MainInjector injector = GWT.create(MainInjector.class);
    injector.getMainApp().run();
  }

}
