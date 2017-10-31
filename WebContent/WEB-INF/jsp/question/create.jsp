<%@page import="com.augmentum.exam.utils.EncryptUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.augmentum.exam.utils.RandomUtil" %>
<%@ taglib prefix="block" uri="/WEB-INF/block.tld" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="http://examstatic.com/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="http://examstatic.com/js/common.js"> </script>
    <link rel="stylesheet" href="http://examstatic.com/css/create-question.css" style="text/css">
    <link rel="stylesheet" href="http://examstatic.com/css/index.css" style="text/css">
    <title>create</title>
  </head>
  <body>
    <div id="disabled-screen">
    </div>
    <c:set var="contexturl" value="${pageContext.request.contextPath}" />
    <c:set var="baseurl" value="${pageContext.request.contextPath}/page/"/>
    <div id="varpper">
      <div id="header">
        <block:display name="headBlock"/>
        <div id="location_detail">Question Management&nbsp;&nbsp;&gt;&nbsp;&nbsp;Create Question</div>
        <div id="content">
          <div id="create_content">
            <form action="${baseurl}question/create" method="post" id="createQuestion">
            <div class="question-id-box">
              <div class="text">Question&nbsp;ID&nbsp;:</div>
              <input type="text" class="textinput" name="questionCodeId" readonly="readonly" value=""/>
            </div>
            <div class="question-content-box">
              <div class="text">Question&nbsp;:&nbsp;</div>
              <div class="right">
                <textarea class="desc" name="questionDesc">Which of the following is correct about the contribution of design pattern?</textarea>
              </div>
            </div>
            <div class="question-answer-box">
              <div class="text">Answer&nbsp;:&nbsp;</div>
              <div class="right">
                <div class="choose-a">
                  <div class="a-checkbox" id="1" onclick="select(1)" style="background-image: url(http://examstatic.com/css/images/BTN_Radio_Unselected_16x16.png)"></div>
                  <div class="a">A</div>
                  <input type="text" class="input" id="radio1" name="questionAChoose"/>
                </div>
                <div class="choose-b-c-d">
                  <div class="a-checkbox" id="2" onclick="select(2)" style="background-image: url(http://examstatic.com/css/images/BTN_Radio_Unselected_16x16.png)"></div>
                  <div class="a">B</div>
                  <input type="text" class="input" id="radio2" name="questionBChoose"/>
                </div>
                <div class="choose-b-c-d">
                  <div class="a-checkbox" id="3" onclick="select(3)" style="background-image: url(http://examstatic.com/css/images/BTN_Radio_Unselected_16x16.png)"></div>
                  <div class="a">C</div>
                  <input type="text" class="input" id="radio3" name="questionCChoose"/>
                </div>
                <div class="choose-b-c-d">
                  <div class="a-checkbox" id="4" onclick="select(4)" style="background-image: url(http://examstatic.com/css/images/BTN_Radio_Unselected_16x16.png)"></div>
                  <div class="a">D</div>
                  <input type="text" class="input" id="radio4" name="questionDChoose"/>
                  <input type="hidden" name="answer" id="answer" value="cmazxiaoma" />
                  <%
                    String token = EncryptUtil.makeToken();
                    request.getSession().setAttribute(token, token); 
                  %>
                  <input type="hidden" name="token" value="<%=token%>"/> 
                </div>
              </div>
            </div>
              <button class="save-button" onclick="create()" id="createButton">Create</button>
              <a href="${baseurl}question/query" class="cancel-button">Cancel</a>
            </form>
            <div id="result_window" >
              <img alt="" style="float: right; margin-top: 10px; margin-right: 10px;" src="http://examstatic.com/css/images/BTN_Close_16x16.png" onclick="closeResultWindow('${contexturl}')" id="fork">
              <div id="result_text" >create successfully</div>
            </div>
          </div>
        </div>
        <div id="footer">
          <div id="text">Copyright@2014Augmentum Inc. All Rights Reserved.</div>
        </div>
      </div>
    </div>
    <input type="hidden" value="${createResult}" id="createResult"/>
    <input type="hidden" value="${contexturl}" id="context"/>
    <c:remove var="createResult" scope="session"/>
  </body>
  <script type="text/javascript" src="http://examstatic.com/js/create.js"> </script>
</html>