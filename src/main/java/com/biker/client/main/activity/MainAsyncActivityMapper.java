package com.biker.client.main.activity;

import java.util.logging.Logger;

import com.biker.client.common.activity.ActivityCallbackHandler;
import com.biker.client.common.activity.AppActivity;
import com.biker.client.common.activity.AsyncActivityMapper;
import com.biker.client.common.place.AppPlace;
import com.biker.client.main.place.AssociatesPlace;
import com.biker.client.main.place.InventoryPlace;
import com.biker.client.main.place.ProfilePlace;
import com.biker.client.main.place.ShopSettingsPlace;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

@SuppressWarnings("rawtypes")
public class MainAsyncActivityMapper extends AsyncActivityMapper<AppPlace, AppActivity>{
	
	final static private Logger log = Logger.getLogger(MainAsyncActivityMapper.class.getName());

	public interface MainActivityFactory {
		InventoryActivity messagesActivity(@Assisted("place") InventoryPlace place);
		AssociatesActivity channelsActivity(@Assisted("place") AssociatesPlace place);
		ShopSettingsActivity accountActivity(@Assisted("place") ShopSettingsPlace place);
		ProfileActivity profileActivity(@Assisted("place") ProfilePlace place);
	}
	
	private final MainActivityFactory factory;
	
	@Inject
	public MainAsyncActivityMapper(MainActivityFactory factory){
		this.factory = factory;
	}
	
	@Override
	public void getActivity(final AppPlace place, 
			final ActivityCallbackHandler<AppActivity> activityCallbackHandler){
		
		if(place instanceof InventoryPlace){
			
			// This is the root place, so let's not create a split point for it
			activityCallbackHandler.onRecieveActivity(factory.messagesActivity((InventoryPlace) place), place);
			
		}else if(place instanceof AssociatesPlace){
			
			GWT.runAsync(AssociatesActivity.class,
					new RunAsyncCallback() {
						@Override
						public void onSuccess() {
							activityCallbackHandler.onRecieveActivity(
									factory.channelsActivity((AssociatesPlace) place), place);
						}
						@Override
						public void onFailure(Throwable reason) {
							Window.alert("Failed to load Channels [GWT Async Activity could not be loaded]");
						}
					});
			
		}else if(place instanceof ShopSettingsPlace){
			
			GWT.runAsync(ShopSettingsActivity.class,
					new RunAsyncCallback() {
						@Override
						public void onSuccess() {
							activityCallbackHandler.onRecieveActivity(
									factory.accountActivity((ShopSettingsPlace) place), place);
						}
						@Override
						public void onFailure(Throwable reason) {
							Window.alert("Failed to load Account [GWT Async Activity could not be loaded]");
						}
					});
			
		}else if(place instanceof ProfilePlace){
			
			GWT.runAsync(ProfileActivity.class,
					new RunAsyncCallback() {
						@Override
						public void onSuccess() {
							activityCallbackHandler.onRecieveActivity(
									factory.profileActivity((ProfilePlace) place), place);
						}
						@Override
						public void onFailure(Throwable reason) {
							Window.alert("Failed to load Profile [GWT Async Activity could not be loaded]");
						}
					});
			
		}
	
	}
	
}
