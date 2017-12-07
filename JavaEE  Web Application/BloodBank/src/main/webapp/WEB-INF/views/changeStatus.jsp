<!DOCTYPE html>

<%@page language="java" contentType="text/html;charset=ISO-8859-9" pageEncoding="ISO-8859-9" session="false" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
	<head>
		<title>Change Status | BloodBank</title>
		
		<link href="<c:url value="/resources/css/changeStatus.css" />" rel="stylesheet">
		<spring:url value="/changeMyStatus" var="changeMyStatus" />
				
		<c:set var="email_hint" value="Enter e-mail" />
		<c:set var="password_hint" value="Enter password" />
		
	</head>
	
	<body>
		<div id="mydiv">
			<form:form action="${changeMyStatus}" method="POST" modelAttribute="user" id="change_form">
				<table class="mystatus" border="1">
					<tr>
						<th colspan="2" align="center">Change Status: Available for donation?</th>
					</tr>
					
					<tr>
						<td><b><form:label path="userEmail">E-mail</form:label></b></td>
						<td><form:input value="${user.userEmail}" type="email" path="userEmail" placeholder="${email_hint}"/></td>
					</tr>
					
					<tr>
						<td><b><form:label path="userPassword">Password</form:label></b></td>
						<td><form:input value="${user.userPassword}" type="password" path="userPassword" placeholder="${password_hint}"/></td>
					</tr>

					<tr>
						<td><b><form:label path = "donor">Available for Donation?</form:label></b></td>
						<td>
							<form:select path="donor">
    							<form:option value="-" label="--Please Select--"/>
    							<form:options items="${isDonor}" />
							</form:select>
						</td>
					</tr>
				
					<tr>
						<td colspan="2" align="center"><input id="btn" type="submit" value="OK"/></td>
					</tr>
				</table>
			</form:form>
		</div>
	</body>
</html>