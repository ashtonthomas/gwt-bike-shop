package com.biker.client.common.utility;

public class StatsEventLogger {
  public static native void logEvent(String app, String subSystem, String eventGroup,
      double millis, String type) /*-{
                                  $wnd.__gwtStatsEvent({
                                  'app' : app,
                                  'subSystem' : subSystem,
                                  'evtGroup' : eventGroup,
                                  'millis' : millis,
                                  'type' : type
                                  });
                                  }-*/;
}
