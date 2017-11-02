<jsp:include page="headerRefresh.jsp"/>

	<body ng-controller="parkMainController">
		
		<div class="container">
		
			<!-- ForEach park in the parkList -->
			<div class="row park-container" style="cursor: pointer;"  ng-click="openParkView(parkObject.park.id)" ng-repeat="parkObject in loadedParkList">
		
				<div class="col s2">PARK {{parkObject.park.id}}</div>
				
				<!-- ForEach room in the currentPark -->
				<div class="col s1" ng-repeat="room in parkObject.rooms">{{room.name}}</div>
		
			</div>
		
		</div>
		
	
<jsp:include page="footer.jsp"/>
