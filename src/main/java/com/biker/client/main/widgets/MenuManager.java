package com.biker.client.main.widgets;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;
import com.biker.client.common.fast.PressEvent;
import com.biker.client.common.fast.PressHandler;
import com.biker.client.common.view.Shell;
import com.biker.client.main.event.ShowMenuEvent;
import com.biker.client.main.event.ShowMenuEvent.ShowMenuEventType;
import com.biker.client.main.ioc.MiscFactory;

public class MenuManager implements PlaceChangeEvent.Handler {

  interface MyEventBinder extends EventBinder<MenuManager> {
  }

  private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

  final static private Logger log = Logger.getLogger(MenuManager.class.getName());

  private final Shell shell;
  private final EventBus eventBus;
  private final MiscFactory miscFactory;
  private final PersonalMenuPopup personalMenuPopup;
  private final PersonalMenu personalMenuSmall;
  private final MainMenu mainMenuLarge;
  private final MainMenu mainMenuSmall;

  private boolean isShowing;

  @Inject
  public MenuManager(Shell shell, EventBus eventBus, MiscFactory miscFactory,
      PersonalMenuPopup personalMenuPopup, PersonalMenu personalMenuSmall) {
    this.shell = shell;
    this.eventBus = eventBus;
    this.miscFactory = miscFactory;
    this.personalMenuPopup = personalMenuPopup;
    this.personalMenuSmall = personalMenuSmall;

    mainMenuLarge = miscFactory.mainMenu();
    mainMenuSmall = miscFactory.mainMenu();

    initialize();
  }

  private void initialize() {
    registerHandlers();

    shell.getMainMenuSide().setWidget(mainMenuLarge);

    shell.getHeaderInfoButton().addPressHandler(new PressHandler() {
      @Override
      public void onPress(PressEvent event) {
        eventBus.fireEvent(new ShowMenuEvent(ShowMenuEventType.PERSONAL));
      }
    });

    shell.getHeaderMenuButton().addPressHandler(new PressHandler() {
      @Override
      public void onPress(PressEvent event) {
        eventBus.fireEvent(new ShowMenuEvent(ShowMenuEventType.MAIN));
      }
    });

    hideSmallAndPopupMenus();

  }

  private void registerHandlers() {
    eventBus.addHandler(PlaceChangeEvent.TYPE, this);
    eventBinder.bindEventHandlers(this, eventBus);
  }

  @EventHandler
  public void onShowMenuEvent(ShowMenuEvent event) {

    if (isShowing || event.getMenuType() == ShowMenuEventType.HIDE) {
      hideSmallAndPopupMenus();
    } else {
      isShowing = true;
      shell.getMainMenuHeaderWrap().clear();
      shell.getMainMenuHeaderWrap().setVisible(true);

      if (event.getMenuType() == ShowMenuEventType.MAIN) {
        shell.getMainMenuHeaderWrap().add(mainMenuSmall);
      } else if (event.getMenuType() == ShowMenuEventType.PERSONAL) {
        personalMenuPopup.showRelativeTo(shell.getHeaderInfoButton());
        shell.getMainMenuHeaderWrap().add(personalMenuSmall);
      }
    }


  }

  @Override
  public void onPlaceChange(PlaceChangeEvent event) {
    hideSmallAndPopupMenus();
  }

  private void hideSmallAndPopupMenus() {
    isShowing = false;
    personalMenuPopup.hide(false);
    shell.getMainMenuHeaderWrap().clear();
    shell.getMainMenuHeaderWrap().setVisible(false);

  }


}
