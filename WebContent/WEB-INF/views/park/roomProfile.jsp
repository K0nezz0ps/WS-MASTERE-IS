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

		<!-- Title -->
		<title>IngéSup - Room</title>
		
		<script>
		
			var currentRoom = ${room};
			var historyList = ${historyList};
			var roomList    = ${roomList};

		</script>
		
		<!-- Import Park.js -->
		<script src="/WS-MASTERE-IS/assets/scripts/park.js"></script>
		
	</head>

	<header>
		JEE Mastère IngéSup Header
	</header>

	<body ng-controller="roomProfileController">
		
		<a href="/WS-MASTERE-IS/park/{{currentRoom.idPark}}">Go back</a>
	
		<div ng-repeat="machine in historyList" style="border: 1px solid grey; margin: 4px;">
		
			<p style="font-weight: bold;">{{machine.machineIp}}</p>
			
			<ul>
				<li>CPU : {{machine.cpu}} - USAGE : 
				
					<span ng-if="machine.cpuPercentage == 'ALERT'" style="background-color:#ff0000;">{{machine.cpuPercentage}}</span>
					<span ng-if="machine.cpuPercentage == 'HEAVEN'" style="background-color:#f5a000;">{{machine.cpuPercentage}}</span>
					<span ng-if="machine.cpuPercentage == 'RAISED'" style="background-color:#ffe200;">{{machine.cpuPercentage}}</span>
					<span ng-if="machine.cpuPercentage == 'AVERAGE'" style="background-color:#fff900;">{{machine.cpuPercentage}}</span>
					<span ng-if="machine.cpuPercentage == 'GOOD'" style="background-color:#b2ff00;">{{machine.cpuPercentage}}</span>
					<span ng-if="machine.cpuPercentage == 'VERYGOOD'" style="background-color:#1ed000;">{{machine.cpuPercentage}}</span>
				
				</li>
				<li>RAM : {{machine.ram}} - USAGE : 
				
					<span ng-if="machine.ramPercentage == 'ALERT'" style="background-color:#ff0000;">{{machine.ramPercentage}}</span>
					<span ng-if="machine.ramPercentage == 'HEAVEN'" style="background-color:#f5a000;">{{machine.ramPercentage}}</span>
					<span ng-if="machine.ramPercentage == 'RAISED'" style="background-color:#ffe200;">{{machine.ramPercentage}}</span>
					<span ng-if="machine.ramPercentage == 'AVERAGE'" style="background-color:#fff900;">{{machine.ramPercentage}}</span>
					<span ng-if="machine.ramPercentage == 'GOOD'" style="background-color:#b2ff00;">{{machine.ramPercentage}}</span>
					<span ng-if="machine.ramPercentage == 'VERYGOOD'" style="background-color:#1ed000;">{{machine.ramPercentage}}</span>
				
				</li>
				<li>Last update : {{machine.dateEvent}}</li>
			</ul>
			
			<!-- Button for the delete request -->
			<button ng-click="deleteMachine(machine)">Delete from room</button>
			
			<!-- Select to choose a new room for the machine -->
			<select ng-model="selectedNewRoom">
				<option ng-repeat="room in roomList">{{room.name}}</option>
			</select>
			<button ng-click="switchMachineRoom(machine)">Switch machine room</button>
		
		</div>		
		
	</body>
	
	<footer>
		JEE Mastère Footer IngéSup
	</footer>

</html>