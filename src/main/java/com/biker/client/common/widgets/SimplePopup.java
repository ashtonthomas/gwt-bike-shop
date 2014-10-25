package com.biker.client.common.widgets;

import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PopupPanel;
import com.biker.client.common.resource.CoreClientBundle;
import com.biker.client.common.resource.CoreStyle;
import com.biker.client.common.utility.Butter;

public class SimplePopup extends PopupPanel {
	
	private CoreStyle style = CoreClientBundle.INSTANCE.coreStyle();
	private boolean disableScroll;
	private boolean hasBeenCentered = false;
	private int zIndex = 101;
	private int scrollTop;
	
	public SimplePopup(boolean autoHide, boolean disableScroll){
		super(autoHide);
		this.disableScroll = disableScroll;
		setup();
	}
	
	public void setZIndex(int zIndex){
		this.zIndex = zIndex;
		getElement().getStyle().setZIndex(zIndex);
		getGlassElement().getStyle().setZIndex(zIndex);
	}
	
	public int getZIndex(){
		return zIndex;
	}
	
	private void setup(){
		// Must set glassEnabled prior to setZIndex
		// Othewise, it will try to set the non-existent glassElement
		setGlassEnabled(true);
		// Call setZIndex AFTER setGlassEnabled(true)
		setZIndex(zIndex);
		
		getGlassElement().removeClassName("gwt-PopupPanelGlass");
		getGlassElement().addClassName(style.simplePopupGlass());
		
		addCloseHandler(new CloseHandler<PopupPanel>() {
			@Override
			public void onClose(CloseEvent<PopupPanel> event) {
				if (disableScroll) {
					Window.enableScrolling(true);
					
					// Scroll disable stopped 
					// messing up position
					// Might need to check this with other browsers
					// However, if we have to go back to this, we will
					// hit other problems with layered popups. 
					// Having a multiple SimplePopups with scrollDisabled
					// we may get incorrect scroll positions and mess up on the
					// close handler -> reset scroll position
					// Window.scrollTo(0, scrollTop);
				}
			}
		});
		
		// TODO Need to setup a Scroll Panel as the main Content 
		// And on show/center/etc, make sure our height is correctly set,
		// Given the current viewport
		
	}

	
	private void grabScroll(){
		// TODO set up scroll position handling
		scrollTop = Window.getScrollTop();
		Window.enableScrolling(false);
	}
	
	
	public void turnLight(){
		getGlassElement().removeClassName(style.simplePopupGlass());
		getGlassElement().addClassName(style.pseudoPopupGlass());
	}
	
	public void ensureCenter(){
		
		grabScroll();
		
		
		if(!hasBeenCentered){
			Butter.go(new Command() {
				@Override
				public void execute() {
					setPopupPositionAndShow(new PositionCallback() {
						@Override
						public void setPosition(int offsetWidth, int offsetHeight) {
							
							int h = Window.getClientHeight();
							int w = Window.getClientWidth();
							
							int left = 0;
							int top = 0;
							
							int hd = h - offsetHeight;
							
							if(hd > 0){
								
								if((hd - 200) < 0){
									top = hd / 2;
								}else{
									top = 100;
								}
								
							}else if(hd < 0) {
								top = 0;
							}
							
							int wd = (w / 2) - (offsetWidth / 2);
							
							if(wd < 0){
								left = 0;
							}else {
								left = wd;
							}
							
							
							
							setPopupPosition(left, Window.getScrollTop() + top);
							
						}
					});
				}
			});
		}
	}


}
