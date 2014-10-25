package com.biker.client.common.place;

import com.google.gwt.place.shared.Place;

public abstract class AppPlace extends Place {
	
	public abstract String getToken();
	
	public static Integer getInt(String token, String key) {
		return Integer.valueOf(getString(token, key));
	}

	public static String getString(String token, String key) {
		if (token.contains(key)) {
			return token.substring(token.indexOf(key) + key.length()+1).split("&")[0];
		} else {
			return null;
		}
	}
	
	public class PlaceToken {
		private String token;
		boolean addQ = false;

		public PlaceToken(String prefix) {
			token = prefix;
		}

		public PlaceToken add(String key, String value) {
			
			if(!addQ){
				token += "?";
			}
			
			if (!token.endsWith("?")) {
				token += "&";
			}
			
			token += key + "=" + value;

			return this;
		}

		public PlaceToken add(String key, Integer value) {
			
			return add(key, value + "");
		}

		public String getToken() {
			return token;
		}
	}

}
