<%@page import="com.augmentum.exam.utils.SpringUtil"%>
<%@page import="com.augmentum.exam.utils.StringUtil"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.augmentum.exam.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="block" uri="/WEB-INF/block.tld" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>index</title>
    <script type="text/javascript" src="http://examstatic.com/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="http://examstatic.com/js/index.js"> </script>
    <script type="text/javascript" src="http://examstatic.com/js/common.js"> </script>
    <link rel="stylesheet" href="http://examstatic.com/css/index.css" style="text/css">
  </head>
  <body>
    <div id="disabled-screen">
    </div>
    <c:set var="baseurl" value="${pageContext.request.contextPath}/page/"/>
    <c:set var="contexturl" value="${pageContext.request.contextPath}" />
    <c:set var="url" value="page/question/query?"></c:set>
    <div id="varpper">
      <div id="header">
        <block:display name="headBlock"/>
        <div id="content">
          <div id="left">
            <a href="${baseurl}question/query" id="question_list">Question List</a>
            <a href="${baseurl}question/create" id="create_question">Create Question</a>
          </div>
          <div id="right">
            <div id="search">
              <input type="text" id="searchkey" placeholder="Please input the keyword" value=""/>
              <button  id="search_button" onclick="searchQuestion()"></button>
            </div>
            <div id="right_content">
              <div id="table_header">
                <div class="header-id"><div class="text">ID</div></div>
                <img src="http://examstatic.com/css/images/ICN_Increase_10x15.png" id="increase_ico" onclick="sortQuestion()"/>
                <div class="header-desc">Description</div>
                <div class="header-edit">Edit</div>
                <div class="header-allchoose" id="header_checkbox" onclick="selectedAll()"></div>
              </div>
              <div id="tableContentBox">
              </div>
                <a href="javascript:open();" class="delete_button">Delete</a>
                <div id="del_window" >
                  <img alt="" style="float: right; margin-top: 10px; margin-right: 10px;" src="http://examstatic.com/css/images/BTN_Close_16x16.png" onclick="closedel()" id="fork">
                  <div class="del_window_text" id="del_window_text" >Are you sure delete the selected options?</div>
                  <button type="button" class="del_window_yes_button" id="del_window_yes_button" onclick="dodel()">YES</button>
                  <button type="button" class="del_window_no_button" id="del_window_no_button" onclick="closedel()">NO</button>
                </div>
                <div class="pageBox">
                  <button id="pre" class="pageleft" onclick="prepage()" type="button"></button>
                  <div id="page" style="display: inline-block;">
                  </div>
                  <button id="next" class="pageright" onclick="nextpage()" type="button"></button>
                  <select class="select" id="page_select" onchange="selectChange(this[selectedIndex].text)">
                  </select>
                  <div class="perpage">Perpage</div>
                  <input type="text" class="page_input" id="perpage" />
                  <button type="button" class="go_button" onclick="forwardPage()">Go</button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div id="footer">
          <div id="text">Copyright@2014Augmentum Inc. All Rights Reserved.</div>
        </div>
      </div>
    <input type="hidden" value="${contexturl}" id="context"/>
    <input type="hidden" value="${baseurl}" id="baseUrl"/>
    <c:remove var="delResult" scope="session"/>
  </body>
</html>