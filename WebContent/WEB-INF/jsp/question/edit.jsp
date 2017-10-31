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
    <title>edit</title>
  </head>
  <body>
    <div id="disabled-screen">
    </div>
    <c:set var="contexturl" value="${pageContext.request.contextPath}" />
    <c:set var="baseurl" value="${pageContext.request.contextPath}/page/"/>
    <div id="varpper">
      <div id="header">
        <block:display name="headBlock"/>
        <div id="location_detail">Question Management&nbsp;&nbsp;&gt;&nbsp;&nbsp;Edit Question</div>
        <div id="content">
          <div id="create_content">
            <form action="${baseurl}question/edit" method="post" id="editQuestion">
              <div class="question-id-box">
                <div class="text">Question&nbsp;ID&nbsp;:</div>
                <input type="text" readonly="readonly" class="textinput" name="questionCodeId" value="${question.questionCodeId}" style="background-color: #D2DAE3"/>
              </div>
              <div class="question-content-box">
                <div class="text">Question&nbsp;:&nbsp;</div>
                <div class="right">
                  <textarea class="desc" name="questionDesc">${question.questionDesc}</textarea>
                </div>
              </div>
              <div class="question-answer-box">
                <div class="text">Answer&nbsp;:&nbsp;</div>
                <div class="right">
                  <div class="choose-a">
                    <div class="a-checkbox" id="1" onclick="select(1)" style="background-image: url(http://examstatic.com/css/images/BTN_Radio_Unselected_16x16.png)"></div>
                    <div class="a">A</div>
                    <c:set var="one" value="${question.questionAChoose}" scope="request"></c:set>
                    <input type="text" class="input" id="radio1" name="questionAChoose" value= "<%=StringUtil.html2String((String) request.getAttribute("one"))%>"/>
                  </div>
                  <div class="choose-b-c-d">
                    <div class="a-checkbox" id="2" onclick="select(2)" style="background-image: url(http://examstatic.com/css/images/BTN_Radio_Unselected_16x16.png)"></div>
                    <div class="a">B</div>
                    <c:set var="two" value="${question.questionBChoose}" scope="request"></c:set>
                    <input type="text" class="input" id="radio2" name="questionBChoose" value="<%=StringUtil.html2String((String) request.getAttribute("two"))%>"/>
                  </div>
                  <div class="choose-b-c-d">
                    <div class="a-checkbox" id="3" onclick="select(3)" style="background-image: url(http://examstatic.com/css/images/BTN_Radio_Unselected_16x16.png)"></div>
                    <div class="a">C</div>
                    <c:set var="three" value="${question.questionCChoose}" scope="request"></c:set>
                    <input type="text" class="input" id="radio3" name="questionCChoose" value="<%=StringUtil.html2String((String) request.getAttribute("three"))%>"/>
                  </div>
                  <div class="choose-b-c-d">
                    <div class="a-checkbox" id="4" onclick="select(4)" style="background-image: url(http://examstatic.com/css/images/BTN_Radio_Unselected_16x16.png)"></div>
                    <div class="a">D</div>
                    <c:set var="four" value="${question.questionDChoose}" scope="request"></c:set>
                    <input type="text" class="input" id="radio4" name="questionDChoose" value = "<%=StringUtil.html2String((String) request.getAttribute("four"))%>"/>
                  </div>
                </div>
              </div>
               <input type="hidden" value="${question.answer}" id="answer" name="answer"/>
               <input type="hidden" name="questionId" value="${question.questionId}"/>
              <button class="save-button" onclick="edit()">Edit</button>
              <a href="${baseurl}question/query" class="cancel-button">Cancel</a>
            </form>
            <div id="result_window" >
              <img alt="" style="float: right; margin-top: 10px; margin-right: 10px;" src="http://examstatic.com/css/images/BTN_Close_16x16.png" onclick="closeResultWindow('${contexturl}')" id="fork">
              <div id="result_text" >edit successfully</div>
            </div>
          </div>
        </div>
        <div id="footer">
          <div id="text">Copyright@2014Augmentum Inc. All Rights Reserved.</div>
        </div>
      </div>
    </div>
    <input type="hidden" value="${editResult}" id="editResult"/>
    <c:remove var="question" scope="session"/>
    <c:remove var="editResult" scope="session"/>
  </body>
  <script type="text/javascript" src="http://examstatic.com/js/edit.js"> </script>
</html>