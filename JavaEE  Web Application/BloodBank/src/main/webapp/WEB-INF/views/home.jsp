<!DOCTYPE html>

<%@page language="java" contentType="text/html;charset=ISO-8859-9" pageEncoding="ISO-8859-9" session="false" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
	<head>
		<title>Home | BloodBank</title>
		
		<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
	</head>
	
	<body>
		<div class="menu">
			<ul>
			  <li><a class="active" href="#home">Please Select</a></li>
			  <li><a href = "<c:url value = "/register"/>">Register</a></li>
			  <li><a href = "<c:url value = "/login"/>">Login</a></li>
			</ul>
		</div>
	</body>
</html>