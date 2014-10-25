package com.biker.client.main.view;

import com.biker.client.main.activity.AssociatesActivity;
import com.biker.client.main.activity.AssociatesActivity.AssociatesViewI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AssociatesView extends Composite implements AssociatesViewI {

	private static AssociatesViewUiBinder uiBinder = GWT
			.create(AssociatesViewUiBinder.class);

	interface AssociatesViewUiBinder extends UiBinder<Widget, AssociatesView> {
	}
	
	private AssociatesActivity presenter;

	public AssociatesView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(AssociatesActivity presenter) {
		this.presenter = presenter;
	}

}
