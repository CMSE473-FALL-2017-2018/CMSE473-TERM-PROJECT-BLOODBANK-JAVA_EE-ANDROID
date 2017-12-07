<!DOCTYPE html>

<%@page language="java" contentType="text/html;charset=ISO-8859-9" pageEncoding="ISO-8859-9" session="false" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<html>
	<head>
		<title>Seek Blood | BloodBank</title>
		
		<link href="<c:url value="/resources/css/seekBlood.css" />" rel="stylesheet">
		
		<spring:url value="/insertSeeker" var="insertSeeker" />
		<spring:url value="/history" var="history" />
		<c:set var="email_hint" value="Enter e-mail" />
		<c:set var="name_hint" value="Enter name" />
		
	</head>
	
	<body>
		<div id="mydiv">
			<form:form action="${insertSeeker}" method="post" modelAttribute="seek" id="seek_form">
			<table class="tbl">
				<tr>
					<th colspan="2" align="center">Need blood? Fill the form</th>
				</tr>
				
				<tr>
					<td><b><form:label path = "bloodType">Blood Type</form:label></b></td>
					<td>
						<form:select path="bloodType">
    						<form:option value="-" label="--Please Select--"/>
    						<form:options items="${bloodType}" />
						</form:select>
					</td>
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
					<td colspan="2" align="center"><input id="btn" type="submit" value="Complete"/></td>
					
				</tr>
				
				<tr>
					<td align="center" colspan="2"><a href="${history}">See My History</a></td>
				</tr>
				
			</table>
		</form:form>
		</div>
	</body>
</html>