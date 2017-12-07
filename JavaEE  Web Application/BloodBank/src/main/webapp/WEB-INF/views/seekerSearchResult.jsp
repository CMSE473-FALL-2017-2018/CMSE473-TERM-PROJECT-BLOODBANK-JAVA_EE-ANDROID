<!DOCTYPE html>

<%@page language="java" contentType="text/html;charset=ISO-8859-9" pageEncoding="ISO-8859-9" session="false" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
	<head>
		<title>Search Result | BloodBank</title>
		
		<link href="<c:url value="/resources/css/seekerSearchResult.css" />" rel="stylesheet">
	</head>
	
	<body>
		<div class="divSearchResult">
			<c:choose>
  				<c:when test="${fn:length(result) > 0}">
	        			<table class="users">
	        			<tr><th colspan="8"><b>Search results for who seeking blood</b></th></tr>
							<tr>
								<th>E-mail</th>
								<th>Name</th>
								<th>City</th>
								<th>Blood Type</th>
							</tr>
					
							<c:forEach var="seeker" items="${result}">
								<tr>
									<td>${seeker.seekerEmail}</td>
									<td>${seeker.seekerName}</td>
									<td>${seeker.city}</td>
									<td>${seeker.bloodType}</td>
								</tr>
							</c:forEach>
							<tr><th colspan="8"><b>${fn:length(result)} seeker(s) found.</b></th></tr>
					</table>
  				</c:when>		
		  		<c:otherwise>
		  			<div class="alert">
			  			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
			  			<strong>Message:</strong>Seeker not found. Go to <a style="color:white" href="/bloodbank/findSeeker">All Seekers</a>
					</div>
		  		</c:otherwise>
			</c:choose>
		</div>
	</body>
</html>