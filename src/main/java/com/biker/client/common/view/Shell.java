package com.biker.client.common.view;

import com.biker.client.common.event.LogoutEvent;
import com.biker.client.common.fast.FastPress;
import com.biker.client.common.resource.CoreClientBundle;
import com.biker.client.common.resource.CoreStyle;
import com.biker.client.common.utility.Butter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

public class Shell extends Composite {

  private static ShellUiBinder uiBinder = GWT.create(ShellUiBinder.class);

  interface ShellUiBinder extends UiBinder<Widget, Shell> {
  }
  interface ShellEventBinder extends EventBinder<Shell> {
  }

  @UiField(provided = true)
  CoreStyle css = CoreClientBundle.INSTANCE.coreStyle();

  @UiField
  SimplePanel main_menu_side;

  @UiField
  FlowPanel main_menu_header_wrap;

  @UiField
  FastPress header_menu_button;

  @UiField
  FastPress header_info_button;

  @UiField
  SimplePanel content;

  private final EventBus eventBus;
  private final ShellEventBinder eventBinder = GWT.create(ShellEventBinder.class);

  @Inject
  public Shell(EventBus eventBus) {
    this.eventBus = eventBus;
    initialize();
  }

  private void initialize() {
    initWidget(uiBinder.createAndBindUi(this));
    RootPanel.get().add(this);
    eventBinder.bindEventHandlers(this, eventBus);
  }

  @EventHandler
  public void onLogoutEvent(LogoutEvent event) {
    Butter.go(new Command() {
      @Override
      public void execute() {
        // deferring this call will actually allow the
        // PersonalMenuPopup to hide
        Window.alert("You may never leave....");
      }
    });
  }

  public SimplePanel getContent() {
    return content;
  }

  public SimplePanel getMainMenuSide() {
    return main_menu_side;
  }

  public FlowPanel getMainMenuHeaderWrap() {
    return main_menu_header_wrap;
  }

  public FastPress getHeaderInfoButton() {
    return header_info_button;
  }

  public FastPress getHeaderMenuButton() {
    return header_menu_button;
  }



}
