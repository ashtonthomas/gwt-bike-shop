package com.biker.client.main.place;

import com.biker.client.common.place.AppPlace;

public class AssociatesPlace extends AppPlace {

  public static final String PLACE_PREFIX = "associates";

  @Override
  public String getToken() {
    return new PlaceToken(PLACE_PREFIX).getToken();
  }

}
