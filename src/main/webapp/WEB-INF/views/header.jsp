<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	HttpSession s = request.getSession(false);

	if(s == null || s.getAttribute("userId") == null) {
		response.sendRedirect(request.getContextPath() + "/login");
	}
%>


<!DOCTYPE html>
<html>
<head>
    <title>Connect</title>
    <link rel="icon" href="<c:url value='/resources/images/app/favicon.ico' />" type="image/x-icon"/>
    
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/css/custom.css' />" rel="stylesheet">
    
    <meta name="viewport" content="width=device-width,initial-scale=1">

    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/custom.js' />"></script>
  </head>
  
  <body>
  	  
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="brand" href="${pageContext.request.contextPath}/home">Connect</a>
          <a href="${pageContext.request.contextPath}/resetlogin" class="btn pull-right"><i class="icon-off"></i> &nbsp;&nbsp;Logout</a> 
          <a class="name pull-right" href="${pageContext.request.contextPath}/profile"><c:out value="${firstName}"></c:out> <c:out value="${lastName}"></c:out></a>
        </div>
      </div>
    </div>
    
    <div class="container-fluid content">
     <div class="row-fluid content">
     	<div class="span2 content">
     		<div id="sidebar content">
     		<div class="well sidebar-nav content">
     			<ul class="nav nav-list">
     				<li><a href="${pageContext.request.contextPath}/home"><i class="icon-home"></i> &nbsp;&nbsp;Home</a></li>
     				<li><a href="${pageContext.request.contextPath}/profile"><i class="icon-briefcase"></i> &nbsp;&nbsp;Profile</a></li>
     				<li><a href="${pageContext.request.contextPath}/newsfeed"><i class="icon-list-alt"></i> &nbsp;&nbsp;News Feed</a></li>
     				<li><a href="${pageContext.request.contextPath}/friends"><i class="icon-user"></i> &nbsp;&nbsp;Friends</a></li>
     				<li><a href="${pageContext.request.contextPath}/events"><i class="icon-calendar"></i> &nbsp;&nbsp;Events</a></li>
     				<li><a href="${pageContext.request.contextPath}/chat"><i class="icon-comment"></i> &nbsp;&nbsp;Chat</a></li>
     			</ul>
     		</div>
     		</div>
     	</div>
     	<div class="span10 main-content content">
     	