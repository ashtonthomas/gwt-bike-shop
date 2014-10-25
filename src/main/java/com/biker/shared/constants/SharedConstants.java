package com.biker.shared.constants;

public interface SharedConstants {

	// Destination/Module Contexts
	final static public int DISPATCH_CONTEXT_ADMINISTRATOR 	= 1000;
	final static public int DISPATCH_CONTEXT_MAIN 		= 1001;
	

	// Other constants used in the Session to load the host page
	final static public String DEV_MODE_QUERY_STRING	= "gwt.codesvr=127.0.0.1:9997";
	
	// Obviously we need to change this... TODO
	final static public String MAIN_APP_URL = "http://google.com"; 
	
	public static final String JSP_VIEW_LOGIN		= "hostLogin";
	public static final String JSP_VIEW_REGISTER	= "hostRegister";
	public static final String JSP_VIEW_MAIN		= "hostMain";
	public static final String JSP_VIEW_MOBILE		= "hostMobile";
	
	public static final String GWT_MODULE_MAIN 		= "../main/main.nocache.js";
	public static final String GWT_MODULE_ADMIN 		= "../admin/admin.nocache.js";
	public static final String GWT_MODULE_LOGIN 			= "../login/login.nocache.js";
	public static final String GWT_MODULE_REGISTER 			= "../register/register.nocache.js";
	

	
	// Server Constants
	final static public String CURRENT_LOGIN_ATTEMPTS 		= "current_login_attempts";
	final static public String CURRENT_USER 				= "current_user";
	
}
