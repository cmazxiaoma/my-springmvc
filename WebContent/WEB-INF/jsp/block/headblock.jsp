<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header_ico"></div>
<div id="title">Online Exam Web System</div>
<div id="language">中文</div>
<a href="${pageContext.request.contextPath}/page/user/logout" id="logout">Logout</a>
<div id="userName">
  <c:choose>
    <c:when test="${USER != null}">
      <c:out value="${USER.name}"></c:out>
    </c:when>
    <c:otherwise><c:out value="null"></c:out></c:otherwise>
    </c:choose>
</div>
<div id="user_ico"></div>
<div id="breadcrumb">
  <a href="${pageContext.request.contextPath}/page/question/query" id="question">Question Management</a>
  <a href="#" id="exam">Exam Management</a>
</div>