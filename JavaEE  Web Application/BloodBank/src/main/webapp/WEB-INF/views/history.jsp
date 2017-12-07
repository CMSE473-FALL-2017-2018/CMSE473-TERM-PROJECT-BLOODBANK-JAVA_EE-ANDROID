<!DOCTYPE html>

<%@page language="java" contentType="text/html;charset=ISO-8859-9" pageEncoding="ISO-8859-9"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html>
	<head>
		<title>History | BloodBank</title>
		
		<link href="<c:url value="/resources/css/findDonor.css" />" rel="stylesheet">
		<c:set var="pageListHolder" value="${seekList}" scope="session" />
		<spring:url value="/history" var="pageurl" />
	</head>
	
	<body>
		<div id="divAllUsers">
			<table class="users">
				<tr id="listheader">
					<th colspan="6">My Seek History</th>
				</tr>
				<tr>
					<th>E-mail</th>
					<th>Name</th>
					<th>City</th>
					<th>Blood Type</th>
					<th>Date</th>
					<th>Select Process</th>
				</tr>
				
				<c:forEach var="seek" items="${pageListHolder.pageList}">
					<tr>
						<td>${seek.seekerEmail}</td>
						<td>${seek.seekerName}</td>
						<td>${seek.city}</td>
						<td>${seek.bloodType}</td>
						<td>${seek.seekDate}</td>
						<td><a href = "<c:url value = "/delete/${seek.seekID}"/>">Delete</a></td>
					</tr>
				</c:forEach>
				<tr>
					<th colspan="6">${fn:length(pageListHolder.pageList)} seek(s) found.</th>
				</tr>
			</table>
		</div>
		&nbsp;
		<div class="pag">
    		<span id="spanPrev">
	    		<c:choose>
	        		<c:when test="${pageListHolder.firstPage}">Prev</c:when>
	       			 <c:otherwise><a href="${pageurl}/prev">Prev</a></c:otherwise>
	    		</c:choose>
    		</span>
    		&nbsp;
    		<span id="spanPages">
    			<c:forEach begin="0" end="${pageListHolder.pageCount-1}" varStatus="loop">&nbsp;&nbsp;
   					<c:choose>
        				<c:when test="${loop.index == pageListHolder.page}">${loop.index+1}</c:when>
        				<c:otherwise><a href="${pageurl}/${loop.index}">${loop.index+1}</a></c:otherwise>
    				</c:choose>&nbsp;&nbsp;
    			</c:forEach>
   			 </span>
    		&nbsp;
    		<span id="spanNext">
			    <c:choose>
			        <c:when test="${pageListHolder.lastPage}">Next</c:when>
			        <c:otherwise><a href="${pageurl}/next">Next</a></c:otherwise>
			    </c:choose>
   			 </span>
   		 </div>
	
	</body>
</html>