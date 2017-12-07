<!DOCTYPE html>

<%@page language="java" contentType="text/html;charset=ISO-8859-9" pageEncoding="ISO-8859-9" session="false" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
	<head>
		<title>Settings | BloodBank</title>
		
		<link href="<c:url value="/resources/css/seekBlood.css" />" rel="stylesheet">
		
		<spring:url value="/updateUser" var="updateUser" />
		<spring:url value="/deleteMe" var="deleteMe" />
				
		<c:set var="email_hint" value="Enter e-mail" />
		<c:set var="name_hint" value="Enter name & surname" />
		<c:set var="password_hint" value="Enter password" />
	</head>
	
	<body>
		<div id="mydiv">
				<form:form action="${updateUser}" method="POST" modelAttribute="user" id="settings_form">
				<table class=tbl>
					<tr>
						<th colspan="2" align="center">You are allowed to change only below informations</th>
					</tr>
					
					<tr>
						<td><b><form:label path="userEmail">E-mail</form:label></b></td>
						<td><form:input type="email" path="userEmail" placeholder="${email_hint}"/></td>
					</tr>
					
					<tr>
						<td><b><form:label path = "userPassword">Password</form:label></b></td>
						<td><form:input type="password" path="userPassword" placeholder="${password_hint}"/></td>
					</tr>
					
					<tr>
						<td><b><form:label path = "city">City</form:label></b></td>
						<td>
							<form:select path="city">
    							<form:option value="-" label="--Please Select--"/>
    							<form:options items="${city}" />
							</form:select>
						</td>
					</tr>
					
					<tr>
						<td><b>New password: </b></td>
						<td><input type="password" name="newpass" placeholder="Enter new password"/></td>
					</tr>
					
					
					<tr>
						<td colspan="2" align="center"><input id="btn" type="submit" value="Save Changes"/></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center"><a href="${deleteMe}/${userEmail}/">Delete My Account</a></td>
					</tr>
				</table>
			</form:form>
		</div>
	</body>
</html>