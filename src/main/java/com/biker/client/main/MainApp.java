package com.biker.client.main;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.inject.Inject;
import com.biker.client.common.activity.AsyncActivityManager;
import com.biker.client.common.resource.CommonResource;
import com.biker.client.common.view.Shell;
import com.biker.client.main.widgets.MenuManager;

public class MainApp {
	
	final static private Logger log = Logger.getLogger(MainApp.class.getName());

	private final Shell shell;
	private final AsyncActivityManager activityManager;
	private final PlaceHistoryHandler historyHandler;
	
	// Even though we don't use these dependencies
	// We need to inject them so they get wired up via gin
	// I wouldn't mind looking into a better way to do this
	private final PlaceController PlaceController;
	private final EventBus eventBus;
	private final MenuManager menuManager;
	
	@Inject
	public MainApp(Shell shell, AsyncActivityManager activityManager, 
			PlaceHistoryHandler historyHandler,
			PlaceController placeController, EventBus eventBus,
			MenuManager menuManager){
		this.shell = shell;
		this.activityManager = activityManager;
		this.historyHandler = historyHandler;
		this.PlaceController = placeController;
		this.eventBus = eventBus;
		this.menuManager = menuManager;
	}
	
	public void run(){
		CommonResource.injectStyle();
		
		activityManager.setDisplay(shell.getContent());
		historyHandler.handleCurrentHistory();
		
	}
	
}
