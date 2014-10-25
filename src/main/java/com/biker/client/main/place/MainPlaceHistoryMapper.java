package com.biker.client.main.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.biker.client.common.place.AppPlace;

public class MainPlaceHistoryMapper implements PlaceHistoryMapper {

	@Override
	public Place getPlace(String token) {
		
		if(token.startsWith(InventoryPlace.PLACE_PREFIX)){
			return new InventoryPlace();
		}else if(token.startsWith(AssociatesPlace.PLACE_PREFIX)){
			return new AssociatesPlace();
		}else if(token.startsWith(ShopSettingsPlace.PLACE_PREFIX)){
			return new ShopSettingsPlace();
		}else if(token.startsWith(ProfilePlace.PLACE_PREFIX)){
			return new ProfilePlace();
		}
		
		return null;
	}

	@Override
	public String getToken(Place place) {
		if(place instanceof AppPlace){
			return ((AppPlace)place).getToken();
		}
		return null; // Ignore unkown place
	}

}
