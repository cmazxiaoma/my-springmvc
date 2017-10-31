<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="block" uri="/WEB-INF/block.tld" %>
<%@ page import="com.augmentum.exam.utils.*" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="http://examstatic.com/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="http://examstatic.com/js/common.js"> </script>
    <link rel="stylesheet" href="http://examstatic.com/css/create-question.css" style="text/css">
    <link rel="stylesheet" href="http://examstatic.com/css/index.css" style="text/css">
    <link rel="stylesheet" href="http://examstatic.com/css/question-detail.css" style="text/css">
    <title>detail</title>
  </head>
  <body>
    <div id="disabled-screen">
    </div>
    <c:set var="contexturl" value="${pageContext.request.contextPath}" />
    <c:set var="baseurl" value="${pageContext.request.contextPath}/page/"/>
    <div id="varpper">
      <div id="header">
        <block:display name="headBlock"/>
        <div id="location_detail">Question Management&nbsp;&nbsp;&gt;&nbsp;&nbsp;Question List&nbsp;&nbsp;&gt;&nbsp;&nbsp;${question.questionCodeId}</div>
        <div id="content">
          <div id="create_content">
            <div class="question-id-box">
              <div class="text">Question&nbsp;ID&nbsp;:</div>
              <input type="text" readonly="readonly" class="textinput" name="questionCodeId" value="${question.questionCodeId}" style="background-color: #D2DAE3"/>
            </div>
            <div class="question-content-box">
              <div class="text">Question&nbsp;:&nbsp;</div>
              <div class="right">
                <textarea class="desc" name="questionDesc" readonly="readonly">${question.questionDesc}</textarea>
              </div>
            </div>
            <div class="question-answer-box">
              <div class="text">Answer&nbsp;:&nbsp;</div>
              <div class="right">
                <div id="chooseA">
                  <c:set var="one" value="${question.questionAChoose}" scope="request"></c:set>
                  <div class="choose_text" title="A <%=StringUtil.html2String((String) request.getAttribute("one"))%>">A <%=StringUtil.html2String((String) request.getAttribute("one"))%></div>
                </div>
                <div id="chooseB">
                  <c:set var="two" value="${question.questionBChoose}" scope="request"></c:set>
                  <div class="choose_text" title="B <%=StringUtil.html2String((String) request.getAttribute("two"))%>">B <%=StringUtil.html2String((String) request.getAttribute("two"))%></div>
                </div>
                <div id="chooseC">
                  <c:set var="three" value="${question.questionCChoose}" scope="request"></c:set>
                  <div class="choose_text" title="C <%=StringUtil.html2String((String) request.getAttribute("three"))%>">C <%=StringUtil.html2String((String) request.getAttribute("three"))%></div>
                </div>
                <div id="chooseD">
                  <c:set var="four" value="${question.questionDChoose}" scope="request"></c:set>
                  <div class="choose_text" title="D <%=StringUtil.html2String((String) request.getAttribute("four"))%>">D <%=StringUtil.html2String((String) request.getAttribute("four"))%></div>
                </div>
              </div>
            </div>
            <button class="save-button" onclick="forwardEdit('${question.questionId}', '${contexturl}')">Edit</button>
            <a href="${baseurl}question/del/${question.questionId}" class="cancel-button" >Delete</a>
          </div>
        </div>
        <div id="footer">
          <div id="text">Copyright@2014Augmentum Inc. All Rights Reserved.</div>
        </div>
      </div>
    </div>
     <input type="hidden" value="${question.answer}" id="answer" name="answer"/>
     <input type="hidden" name="questionId" value="${question.questionId}"/>
     <c:remove var="question" scope="session"/>
  </body>
  <script type="text/javascript" src="http://examstatic.com/js/detail.js"> </script>
</html>