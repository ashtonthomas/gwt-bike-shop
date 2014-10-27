package com.biker.client.common.view;

import com.biker.client.common.fast.FastPress;
import com.biker.client.common.resource.CoreClientBundle;
import com.biker.client.common.resource.CoreStyle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class Shell extends Composite {

	private static ShellUiBinder uiBinder = GWT
			.create(ShellUiBinder.class);

	interface ShellUiBinder extends UiBinder<Widget, Shell> {
	}
	
	interface ShellStyle extends CssResource {
		
	}
	
	@UiField
	ShellStyle style;
	
	@UiField(provided=true)
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

	public Shell() {
		initWidget(uiBinder.createAndBindUi(this));
		RootPanel.get().add(this);
	}
	
	public SimplePanel getContent(){
		return content;
	}
	
	public SimplePanel getMainMenuSide(){
		return main_menu_side;
	}
	
	public FlowPanel getMainMenuHeaderWrap(){
		return main_menu_header_wrap;
	}
	
	public FastPress getHeaderInfoButton(){
		return header_info_button;
	}
	
	public FastPress getHeaderMenuButton(){
		return header_menu_button;
	}

}
