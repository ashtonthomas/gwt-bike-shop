package com.biker.server.request.dispatch;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ErrorController implements Controller {

  final private Logger log = Logger.getLogger(ErrorController.class.getName());


  @Override
  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // GWT's RequestDispatcher doesn't have any constants defined.
    // See
    // http://docs.oracle.com/javaee/6/api/constant-values.html#javax.servlet.RequestDispatcher.ERROR_EXCEPTION
    // String originalUri = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
    // Can be null e.g. if just navigate to /errors.
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    String originalQueryString =
        (String) request.getAttribute("javax.servlet.forward.query_string");
    String originalUri = (String) request.getAttribute("javax.servlet.forward.request_uri");
    String originalPathInfo = (String) request.getAttribute("javax.servlet.forward.path_info");

    // String serverName = request.getServerName();
    // int serverPort = request.getServerPort();
    // String scheme = request.getScheme();

    String file = "/";
    if (originalQueryString != null) // For devmode: e.g. "gwt.codesvr=127.0.0.1:9997".
      file += "?" + originalQueryString;

    // URL url = new URL( scheme, serverName, serverPort, file );
    String url = file;
    // log.info( "serverName={}, serverPort={}, scheme={}, built url={}.", serverName, serverPort,
    // scheme, url );

    log.info("Error={} loading requested URL=\"{}\", query string=\"{}\", path info=\"{}\", appUrl=\"{}\"."
        + "--"
        + statusCode
        + "--"
        + originalUri
        + "--"
        + originalQueryString
        + "--"
        + originalPathInfo + "--" + url);

    ModelAndView mav = new ModelAndView("error"); // Will render error.jsp.
    mav.addObject("appUrl", url); // This will just be a path ("/${moduleNameLower}/"); the browser
                                  // will fill
                                  // the scheme, host, port.
    mav.addObject("statusCode", statusCode);

    return mav;
  }

}
