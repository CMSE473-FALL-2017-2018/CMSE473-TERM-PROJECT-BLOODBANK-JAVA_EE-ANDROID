<!DOCTYPE html>

<%@page language="java" contentType="text/html;charset=ISO-8859-9" pageEncoding="ISO-8859-9" session="false" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
	<head>
		<title>Result | BloodBank</title>
		
		<link href="<c:url value="/resources/css/result.css" />" rel="stylesheet">
	</head>
	
	<body>
		<div class="alert">
		  <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		  <strong>Message:</strong>Successfully!
		</div>
		<div class="menu">
			<ul>
				<li><a class="active" href="#home">Please Select</a></li>
				<li><a href = "<c:url value = "/"/>">Home</a></li>
				<li><a href = "<c:url value = "/login"/>">Login</a></li>
			</ul>
		</div>
	</body>
</html>