<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>error</title>
  </head>
  <body>
    <h2>
      <c:out value=" {result: ${resultJson.result}, data: ${resultJson.data}, errorCode: ${resultJson.errorCode}}"></c:out>
    </h2>
  </body>
</html>