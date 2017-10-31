<%@page import="com.mysql.jdbc.Constants"%>
<%@page import="com.augmentum.exam.utils.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.augmentum.exam.constant.*" %>
<%@ page import="com.augmentum.exam.utils.*" %>
<%@ taglib prefix="block" uri="/WEB-INF/block.tld"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="http://examstatic.com/css/login.css" style="text/css">
     <script type="text/javascript" src="http://examstatic.com/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="http://examstatic.com/js/login.js"> </script>
  </head>
  <body>
    <c:set var="tip_message_visibility" scope="session"></c:set>
    <c:set var="userName" scope="session"></c:set>
    <c:set var="password" scope="session"></c:set>
    <block:display name="loginCookieSessionBlock"/>
    <div id="varpper">
      <div class="web_ico">
      </div>
      <div class="web_name">Online Exam Web System</div>
      <div class="login_form">
        <input type="hidden" value="${contexturl}" id="context"/>
        <div class="login_content">
          <div class="tip_message" id="error_message" style="visibility: ${tip_message_visibility}">
            ${TIP_MESSAGE}
          </div>
          <c:remove var="TIP_MESSAGE" scope="session"/>
          <form id="loginForm" action="<%=PathUtil.getFullPath("user/login")%>" method="post">
            <div class="login_form_username">
              <div class="login_form_username_logo"></div>
              <input type="text" name="userName" id="userName" class="login_form_username_input" placeholder="Username" value="<%=StringUtil.html2String((String) request.getSession().getAttribute("userName"))%>"/>
              <img id="validate_userName" src="${pageContext.servletContext.contextPath}/static/css/images/ICN_Client_Login_Wrong_20X20.png" class="login_form_username_validate_img">
            </div>
            <div class="login_form_password">
              <div class="login_form_password_logo"></div>
              <input type="password" name="password" id="password" class="login_form_password_intput" placeholder="Password" value="<%=StringUtil.html2String((String) request.getSession().getAttribute("password"))%>"/>
              <img id="validate_password" src="${pageContext.servletContext.contextPath}/static/css/images/ICN_Client_Login_Wrong_20X20.png" class="login_form_username_validate_img">
            </div>
            <input type="hidden" value="${lastpage}" name="lastpage">
            <input type="button" value="login" class="login_form_button"/>
            <div class="login_form_other">
              <input type="checkbox" name="remeber" class="login_form_checkbox" value="yes"/>
              <span class="login_form_checkbox_text">Remember me</span>
              <span class="login_form_forgotpassword_text">Forgot password?</span>
            </div>
          </form>
        </div>
      </div>
      <c:remove var="lastpage"/>
      <c:remove var="tip_message_visibility" scope="session"/>
      <c:remove var="userName" scope="session"/>
      <c:remove var="password" scope="session"/>
      <c:remove var="TIP_MESSAGE" scope="session"/>
      <div class="login_footer">
         <div class="login_footer_text">Copyright@2014Augmentum Inc. All Rights Reserved.</div>
       </div>
    </div>
  </body>
</html>