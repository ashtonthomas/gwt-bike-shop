package com.biker.client.main.ioc.provider;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.biker.client.main.place.MainPlaceHistoryMapper;
import com.biker.client.main.place.InventoryPlace;

public class MainPlaceHistoryHandlerProvider implements Provider<PlaceHistoryHandler>{
	private final MainPlaceHistoryMapper historyMapper;
	private final EventBus eventBus;
	private final PlaceController placeController;
	
	@Inject
	public MainPlaceHistoryHandlerProvider(MainPlaceHistoryMapper historyMapper,
			EventBus eventBus, PlaceController placeController){
		this.historyMapper = historyMapper;
		this.eventBus = eventBus;
		this.placeController = placeController;
	}
	
	@Override
	public PlaceHistoryHandler get() {
		
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		
		InventoryPlace defaultPlace = new InventoryPlace();
		
		historyHandler.register(placeController, eventBus, defaultPlace);
		
		return historyHandler;
	}
}
