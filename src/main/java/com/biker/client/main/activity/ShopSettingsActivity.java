package com.biker.client.main.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import com.biker.client.common.activity.AppActivity;
import com.biker.client.main.place.ShopSettingsPlace;

public class ShopSettingsActivity extends AppActivity<ShopSettingsPlace> {

  public interface ShopSettingsViewI extends IsWidget {
    public void setPresenter(ShopSettingsActivity presenter);
  }

  private final ShopSettingsViewI view;
  private final PlaceController placeController;
  private EventBus eventBus;

  @AssistedInject
  public ShopSettingsActivity(@Assisted("place") ShopSettingsPlace place, ShopSettingsViewI view,
      PlaceController placeController) {
    super(place);
    this.view = view;
    view.setPresenter(this);
    this.placeController = placeController;
  }

  @Override
  public void doStart(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(view);
    this.eventBus = eventBus;
  }

}
