package com.biker.server.request.dispatch;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class JspAssembler {
  public static ModelAndView assembleHostJsp(JspConfig config) {

    ModelAndView mav = new ModelAndView(config.getJspView());

    mav.addObject("gwtModule", config.getGwtModule());
    mav.addObject("appTitle", config.getAppTitle());
    mav.addObject("isMobile", config.isMobile());

    return mav;

  }
}
