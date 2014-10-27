package com.biker.client.main.activity;

import com.biker.client.common.activity.AppActivity;
import com.biker.client.main.ioc.MainWidgetFactory;
import com.biker.client.main.place.InventoryPlace;
import com.biker.client.main.widgets.BikeWidget;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

public class InventoryActivity extends AppActivity<InventoryPlace>{
	
	public interface InventoryViewI extends IsWidget {
		public void setPresenter(InventoryActivity presenter);
		public void addBike(IsWidget bikeWidget);
	}
	
	private final InventoryViewI view;
	private final PlaceController placeController;
	private EventBus eventBus;
	private final MainWidgetFactory widgetFactory;

	@AssistedInject
	public InventoryActivity(@Assisted("place") InventoryPlace place,
			InventoryViewI view, PlaceController placeController, MainWidgetFactory widgetFactory) {
		super(place);
		this.view = view;
		view.setPresenter(this);
		this.placeController = placeController;
		this.widgetFactory = widgetFactory;
	}

	@Override
	public void doStart(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		this.eventBus = eventBus;
		refresh();
	}
	
	private void refresh(){
		for(int i=0;i<10;i++){
			BikeWidget bike = widgetFactory.bikeWidget(i);
			view.addBike(bike);
		}
	}

}
