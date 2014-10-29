package com.biker.client.common.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.biker.client.common.place.AppPlace;

public abstract class AppActivity<P extends AppPlace> extends AbstractActivity {

  protected final P place;

  public AppActivity(P place) {
    this.place = place;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    throw new RuntimeException("Wrong Constructor used for AppActivity");
  }

  public P getPlace() {
    return place;
  }

  abstract public void doStart(AcceptsOneWidget panel, EventBus eventBus);
}
