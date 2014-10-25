package com.biker.server.request.dispatch;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.biker.server.request.dispatch.controllers.DispatchAdminController;
import com.biker.server.request.dispatch.controllers.DispatchMainController;
import com.biker.server.request.dispatch.controllers.DispatchRegisterController;
import com.biker.shared.constants.SharedConstants;

@Component
public class DefaultDispatchController implements Controller {

	@Autowired
	private DispatchRegisterController dispatchRegistration;
	
	@Autowired
	private DispatchMainController dispatchMain;
	
	@Autowired
	private DispatchAdminController dispatchAdmin;
	
	Logger log = Logger.getLogger("my.logger");
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		log.log(Level.ALL, "handleRequest: "+request);
		
		// Set Thread Locals
		GwtRpcDispatcherUtil.setThreadLocals(request, response);

		String url = request.getRequestURL().toString();

		// -- Before doing anything, we verify HTTPS

		boolean usingHttps = url != null && url.contains("https://");

		boolean isDevMode = request.getQueryString() != null
				&& request.getQueryString().contains(
						SharedConstants.DEV_MODE_QUERY_STRING);

		boolean isByPass = request.getQueryString() != null
				&& request.getQueryString().contains("nohttpstest");

		boolean allowRedirect = Boolean.parseBoolean(System.getProperty(
				"allowRedirect", "" + true));

		// Before doing anything, verify we are on https
		if (!usingHttps && !isDevMode && !isByPass && allowRedirect) {
			// TODO log.info("Encountered a nonDevMode NOT using HTTPS - redirect");
			response.sendRedirect(SharedConstants.MAIN_APP_URL);
			return null; // Handle with redirect
		}

		// -- Now that we verified HTTPS, we can continue with the actual
		// dispatch process

		if (DispatchRoutingHelper.isRegistration(url)) {
			return dispatchRegistration.handleRequest(request, response);
		} else {
			// Determine destination context and dispatch
			switch (DispatchRoutingHelper.getDestinationContext(request)) {
			
			case SharedConstants.DISPATCH_CONTEXT_MAIN:		
				return dispatchMain.handleRequest(request, response);
				
			case SharedConstants.DISPATCH_CONTEXT_ADMINISTRATOR:
				return dispatchAdmin.handleRequest(request, response);
				
			}
		}

		return null; // TODO - handle with error page?

	}

}
