package com.biker.server.request.filter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Checks the request for an existing request.gz file, indicating a precompressed version of the
 * resource and serve it if found.
 */
public class StaticGzipFilter implements Filter {

  private static final String ENCODING_GZIP = "gzip";
  private static final String GZ_EXTENSION = ".gz";
  private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
  private static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
  private static final String HEADER_IF_MODIFIED_SINCE = "If-Modified-Since";
  private static final String HEADER_LAST_MODIFIED = "Last-Modified";
  private static final String HEADER_VARY = "Vary";

  final static private String INIT_MIME_TYPES = "mime-types";

  private ServletContext servletContext;
  private Set<String> mimeTypes = null;

  Logger log = Logger.getLogger("my.logger");


  public void destroy() {
    // No-op.
  }

  public void doFilter(ServletRequest rawRequest, ServletResponse rawResponse, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) rawRequest;
    HttpServletResponse response = (HttpServletResponse) rawResponse;

    log.log(Level.ALL, "doFilter: " + request);

    String requestPath = request.getServletPath();

    String mt = servletContext.getMimeType(requestPath);

    String gzipPath = requestPath + GZ_EXTENSION;
    // log.info( "Requested path={}, mime type={}; gzip proposal={}.", requestPath, mt, gzipPath );

    // Don't bother checking for null mime types (which includes directories ending in slash),
    // or anything other than the configured mime types (if any).
    if (mt == null || (mimeTypes != null && !mimeTypes.contains(mt))) {
      // Not looking for this mime type.
      // log.info( "Not looking for static gz for mime type={}; request={}.", mt, requestPath );

      chain.doFilter(request, response);
      return;
    }

    URL url = servletContext.getResource(gzipPath);
    if (url != null) {
      // log.info( "Gzip proposal={} was found; mime type={}, url={}.", gzipPath, mt, url );

      // Tell caches that response may vary by accept-encoding
      response.setHeader(HEADER_VARY, HEADER_ACCEPT_ENCODING);

      boolean gzip = false;

      // Does the client accept gzip?
      String accept = request.getHeader(HEADER_ACCEPT_ENCODING);
      if (accept != null && accept.indexOf(ENCODING_GZIP) >= 0)
        gzip = true;

      if (gzip) {
        URLConnection conn = url.openConnection();
        long lastModified = conn.getLastModified() / 1000 * 1000; // Strip milliseconds.
        int contentLength = conn.getContentLength();
        // String eTag = requestPath + "_" + contentLength + "_" + lastModified;

        // If-Modified-Since header should be greater than LastModified. If so, then return 304.
        // This header is ignored if any If-None-Match header is specified.
        long ifModifiedSince = request.getDateHeader(HEADER_IF_MODIFIED_SINCE);

        // log.info(
        // "Req={}, gz length={}, last mod={}/{}, client request If-Modified-Since={}/{}.",
        // requestPath, contentLength, lastModified, new Date( lastModified ),
        // ifModifiedSince, new Date( ifModifiedSince ) );

        if ( // ifNoneMatch == null &&
        ifModifiedSince != -1 && // -1==client didn't even send I-M-S header.
            ifModifiedSince + 1000 > lastModified) // Give a 1000ms buffer in case of
                                                   // slight time mismatch in last modified;
                                                   // but shouldn't happen since we
                                                   // stripped milliseconds above.
        {
          // log.info( "File={} NOT MODIFIED (304).", requestPath );

          // response.setHeader("ETag", eTag); // Required in 304.
          response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
          return;
        } else {
          // log.info( "File={} modified, sending to client.", requestPath );
        }

        response.setHeader(HEADER_CONTENT_ENCODING, ENCODING_GZIP);
        if (mt != null)
          response.setContentType(mt);

        // We know the length because we loaded from a file, not an on-the-fly gzip
        // stream, so go ahead and include it.
        if (contentLength > 0)
          response.setContentLength(contentLength);

        response.setDateHeader(HEADER_LAST_MODIFIED, lastModified);

        ServletOutputStream outs = response.getOutputStream();
        final int BUF_SIZE = 8 * 1024;
        byte[] buf = new byte[BUF_SIZE];
        int numRead;
        // int total = 0;

        InputStream ins = conn.getInputStream();

        try {
          while ((numRead = ins.read(buf)) != -1) {
            outs.write(buf, 0, numRead);
            // total += numRead;
          }
        } finally {
          ins.close();
        }

        // log.info( "Gzip resource={}: total bytes written={}.", gzipPath, total );

        // Should we let the rest of the chain proceed (via chain.doFilter), or just
        // return here since we wrote to the underlying stream?
        // And would it be better to use RequestDispatcher.include() as opposed to
        // writing directly to the stream?
        return;
      }
    } else {
      // log.info( "Gzip proposal={} was NOT found.", gzipPath );
      // No-op; fall through and let the chain take care of sending the uncompressed file, rpc, etc.
    }

    // Static gzip file was not found, or else client didn't claim to accept gzip.
    chain.doFilter(request, response);
  }

  public void init(FilterConfig cfg) throws ServletException {
    // log.info( "Entering init." );

    servletContext = cfg.getServletContext();

    String cfgMimeTypes = cfg.getInitParameter(INIT_MIME_TYPES);
    if (cfgMimeTypes != null) {
      String[] types = cfgMimeTypes.split(",");
      mimeTypes = new HashSet<String>();
      for (String type : types) {
        type = type.trim();
        // log.info( "Enabled mime type={}.", type );
        mimeTypes.add(type);
      }
    } else {
      // log.info( "No mime type filtering." );
    }
  }

}
