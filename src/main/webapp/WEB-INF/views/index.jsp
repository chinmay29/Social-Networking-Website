<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
          <a class="brand" href="">Connect</a>
          <a href="${pageContext.request.contextPath}/login" class="btn pull-right"><i class="icon-home"></i> &nbsp;&nbsp;Login</a> 
        </div>
      </div>
    </div>
    
    <div class="container-fluid content">
     <div class="row-fluid">
        <div id="index-page-content">
     		<div id="text">
     		<h1 class="page-header"> Welcome to Connect!!!</h1>
     		<h4>Connect easily with your friends and family</h4>
     		<div class="row-fluid">
     		<a href="${pageContext.request.contextPath}/register" class="home-buttons btn"><i class="icon-leaf"></i> &nbsp;&nbsp;Register</a>
     		&nbsp;&nbsp;&nbsp;&nbsp;
     		<a href="${pageContext.request.contextPath}/login" class="home-buttons btn"><i class="icon-home"></i> &nbsp;&nbsp;Login</a>
     		</div>
     		</div>
        </div>
     </div>
    </div>
    
	<div id="background">
		<img id="bg-img" alt="" src="<c:url value='/resources/images/app/background.jpg' />" />
	</div> 
 	     
 	<div id="footer">
	<div class="row">
	<div class="col-xs-12 col-sm-7 footer-text">
 	&copy; Copyright <script type="text/javascript">document.write((new Date()).getFullYear());</script> Team Connect. All Rights Reserved.
	</div>
	<div class="col-xs-12 col-sm-5 footer-text">
	<span id="name">Site designed by <em>Team Connect</em></span>
	</div>
	</div>
	</div>
 	
  </body>
</html>