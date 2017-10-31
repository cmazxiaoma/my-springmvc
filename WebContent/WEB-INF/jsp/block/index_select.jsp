<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  if (request.getSession().getAttribute("selectText") == null) {
      out.print("<option selected='selected'>" + "10" + "</option>");
      out.print("<option>" + "5" + "</option>");
  } else {
      int selectText = (int) request.getSession().getAttribute("selectText");
      if (selectText == 10) {
          out.print("<option selected='selected'>" + "10" + "</option>");
          out.print("<option>" + "5" + "</option>");
      } else if (selectText == 5) {
          out.print("<option>" + "10" + "</option>");
          out.print("<option selected='selected'>" + "5" + "</option>");
      }
  }
%> 