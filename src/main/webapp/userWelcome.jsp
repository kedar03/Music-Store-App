<!DOCTYPE HTML>
<%@page contentType="text/html;charset=UTF-8"%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Music Store</title>
</head>
<body>
	<h1>Welcome to the Music Store!</h1>
	<h2>Where would you like to go?</h2>

	<uL>
		<li><a href="catalog.html">Browse Catalog </a></li>
		<li><a href="cart.html">View Cart </a></li>
		<li><a href="welcome.html">Back to Site Homepage </a></li>
	</ul>

</body>
</html>
