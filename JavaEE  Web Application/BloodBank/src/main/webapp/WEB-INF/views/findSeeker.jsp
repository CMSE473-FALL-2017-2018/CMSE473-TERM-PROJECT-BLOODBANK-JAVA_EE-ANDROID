<!DOCTYPE html>

<%@page language="java" contentType="text/html;charset=ISO-8859-9" pageEncoding="ISO-8859-9"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html>
	<head>
		<title>Find Seeker | BloodBank</title>
		
		<link href="<c:url value="/resources/css/findSeeker.css" />" rel="stylesheet">
		<c:set var="pageListHolder" value="${seekList}" scope="session" />
		<spring:url value="/findSeeker" var="pageurl" />
	
	</head>
	
	<body>		
		<div class="divSearch">
			<form action="${pageurl}/srcSeekerResult" id="formSearch" method="post">
         		<table class="search_table">
					<tr>
						<td><b>City</b></td>
						<td><input type="text" name="city" id="city"  placeholder="Enter city"></td>
						<td><p><b>AND</b></p></td>
						<td><b>Blood Type</b></td>
						<td><input type="text" name="bloodType" id="bloodType"  placeholder="Enter Blood Type"></td>
						<td colspan="2"><input type='submit' value='Search!'/></td>
					</tr>
				</table>
      		</form>   
		</div>
		&nbsp;
		
		<div id="divAllUsers">
		
			<table class="users">
				<tr id="listheader">
					<th colspan="8">List of the people who seeking blood</th>
				</tr>
				<tr>
					<th>E-mail</th>
					<th>Name</th>
					<th>City</th>
					<th>Blood Type</th>
					<th>Date</th>
				</tr>
				
				<c:forEach var="seeker" items="${pageListHolder.pageList}">
					<tr>
						<td>${seeker.seekerEmail}</td>
						<td>${seeker.seekerName}</td>
						<td>${seeker.city}</td>
						<td>${seeker.bloodType}</td>
						<td>${seeker.seekDate}</td>
					</tr>
				</c:forEach>
				<tr>
					<th colspan="8">${fn:length(pageListHolder.pageList)} seeker(s) found.</th>
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