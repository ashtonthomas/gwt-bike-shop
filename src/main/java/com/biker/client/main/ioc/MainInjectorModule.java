package com.biker.client.main.ioc;

import com.biker.client.common.activity.AsyncActivityMapper;
import com.biker.client.common.ioc.CommonInjectorModule;
import com.biker.client.main.activity.AssociatesActivity.AssociatesViewI;
import com.biker.client.main.activity.InventoryActivity.InventoryViewI;
import com.biker.client.main.activity.MainAsyncActivityMapper;
import com.biker.client.main.activity.MainAsyncActivityMapper.MainActivityFactory;
import com.biker.client.main.activity.ProfileActivity.ProfileViewI;
import com.biker.client.main.activity.ShopSettingsActivity.ShopSettingsViewI;
import com.biker.client.main.ioc.provider.MainPlaceHistoryHandlerProvider;
import com.biker.client.main.place.MainPlaceHistoryMapper;
import com.biker.client.main.view.AssociatesView;
import com.biker.client.main.view.InventoryView;
import com.biker.client.main.view.ProfileView;
import com.biker.client.main.view.ShopSettingsView;
import com.biker.client.main.widgets.MenuManager;
import com.biker.client.main.widgets.PersonalMenuPopup;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.inject.Singleton;

public class MainInjectorModule extends CommonInjectorModule {

  @Override
  protected void configureModule() {
    bind(MainPlaceHistoryMapper.class).in(Singleton.class);
    bind(PlaceHistoryHandler.class).toProvider(MainPlaceHistoryHandlerProvider.class).in(
        Singleton.class);
    bind(AsyncActivityMapper.class).to(MainAsyncActivityMapper.class).in(Singleton.class);

    bind(MenuManager.class).in(Singleton.class);
    bind(PersonalMenuPopup.class).in(Singleton.class);

    bind(InventoryViewI.class).to(InventoryView.class);
    bind(AssociatesViewI.class).to(AssociatesView.class);
    bind(ShopSettingsViewI.class).to(ShopSettingsView.class);
    bind(ProfileViewI.class).to(ProfileView.class);

    install(new GinFactoryModuleBuilder().build(MainActivityFactory.class));
    install(new GinFactoryModuleBuilder().build(MainWidgetFactory.class));
    install(new GinFactoryModuleBuilder().build(MiscFactory.class));
  }

}
