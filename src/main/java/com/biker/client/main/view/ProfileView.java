package com.biker.client.main.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.biker.client.main.activity.ProfileActivity;
import com.biker.client.main.activity.ProfileActivity.ProfileViewI;

public class ProfileView extends Composite implements ProfileViewI {

	private static ProfileViewUiBinder uiBinder = GWT
			.create(ProfileViewUiBinder.class);

	interface ProfileViewUiBinder extends UiBinder<Widget, ProfileView> {
	}

	private ProfileActivity presenter;
	
	public ProfileView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(ProfileActivity presenter) {
		this.presenter = presenter;
	}

}
