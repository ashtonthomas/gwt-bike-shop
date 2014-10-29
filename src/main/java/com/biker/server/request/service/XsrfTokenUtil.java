package com.biker.server.request.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;

public class XsrfTokenUtil {

  private static final ThreadLocal<MessageDigest> perThreadMd5 = new ThreadLocal<MessageDigest>() {
    @Override
    protected MessageDigest initialValue() {
      try {
        return MessageDigest.getInstance("MD5");
      } catch (final NoSuchAlgorithmException e) {
        return null;
      }
    }
  };


  public static char[] HEX_CHARS = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
      'A', 'B', 'C', 'D', 'E', 'F'};

  public static String getToken(final String sessionCookie) {
    final byte[] cookieBytes = sessionCookie.getBytes();
    return toHexString(getMd5Digest(cookieBytes));
  }

  private static Cookie getCookie(final Cookie[] cookies, final String cookieName,
      final boolean allowDuplicates) {
    Cookie cookieToReturn = null;
    // Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (final Cookie cookie : cookies) {
        if (cookieName.equals(cookie.getName())) {
          // ensure that it's the only one cookie, since duplicate
          // cookies
          // can be a sign of a cookie overriding attempt.
          if (cookieToReturn == null) {
            if (allowDuplicates) {
              // do not attempt to detect duplicate cookies
              return cookie;
            } else {
              cookieToReturn = cookie;
            }
          } else {
            throw new IllegalArgumentException("Duplicate cookie! " + "Cookie override attack?");
          }
        }
      }
    }
    return cookieToReturn;
  }

  public static byte[] getMd5Digest(final byte[] input) {
    final MessageDigest md5 = perThreadMd5.get();
    md5.reset();
    md5.update(input);
    return md5.digest();
  }

  public static String getToken(final Cookie[] cookies) {

    final Cookie sessionCookie = getCookie(cookies, "JSESSIONID", false);
    if (sessionCookie == null || sessionCookie.getValue() == null
        || sessionCookie.getValue().length() == 0) {
      return "Session cookie is not set or empty! " + "Unable to generate XSRF cookie";
    }
    return getToken(sessionCookie.getValue());

  }

  /**
   * Returns a string representation of the byte array as a series of hexadecimal characters.
   *
   * @param bytes byte array to convert
   * @return a string representation of the byte array as a series of hexadecimal characters
   */
  public static String toHexString(final byte[] bytes) {
    final char[] hexString = new char[2 * bytes.length];
    int j = 0;
    for (final byte b : bytes) {
      hexString[j++] = HEX_CHARS[(b & 0xF0) >> 4];
      hexString[j++] = HEX_CHARS[b & 0x0F];
    }

    return new String(hexString);
  }


}
