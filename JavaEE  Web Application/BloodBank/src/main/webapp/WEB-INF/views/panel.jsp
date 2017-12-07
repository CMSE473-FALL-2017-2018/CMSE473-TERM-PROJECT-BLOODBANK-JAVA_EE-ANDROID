<!DOCTYPE html>

<%@page language="java" contentType="text/html;charset=ISO-8859-9" pageEncoding="ISO-8859-9" session="false" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
	<head>
		<title>User Panel | BloodBank</title>
		
		<link href="<c:url value="/resources/css/panel.css" />" rel="stylesheet">
	</head>
	
	<body>
		<div id="menu">
			<ul>
			  <li><a class="active" href="#user">Welcome, ${user.getUserName()}</a></li>
			  <li><a href = "<c:url value = "/findDonor"/>">Find Donor</a></li>
			  <li><a href = "<c:url value = "/findSeeker"/>">Find Seeker</a></li>
			  <li><a href = "<c:url value = "/changeStatus"/>">Change Status</a></li>
			  <li><a href = "<c:url value = "/seekBlood"/>">Seek Blood</a></li>
			  <li><a href = "<c:url value = "/settings"/>">Settings</a></li>
			  <li><a href = "<c:url value = "/logout"/>">Log Out</a></li>
			</ul>
		</div>
	</body>
</html>