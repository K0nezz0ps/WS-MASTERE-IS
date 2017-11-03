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
		<!-- Import CSS -->
		<link rel="stylesheet" type="text/css" href="/WS-MASTERE-IS/assets/styles/style.css"/>
		<!-- Compiled and minified CSS -->
 		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
	    <!-- Compiled and minified JavaScript -->
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	   	<!-- Material icons -->
	    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	    <!-- Script drag & drop  -->
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-drag-and-drop-lists/1.4.0/angular-drag-and-drop-lists.min.js"></script>
	    <link href="/WS-MASTERE-IS/assets/styles/c.css" rel="stylesheet">
		<!-- Title -->
		<title>IngéSup - Dashboard</title>
		<link rel="icon" type="image/png" href="/WS-MASTERE-IS/assets/images/favicon.png" />
		
		<script>
		
			var parkList 	= ${parkList};
			var currentRoom = ${room};
			var historyList = ${historyList};
			var roomList    = ${roomList};
			var currentPark = ${currentPark};

		</script>
		
		<!-- Import Park.js -->
		<script src="/WS-MASTERE-IS/assets/scripts/park.js"></script>
		
	</head>
	
	<header class="header-container">
		<a href="/WS-MASTERE-IS/park"><img src="/WS-MASTERE-IS/assets/images/ingesup_header_img.png" style="margin: 7px 0 7px 7px; height: 35px;"/></a>
		<a id="logout" href="/WS-CNS-AUTH/logout"><i class="material-icons">power_settings_new</i></a>
	</header>