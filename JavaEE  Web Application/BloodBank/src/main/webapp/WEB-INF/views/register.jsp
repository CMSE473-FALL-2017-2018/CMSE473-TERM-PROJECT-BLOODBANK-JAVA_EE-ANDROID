<!DOCTYPE html>

<%@page language="java" contentType="text/html;charset=ISO-8859-9" pageEncoding="ISO-8859-9" session="false" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
	<head>
		<title>Register | BloodBank</title>
		
		<link href="<c:url value="/resources/css/register.css" />" rel="stylesheet">
		
		<spring:url value="/insertUser" var="insertUser" />
		
		<c:set var="email_hint" value="Enter e-mail" />
		<c:set var="name_hint" value="Enter name & surname" />
		<c:set var="password_hint" value="Enter password" />
	</head>
	
	<body>
		<form:form action="${insertUser}" method="POST" modelAttribute="user" id="reg_form">
				<table id="reg_table" border="1">
					<tr>
						<th colspan="2" align="center">User Registration Form</th>
					</tr>
					
					<tr>
						<td class="left"><b><form:label path="userEmail">E-mail</form:label></b></td>
						<td class="right"><form:input type="email" path="userEmail" placeholder="${email_hint}"/></td>
					</tr>
					
					<tr>
						<td class="left"><b><form:label path = "userPassword">Password</form:label></b></td>
						<td class="right"><form:input type="password" path="userPassword" placeholder="${password_hint}"/></td>
					</tr>
					
					<tr>
						<td class="left" ><b><form:label path="userName">Name</form:label></b></td>
						<td class="right"><form:input type="text" path="userName" placeholder="${name_hint}"/></td>
					</tr>
					
				<tr>
						<td class="left"><b><form:label path="sex">Sex</form:label></b></td>
						<td class="right">
							<form:select path="sex">
    							<form:option value="-" label="--Please Select--"/>
    							<form:options items="${sex}" />
							</form:select>
						</td>
					</tr>
					
					<tr>
						<td class="left"><b><form:label path="birthYear">Birth Year</form:label></b></td>
						<td class="right">
							<form:select path="birthYear">
    							<form:option value="0" label="--Please Select--"/>
    							<form:options items="${birthYear}" />
							</form:select>
						</td>
					</tr>
					
					<tr>
						<td class="left"><b><form:label path="city">City</form:label></b></td>
						<td class="right">
							<form:select path="city">
    							<form:option value="-" label="--Please Select--"/>
    							<form:options items="${city}" />
							</form:select>
						</td>
					</tr>
					
					<tr>
						<td class="left"><b><form:label path="bloodType">Blood Type</form:label></b></td>
						<td class="right">
							<form:select path="bloodType">
    							<form:option value="-" label="--Please Select--"/>
    							<form:options items="${bloodType}" />
							</form:select>
						</td>
					</tr>
					
					<tr>
						<td class="left"><b><form:label path="donor">Available for Donation?</form:label></b></td>
						<td class="right">
							<form:select path="donor">
    							<form:option value="-" label="--Please Select--"/>
    							<form:options items="${donor}" />
							</form:select>
						</td>
					</tr>
				
					<tr>
						<td class="right" colspan="2" align="center"><input type="submit" value="Enroll"/></td>
					</tr>
				</table>
			</form:form>	
	</body>
</html>