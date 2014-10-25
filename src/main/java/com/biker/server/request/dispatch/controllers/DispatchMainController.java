package com.biker.server.request.dispatch.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.biker.server.request.dispatch.DispatchDataHelper;
import com.biker.server.request.dispatch.DispatchRoutingHelper;
import com.biker.server.request.dispatch.JspAssembler;
import com.biker.server.request.dispatch.JspConfig;
import com.biker.shared.constants.SharedConstants;
import com.biker.shared.dto.DataConfigDto;

@Component
public class DispatchMainController implements Controller{
	
	@Autowired
	private DispatchDataHelper dispatchDataHelper;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return assembleMain(request);
		
	}
	
	private ModelAndView assembleMain(HttpServletRequest request){
		
		JspConfig config = new JspConfig();
		
		DataConfigDto dataConfig = DispatchRoutingHelper.getDataConfig(request);
		
		config.setAppTitle("Biker - Bike Shop Management");
		config.setJspView(SharedConstants.JSP_VIEW_MAIN);
		config.setGwtModule(SharedConstants.GWT_MODULE_MAIN);
		
		return JspAssembler.assembleHostJsp(config);
	}

}
