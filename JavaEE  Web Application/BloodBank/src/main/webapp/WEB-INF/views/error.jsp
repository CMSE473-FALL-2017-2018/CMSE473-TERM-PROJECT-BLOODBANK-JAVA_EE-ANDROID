<!DOCTYPE html>

<%@page language="java" contentType="text/html;charset=ISO-8859-9" pageEncoding="ISO-8859-9" session="false" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
	<head>
		<title>Error | BloodBank</title>
		
		<link href="<c:url value="/resources/css/error.css" />" rel="stylesheet">
	</head>
	
	<body>
		<h2>Error</h2>
		<a href = "<c:url value = "/"/>">Home</a> <b>OR</b> <a href = "<c:url value = "/login"/>">Login</a>
	</body>
</html>