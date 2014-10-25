package com.biker.client.main.place;

import com.biker.client.common.place.AppPlace;

public class ShopSettingsPlace extends AppPlace {
	public static final String PLACE_PREFIX = "shopSettings";

	@Override
	public String getToken() {
		return new PlaceToken(PLACE_PREFIX).getToken();
	}
	
}
