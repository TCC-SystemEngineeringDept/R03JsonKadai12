<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% int point = (Integer)request.getAttribute("point");%>  
{"POINT":<%=point%>}