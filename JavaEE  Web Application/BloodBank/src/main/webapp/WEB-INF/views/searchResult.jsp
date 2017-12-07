<!DOCTYPE html>

<%@page language="java" contentType="text/html;charset=ISO-8859-9" pageEncoding="ISO-8859-9" session="false" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
	<head>
		<title>Search Result | BloodBank</title>
		
		<link href="<c:url value="/resources/css/srcDonorResult.css" />" rel="stylesheet">
	</head>
	
	<body>
		<div class="divSearchResult">
			<c:choose>
  				<c:when test="${fn:length(result) > 0}">
	        			<table class="users">
	        				<tr><th colspan="8"><b>Search results for Available people for donation.</b></th></tr>
							<tr>
								<th>E-mail</th>
								<th>Name</th>
								<th>Birth Year</th>
								<th>Sex</th>
								<th>City</th>
								<th>Blood Type</th>
								<th>Available for Donation</th>
							</tr>
					
							<c:forEach var="user" items="${result}">
								<tr>
									<td>${user.userEmail}</td>
									<td>${user.userName}</td>
									<td>${user.birthYear}</td>
									<td>${user.sex}</td>
									<td>${user.city}</td>
									<td>${user.bloodType}</td>
									<td>${user.donor}</td>	
								</tr>
							</c:forEach>
							
							<tr><th colspan="8"><b>${fn:length(result)} user(s) found.</b></th></tr>
					</table>
  				</c:when>		
		  		<c:otherwise>
			  		<div class="alert">
				  			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
				  			<strong>Message:</strong>Donor not found. Go to <a style="color:white" href="/bloodbank/findDonor">All Donors</a>
					</div>
		  		</c:otherwise>
			</c:choose>
		</div>
	</body>
</html>