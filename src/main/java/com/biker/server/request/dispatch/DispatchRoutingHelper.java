package com.biker.server.request.dispatch;

import javax.servlet.http.HttpServletRequest;

import com.biker.server.request.service.XsrfTokenUtil;
import com.biker.shared.constants.SharedConstants;
import com.biker.shared.dto.DataConfigDto;

public class DispatchRoutingHelper {
	
	public static int getDestinationContext(HttpServletRequest request){
		String url = request.getRequestURL().toString();
			
		if(url.contains("/app")) {
			return SharedConstants.DISPATCH_CONTEXT_MAIN;
		}else if(url.contains("/admin")){
			return SharedConstants.DISPATCH_CONTEXT_ADMINISTRATOR;
		}else {
			return SharedConstants.DISPATCH_CONTEXT_MAIN;
			// throw new RuntimeException("Encountered an invalid destination context in main dispatch. The destination scope did not match any of the valid scopes: "+ url);
		}
			
	}


	
	/**
	 * Determine if we are requesting a 'registration' or any type: register/
	 * register-trainer/ register-etc/
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isRegistration(String url) {
		return url != null && url.contains("/register");
	}
	
	/**
	 * Helper method to build the DataConfigDto for ALL GWT-based modules.
	 * 
	 * Register, on-boarding, main all use the dataConfig.
	 * 
	 * 
	 * If we have module specific data, we will need to revisit this structure,
	 * etc.
	 * 
	 * @param request
	 * @return
	 */
	public static DataConfigDto getDataConfig(HttpServletRequest request) {
		DataConfigDto dataConfig = new DataConfigDto();

		dataConfig.setXsrfToken(getXsrf(request));

		return dataConfig;
	}
	
	/**
	 * Helper method to create the XSRF Token for this session
	 * 
	 * @param request
	 * @return
	 */
	public static String getXsrf(HttpServletRequest request) {
		// Create the xsrf token to be used
		return XsrfTokenUtil.getToken(request.getSession().getId());
	}
}
