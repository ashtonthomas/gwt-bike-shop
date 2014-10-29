package com.biker.client.main.widgets;

import com.biker.client.common.event.LogoutEvent;
import com.biker.client.common.fast.FastPress;
import com.biker.client.common.fast.PressEvent;
import com.biker.client.common.fast.PressHandler;
import com.biker.client.common.resource.CoreClientBundle;
import com.biker.client.common.resource.CoreStyle;
import com.biker.client.common.utility.Butter;
import com.biker.client.main.event.ShowMenuEvent;
import com.biker.client.main.event.ShowMenuEvent.ShowMenuEventType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class PersonalMenu extends Composite {

  private static PersonalMenuUiBinder uiBinder = GWT.create(PersonalMenuUiBinder.class);

  interface PersonalMenuUiBinder extends UiBinder<Widget, PersonalMenu> {
  }

  @UiField(provided = true)
  CoreStyle css = CoreClientBundle.INSTANCE.coreStyle();

  @UiField
  FastPress settings;

  @UiField
  FastPress logout;

  private final EventBus eventBus;

  @Inject
  public PersonalMenu(EventBus eventBus) {
    initWidget(uiBinder.createAndBindUi(this));
    this.eventBus = eventBus;
    initialize();
  }

  private void initialize() {
    logout.addPressHandler(new PressHandler() {
      @Override
      public void onPress(PressEvent event) {
        eventBus.fireEvent(new LogoutEvent());
        eventBus.fireEvent(new ShowMenuEvent(ShowMenuEventType.HIDE));
      }
    });
  }

}
