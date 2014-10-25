package com.biker.client.main.view;

import com.biker.client.main.activity.InventoryActivity;
import com.biker.client.main.activity.InventoryActivity.InventoryViewI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class InventoryView extends Composite implements InventoryViewI {

	private static InventoryViewUiBinder uiBinder = GWT
			.create(InventoryViewUiBinder.class);

	interface InventoryViewUiBinder extends UiBinder<Widget, InventoryView> {
	}
	
	private InventoryActivity presenter;

	public InventoryView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(InventoryActivity presenter) {
		this.presenter = presenter;
	}

}
