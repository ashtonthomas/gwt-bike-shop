package com.biker.client.main.view;

import com.biker.client.main.activity.ShopSettingsActivity;
import com.biker.client.main.activity.ShopSettingsActivity.ShopSettingsViewI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ShopSettingsView extends Composite implements ShopSettingsViewI {

	private static ShopSettingsViewUiBinder uiBinder = GWT
			.create(ShopSettingsViewUiBinder.class);

	interface ShopSettingsViewUiBinder extends UiBinder<Widget, ShopSettingsView> {
	}

	private ShopSettingsActivity presenter;
	
	public ShopSettingsView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(ShopSettingsActivity presenter) {
		this.presenter = presenter;
	}

}
