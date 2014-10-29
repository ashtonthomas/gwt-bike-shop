package com.biker.client.common.activity;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.ResettableEventBus;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceChangeRequestEvent;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.biker.client.common.place.AppPlace;

public class AsyncActivityManager implements PlaceChangeEvent.Handler,
    PlaceChangeRequestEvent.Handler {

  final static private Logger log = Logger.getLogger(AsyncActivityManager.class.getName());

  /**
   * Wraps our real display to prevent an Activity from taking it over if it is not the
   * currentActivity.
   */
  private class ProtectedDisplay implements AcceptsOneWidget {

    private final AppActivity activity;

    ProtectedDisplay(AppActivity activity) {
      this.activity = activity;
    }

    @Override
    public void setWidget(IsWidget view) {
      if (this.activity == AsyncActivityManager.this.currentActivity) {
        showWidget(view);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final AppActivity NULL_ACTIVITY = new AppActivity(null) {
    @Override
    public void doStart(AcceptsOneWidget panel, EventBus eventBus) {

    }
  };

  private final AsyncActivityMapper mapper;

  private final EventBus eventBus;

  /*
   * Note that we use the legacy class from com.google.gwt.event.shared, because we can't change the
   * Activity interface.
   */
  private final ResettableEventBus stopperedEventBus;

  private AppActivity currentActivity = NULL_ACTIVITY;

  private AcceptsOneWidget display;

  private AppPlace currentPlace = null;

  private HandlerRegistration handlerRegistration;

  public AsyncActivityManager(EventBus eventBus, AsyncActivityMapper mapper) {
    this.mapper = mapper;
    this.eventBus = eventBus;
    this.stopperedEventBus = new ResettableEventBus(eventBus);
  }

  @Override
  public void onPlaceChange(PlaceChangeEvent event) {

    // Only Handle MainPlace PCE's
    if (event.getNewPlace() instanceof AppPlace) {

      // The most recent PCE should determine the current activity
      // after any deferred async loading
      this.currentPlace = (AppPlace) event.getNewPlace();

      // Could even possibly force this to be an "AppPlace"
      // And require the implementation of getAsynLoadingMessage()
      // To display while any code is downloaded

      // TODO - this is where we need to show the ActivityLoading*

      // TODO get a more specific message from place
      showActivityLoading("loading...");

      // Launch the Activity
      getNextActivity(event, new ActivityCallbackHandler<AppActivity>() {

        @Override
        public void onRecieveActivity(AppActivity nextActivity, AppPlace linkedPlace) {

          Throwable caughtOnStop = null;
          Throwable caughtOnCancel = null;
          Throwable caughtOnStart = null;

          if (nextActivity == null) {
            nextActivity = NULL_ACTIVITY;
          }

          if (currentActivity.equals(nextActivity)) {
            return;
          }

          if (currentPlace != linkedPlace) {
            // The place changed again before the new current
            // activity
            // Downloaded/Started/Set its display widget
            caughtOnCancel = tryStopOrCancel(false);
            currentActivity = NULL_ACTIVITY;

          } else if (!currentActivity.equals(NULL_ACTIVITY)) {
            showWidget(null);

            /*
             * Kill off the activity's handlers, so it doesn't have to worry about them accidentally
             * firing as a side effect of its tear down
             */
            stopperedEventBus.removeHandlers();
            caughtOnStop = tryStopOrCancel(true);
          }

          currentActivity = nextActivity;

          if (currentActivity.equals(NULL_ACTIVITY)) {

            showWidget(null);

          } else {

            // PotentialButton - user "PotentialButton" to mark
            // areas of the app we can
            // think about putting a Butter.go() if it increases
            // performance in browser
            caughtOnStart = tryStart();

          }

          if (caughtOnStart != null || caughtOnCancel != null || caughtOnStop != null) {
            Set<Throwable> causes = new LinkedHashSet<Throwable>();
            if (caughtOnStop != null) {
              causes.add(caughtOnStop);
            }
            if (caughtOnCancel != null) {
              causes.add(caughtOnCancel);
            }
            if (caughtOnStart != null) {
              causes.add(caughtOnStart);
            }

            throw new UmbrellaException(causes);
          }

        }
      });

    }
  }

  /**
   * Reject the place change if the current activity is not willing to stop.
   * 
   * @see com.google.gwt.place.shared.PlaceChangeRequestEvent.Handler#onPlaceChangeRequest(PlaceChangeRequestEvent)
   */
  @Override
  public void onPlaceChangeRequest(PlaceChangeRequestEvent event) {
    event.setWarning(currentActivity.mayStop());
  }

  /**
   * Sets the display for the receiver, and has the side effect of starting or stopping its
   * monitoring the event bus for place change events.
   * <p>
   * If you are disposing of an ActivityManager, it is important to call setDisplay(null) to get it
   * to deregister from the event bus, so that it can be garbage collected.
   * 
   * @param display an instance of AcceptsOneWidget
   */
  public void setDisplay(AcceptsOneWidget display) {
    boolean wasActive = (null != this.display);
    boolean willBeActive = (null != display);
    this.display = display;
    if (wasActive != willBeActive) {
      updateHandlers(willBeActive);
    }
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  private void getNextActivity(PlaceChangeEvent event,
      ActivityCallbackHandler<AppActivity> activityCallbackHandler) {
    if (display == null) {
      /*
       * Display may have been nulled during PlaceChangeEvent dispatch. Don't bother the mapper,
       * just return a null to ensure we shut down the current activity
       */
      return;
    }
    mapper.getActivity((AppPlace) event.getNewPlace(), activityCallbackHandler);
  }

  private void showWidget(IsWidget view) {
    if (display != null) {
      display.setWidget(view);
    }
  }

  private Throwable tryStart() {
    Throwable caughtOnStart = null;
    try {
      /*
       * Wrap the actual display with a per-call instance that protects the display from canceled or
       * stopped activities, and which maintains our startingNext state.
       */
      log.info("tryStart: " + currentActivity);
      currentActivity.doStart(new ProtectedDisplay(currentActivity), stopperedEventBus);

    } catch (Throwable t) {
      caughtOnStart = t;
    }
    return caughtOnStart;
  }

  private Throwable tryStopOrCancel(boolean stop) {
    Throwable caughtOnStop = null;
    try {
      if (stop) {
        currentActivity.onStop();
      } else {
        currentActivity.onCancel();
      }
    } catch (Throwable t) {
      caughtOnStop = t;
    } finally {
      /*
       * Kill off the handlers again in case it was naughty and added new ones during onstop or
       * oncancel
       */
      stopperedEventBus.removeHandlers();
    }
    return caughtOnStop;
  }

  private void updateHandlers(boolean activate) {
    if (activate) {
      final HandlerRegistration placeReg = eventBus.addHandler(PlaceChangeEvent.TYPE, this);
      final HandlerRegistration placeRequestReg =
          eventBus.addHandler(PlaceChangeRequestEvent.TYPE, this);

      this.handlerRegistration = new HandlerRegistration() {
        @Override
        public void removeHandler() {
          placeReg.removeHandler();
          placeRequestReg.removeHandler();
        }
      };
    } else {
      if (handlerRegistration != null) {
        handlerRegistration.removeHandler();
        handlerRegistration = null;
      }
    }
  }

  private void showActivityLoading(String message) {
    // Go ahead and clear any loading control before starting again
    // On Hold for now
    log.warning("showActivityLoading does nothing right now");
  }

}
