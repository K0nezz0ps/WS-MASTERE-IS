<jsp:include page="headerRefresh.jsp"/>

	<body ng-controller="parkMainController">
	
		<div class="park-title">
		
			<h3>Park Management</h3>
			<i>Click on the wanted park to enter</i>
		
		</div>
		
		<div class="container park-container">
		
			<!-- ForEach park in the parkList -->
			<div class="row park-container park-line" style="cursor: pointer;"  ng-click="openParkView(parkObject.park.id)" ng-repeat="parkObject in loadedParkList">
		
				<div class="col s2">PARK {{parkObject.park.id}}</div>
				
				<!-- ForEach room in the currentPark -->
				<div class="col s1" ng-repeat="room in parkObject.rooms">{{room.name}}</div>
		
			</div>
		
		</div>
		
		<div class="park-create">
			<a ng-click="showCreationPark()" class="waves-effect waves-light btn"><i class="material-icons right">{{createParkIcon}}</i>{{createParkButton}}</a>
			
			<div ng-if="createPark">
				<div class="input-field col s3">
          			<input placeholder="Name" ng-model="inputParkName" type="text" class="validate">
          			<label for="first_name">Park Name</label>
          			<a ng-click="createPark()" class="waves-effect waves-light btn">Create</a>
          			<p ng-if="showCreateError" style="color: red; font-size: 12px;">{{errorCreateMessage}}</p>
          			<p ng-if="showCreateValidation" style="color: green; font-size: 12px;">{{validationCreateMessage}}</p>
        		</div>
			</div>
		</div>
		
	
<jsp:include page="footer.jsp"/>
