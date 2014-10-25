package com.biker.client.common.utility;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Command;

public class Butter {

	public static void go(final Command command){
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				command.execute();
			}
		});
	}
	
}
