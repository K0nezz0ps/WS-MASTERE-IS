<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="monitorApp">
	<head>
		<!-- UTF-8 -->
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<!-- AutoRefresh -->
		<meta http-equiv="refresh" content="60">
		<!-- Import for JQuery -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<!-- Import for AngularJS -->
		<script src="/WS-MASTERE-IS/assets/libs/angular.min.js"></script>
		<!-- Import Monitor.js -->
		<script src="/WS-MASTERE-IS/assets/scripts/monitor.js"></script>
		<!-- Title -->
		<title>IngéSup - Monitor</title>
	</head>

	<header>
		JEE Mastère IngéSup Header
	</header>

	<body ng-controller="monitorMainController">
	
		<h1>MONITOR</h1>
		
		<c:if test="${fn:length(historyList) gt 0}">
			<div>
			
				<table id="monitorTable" style="border: 1px solid black; text-align: center;">
					<thead><th>Date</th><th>Machine ID</th><th>CPU</th><th>RAM</th><th>Storage</th></thead>
					<tbody>
						<c:forEach items="${historyList}" var="historyLine">
							<tr><td>${historyLine.dateEvent}</td><td>${historyLine.machineId}</td><td <c:if test="${historyLine.cpuState == ComponentState.VERYGOOD}">style="color: #1ed000;"</c:if>>${historyLine.cpuState}<td>${historyLine.ramState}</td><td>${historyLine.storageState}</td></tr>
						</c:forEach>
					</tbody>
				</table>
			
			</div>
		</c:if>
		
	</body>
	
	<footer>
		JEE Mastère Footer IngéSup
	</footer>

</html>