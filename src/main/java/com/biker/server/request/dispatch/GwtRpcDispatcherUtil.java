package com.biker.server.request.dispatch;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GwtRpcDispatcherUtil {
  private static final ThreadLocal<HttpServletRequest> perThreadRequest =
      new ThreadLocal<HttpServletRequest>();
  private static final ThreadLocal<HttpServletResponse> perThreadResponse =
      new ThreadLocal<HttpServletResponse>();
  private static final ThreadLocal<ServletContext> perThreadContext =
      new ThreadLocal<ServletContext>();

  /**
   * Sets the <code>HttpServletRequest,HttpServletResponse</code> objects for the current call. It
   * is stored thread-locally so that simultaneous invocations can have different request objects.
   */
  protected static void setThreadLocals(HttpServletRequest request, HttpServletResponse response) {
    setThreadLocalRequest(request);
    setThreadLocalResponse(response);
    // setThreadLocalContext(context);
  }

  /**
   * Sets the <code>HttpServletRequest</code> object for the current call. It is stored
   * thread-locally so that simultaneous invocations can have different request objects.
   */
  protected static void setThreadLocalRequest(HttpServletRequest request) {
    perThreadRequest.set(request);
  }

  /**
   * Sets the <code>HttpServletResponse</code> object for the current call. It is stored
   * thread-locally so that simultaneous invocations can have different response objects.
   */
  protected static void setThreadLocalResponse(HttpServletResponse response) {
    perThreadResponse.set(response);
  }

  // /**
  // * Sets the <code>ServletContext</code> object for the current call. It is
  // * stored thread-locally so that simultaneous invocations can have different
  // * request objects.
  // */
  // protected static void setThreadLocalContext(ServletContext context) {
  // perThreadContext.set(context);
  // }

  /**
   * Gets the <code>HttpServletRequest</code> object for the current call. It is stored
   * thread-locally so that simultaneous invocations can have different request objects.
   */
  public static final HttpServletRequest getThreadLocalRequest() {
    return perThreadRequest.get();
  }

  /**
   * Gets the <code>HttpServletResponse</code> object for the current call. It is stored
   * thread-locally so that simultaneous invocations can have different response objects.
   */
  public static final HttpServletResponse getThreadLocalResponse() {
    return perThreadResponse.get();
  }

  // /**
  // * Gets the <code>ServletContext</code> object for the current call. It is
  // * stored thread-locally so that simultaneous invocations can have different
  // * context objects.
  // */
  // public static final ServletContext getThreadLocalContext() {
  // return perThreadContext.get();
  // }

  /**
   * Clears out the ThreadLocals. This is a convienence method.
   */
  public static final void clearThreadLocals() {
    perThreadRequest.set(null);
    perThreadResponse.set(null);
    // perThreadContext.set(null);
  }

  /**
   * Gets the <code>HttpSession</code> object for the current call. It is stored thread-locally so
   * that simultaneous invocations can have different context objects. This is a convenience method,
   * it's equivalent to getThreadLocalRequest().getSession() with null checking.
   * 
   * @return HttpSession
   */
  public static final HttpSession getThreadLocalSession() {
    HttpSession session = null;
    HttpServletRequest request = perThreadRequest.get();
    if (request != null) {
      session = request.getSession();
    }
    return session;
  }
}
