<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.augmentum.exam.constant.*" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
  HttpSession httpSession = request.getSession();
  String tip_message_visibility = "hidden";
  Cookie[] cookies = request.getCookies();
  if (httpSession.getAttribute("PASSWORD") != null && httpSession.getAttribute("USERNAME") != null) {
%>
  <c:set var="userName" value="${USERNAME}" scope="session"></c:set>
  <c:set var="password" value="${PASSWORD}" scope="session"></c:set>
<%
  } else {
      for (Cookie cookie : cookies) {

          if (cookie.getName().equals("username")) {
              System.out.println("name=" + cookie.getValue());
%>
              <c:set var="userName" value="<%=cookie.getValue()%>" scope="session"></c:set>
<%
          } else if (cookie.getName().equals("password")) {
              System.out.println("password=" + cookie.getValue());
%>
              <c:set var="password" value="<%=cookie.getValue()%>" scope="session"></c:set>
<%
          }
      }
  }
%>
  <c:remove var="USERNAME" scope="session"/>
  <c:remove var="PASSWORD" scope="session"/>
<%
  String str = (String) httpSession.getAttribute(JsonCodeConstant.TIP_MESSAGE);
  if (str != null && !str.equals("")) {
      if (str.equals(JsonCodeConstant.USER_LOGOUT_SUCCESS_MESSAGE)) {
%>
          <c:set var="tip_message_visibility" value="hidden" scope="session"></c:set>
<%
      } else {
%>
          <c:set var="tip_message_visibility" value="visible" scope="session"></c:set>
<%
      }
  }
%>