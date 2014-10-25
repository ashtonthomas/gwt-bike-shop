package com.biker.client.main.widgets;

import com.biker.client.common.resource.CoreClientBundle;
import com.biker.client.common.resource.CoreStyle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class PersonalMenu extends Composite {

	private static PersonalMenuUiBinder uiBinder = GWT
			.create(PersonalMenuUiBinder.class);

	interface PersonalMenuUiBinder extends UiBinder<Widget, PersonalMenu> {
	}

	@UiField(provided=true)
	CoreStyle css = CoreClientBundle.INSTANCE.coreStyle();
	
	public PersonalMenu() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
