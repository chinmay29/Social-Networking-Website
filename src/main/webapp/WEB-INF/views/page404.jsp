<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Connect</title>
<link rel="icon"
	href="<c:url value='/resources/images/app/favicon.ico' />"
	type="image/x-icon" />

<link href="<c:url value='/resources/css/bootstrap.min.css' />"
	rel="stylesheet">
<link
	href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/custom.css' />"
	rel="stylesheet">

<meta name="viewport" content="width=device-width,initial-scale=1">

<script type="text/javascript"
	src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/custom.js' />"></script>
</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="brand" href="">Connect</a>
			</div>
		</div>
	</div>

	<div class="container-fluid content">
		<div class="row-fluid content">
			<div id="loginpage-content">
				<div id="text">
					<div class="container-fluid content">
						<div class="row-fluid content">
							<h1>Sorry!</h1>
							<h3>The page you requested is not available. The link you
								followed may be broken, or the page may have been moved or
								deleted. Please hit the browser back button to continue.</h3>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="footer">
		<div class="row">
			<div class="col-xs-12 col-sm-7 footer-text">
				&copy; Copyright
				<script type="text/javascript">
					document.write((new Date()).getFullYear());
				</script>
				Team Connect. All Rights Reserved.
			</div>
			<div class="col-xs-12 col-sm-5 footer-text">
				<span id="name">Site designed by <em>Team Connect</em></span>
			</div>
		</div>
	</div>
</body>
</html>