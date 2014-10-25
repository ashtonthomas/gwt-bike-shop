<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- Would not evaluate EL (e.g. ${appUrl}) without this; not sure why it seems disabled by default. --%>
<%@ page isELIgnored="false" %>

<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <title>biker Project - ERROR ${statusCode}</title>
  </head>
  <body>
    <p>We're sorry!  We couldn't find that page.</p>
    <p>Please <a href="${appUrl}">click here</a> to return to the application.</p>
  </body>
</html>
