package com.biker.client.main.place;

import com.biker.client.common.place.AppPlace;

public class ProfilePlace extends AppPlace {
	
	public static final String PLACE_PREFIX = "profile";
	
	public ProfilePlace(){
		// no-op
	}
	
	@Override
	public String getToken() {
		return new PlaceToken(PLACE_PREFIX).getToken();
	}

}
