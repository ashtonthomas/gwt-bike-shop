package com.biker.server.request.dispatch;

import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.impl.AbstractSerializationStream;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GwtRpcDispatcher extends RemoteServiceServlet implements Controller,
    ServletContextAware {

  // TODO need to fix the log levels to match original project for WARN etc
  final private Logger log = Logger.getLogger(GwtRpcDispatcher.class.getName());


  private RemoteService remoteService;
  private ServletContext servletContext;

  public void setRemoteService(RemoteService remoteService) {
    this.remoteService = remoteService;
  }

  @Override
  protected void doUnexpectedFailure(Throwable e) {
    // Log the exception with slf4j to take advantage of the MDC, which has the user email
    // and host address set.
    log.info("Unexpected failure in RPC call:" + "--" + e);

    // This will also log the error to the servlet context. This is redundant, but I like
    // the rest of the impl, i.e. resetting the response and sending a 500 to the client.
    super.doUnexpectedFailure(e);
  }

  @Override
  public String processCall(String payload) throws SerializationException {

    try {

      GwtRpcDispatcherUtil.setThreadLocals(getThreadLocalRequest(), getThreadLocalResponse());
      // RPCRequest rpcRequest = RPC.decodeRequest(payload, this.remoteService.getClass());
      RPCRequest rpcRequest = RPC.decodeRequest(payload, this.remoteService.getClass(), this);

      log.info("Processing RPC call={}.{}()." + "--"
          + rpcRequest.getMethod().getDeclaringClass().getSimpleName() + "--"
          + rpcRequest.getMethod().getName());

      // return RPC.invokeAndEncodeResponse(this.remoteService, rpcRequest.getMethod(),
      // rpcRequest.getParameters());
      return RPC.invokeAndEncodeResponse(this.remoteService, rpcRequest.getMethod(),
          rpcRequest.getParameters(), rpcRequest.getSerializationPolicy(),
          AbstractSerializationStream.FLAG_ELIDE_TYPE_NAMES);
    } catch (IncompatibleRemoteServiceException e) {
      return RPC.encodeResponseForFailure(null, e);
    }
  }

  @Override
  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    doPost(request, response);
    return null; // response handled by GWT RPC over XmlHttpRequest
  }

  @Override
  public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  @Override
  public ServletContext getServletContext() {
    return servletContext;
  }

}
