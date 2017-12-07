<!DOCTYPE html>

<%@page language="java" contentType="text/html;charset=ISO-8859-9" pageEncoding="ISO-8859-9" session="false" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
	<head>
		<title>Login | BloodBank</title>
		
		<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">
		<spring:url value="/panel" var="panel" />
		
		<c:set var="email_hint" value="Enter e-mail" />
		<c:set var="password_hint" value="Enter password" />
		<c:set var="images" value="/bloodbank/resources/images"/>
	</head>
	
	<body>
		<form:form id="loginForm" modelAttribute="loginUser" action="${panel}" method="post">
			<div class="imgcontainer">
				<img src="${images}/img_avatar2.png" alt="image" class="avatar"/>
  			</div>
           
            <div id="container">
	            <table id="mytable">
	               	<tr>
						<td><b><form:label path="loginEmail">E-mail:</form:label></b></td>
						<td><form:input type="email" path="loginEmail" placeholder="${email_hint}"/></td>
					</tr>	
					<tr>
						<td><b><form:label path = "loginPassword">Password:</form:label></b></td>
						<td><form:input type="password" path="loginPassword" placeholder="${password_hint}"/></td>
					</tr>	
					<tr>
						<td colspan="2"><input id="btn" type="submit" value="Login"/></td>
					</tr>	
	             </table>
            </div>
       </form:form>
	</body>
</html>