<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="parkApp">
	<head>
		<!-- UTF-8 -->
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<!-- AutoRefresh -->
		<meta http-equiv="refresh" content="60">
		<!-- Import for JQuery -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<!-- Import for AngularJS -->
		<script src="/WS-MASTERE-IS/assets/libs/angular.min.js"></script>
		<script>
		
			var parkList = ${parkList};
			
		</script>
		
		<!-- Import Park.js -->
		<script src="/WS-MASTERE-IS/assets/scripts/park.js"></script>
		
		<!-- Title -->
		<title>IngéSup - Monitor</title>
	</head>

	<header>
		JEE Mastère IngéSup Header
	</header>

	<body ng-controller="parkMainController">
	
	<ul>
	<%-- 	<c:forEach items="${parkList}" var="park"> --%>
	<%-- 		<li><a href = "/WS-MASTERE-IS/park/${park.id}">Park : ${park.roomIds}</a></li> --%>
	<%-- 	</c:forEach> --%>
	
		<li ng-repeat="park in loadedParkList">
			<a href="/WS-MASTERE-IS/park/{{park.id}}">Park : {{park.roomIds}}</a>
		</li>
	</ul>
	
	<button ng-click="displayParkList()">Display</button>
	
	</body>

</html>
