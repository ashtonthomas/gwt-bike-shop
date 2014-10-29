package com.biker.server.request.dispatch;

import com.biker.shared.dto.DataConfigDto;

/**
 * This class should represent every possible variable that host pages may expect.
 * 
 * This is helpful as it may be hard to determin all the variables expected and used by the
 * different JSP partials. This class is the only way to programmatically account for all the
 * details
 * 
 * @author ashton
 *
 */
public class JspConfig {
  private String jspView = "";
  private String gwtModule = "";
  private String appTitle = "";
  private boolean isMobile = false;

  private DataConfigDto dataConfig = null;

  public String getJspView() {
    return jspView;
  }

  public void setJspView(String jspView) {
    this.jspView = jspView;
  }

  public String getGwtModule() {
    return gwtModule;
  }

  public void setGwtModule(String gwtModule) {
    this.gwtModule = gwtModule;
  }

  public String getAppTitle() {
    return appTitle;
  }

  public void setAppTitle(String appTitle) {
    this.appTitle = appTitle;
  }

  public boolean isMobile() {
    return isMobile;
  }

  public void setMobile(boolean isMobile) {
    this.isMobile = isMobile;
  }

  public DataConfigDto getDataConfig() {
    return dataConfig;
  }

  public void setDataConfig(DataConfigDto dataConfig) {
    this.dataConfig = dataConfig;
  }


}
