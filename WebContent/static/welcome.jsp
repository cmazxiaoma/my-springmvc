<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
  </head>
  <c:set var="context" value="${pageContext.request.contextPath}"></c:set>
  <body>
    <script type="text/javascript">
        window.location.href = '${context}' + '/page/user/login';
    </script>
  </body>
</html>