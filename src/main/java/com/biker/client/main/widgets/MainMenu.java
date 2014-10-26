package com.biker.client.main.widgets;

import java.util.logging.Logger;

import com.biker.client.common.fast.FastPress;
import com.biker.client.common.fast.PressEvent;
import com.biker.client.common.fast.PressHandler;
import com.biker.client.common.resource.CoreClientBundle;
import com.biker.client.common.resource.CoreStyle;
import com.biker.client.main.place.AssociatesPlace;
import com.biker.client.main.place.InventoryPlace;
import com.biker.client.main.place.ShopSettingsPlace;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MainMenu extends Composite {

	final static private Logger log = Logger
			.getLogger(MainMenu.class.getName());

	private static MainMenuUiBinder uiBinder = GWT
			.create(MainMenuUiBinder.class);

	interface MainMenuUiBinder extends UiBinder<Widget, MainMenu> {
	}

	@UiField(provided=true)
	CoreStyle css = CoreClientBundle.INSTANCE.coreStyle();

	@UiField
	FastPress org;

	@UiField
	InlineLabel org_name;

	@UiField
	FastPress inventory;

	@UiField
	FastPress associates;

	@UiField
	FastPress shopSettings;

	private final PlaceController placeController;

	@Inject
	public MainMenu(PlaceController placeController) {
		initWidget(uiBinder.createAndBindUi(this));
		this.placeController = placeController;
		initialize();
	}

	private void initialize() {

		org_name.setText("Bike Shop #5");

		org.addPressHandler(new PressHandler() {
			@Override
			public void onPress(PressEvent event) {
				// We will remove this
				// Changing org context is out of scope for the MVP
				// Window.alert("Org Context Button Clicked");
			}
		});

		inventory.addPressHandler(new PressHandler() {
			@Override
			public void onPress(PressEvent event) {
				placeController.goTo(new InventoryPlace());
			}
		});

		associates.addPressHandler(new PressHandler() {
			@Override
			public void onPress(PressEvent event) {
				placeController.goTo(new AssociatesPlace());
			}
		});

		shopSettings.addPressHandler(new PressHandler() {
			@Override
			public void onPress(PressEvent event) {
				placeController.goTo(new ShopSettingsPlace());
			}
		});
	}

}
