<jsp:include page="headerRefresh.jsp"/>

	<body ng-controller="parkMainController">
	
		<div class="park-title">
		
			<h3>Park Management</h3>
			<i>Click on the wanted park to enter</i>
		
		</div>
		
		<div class="container park-container">
		
			<!-- ForEach park in the parkList -->
			<div class="row park-container park-line" style="cursor: pointer;" ng-repeat="parkObject in loadedParkList">
		
				<div class="col s2" ng-click="openParkView(parkObject.park.id)">PARK {{parkObject.park.id}}</div>
				
				<!-- ForEach room in the currentPark -->
				<div class="col s1" ng-click="openParkView(parkObject.park.id)" ng-repeat="room in parkObject.rooms">{{room.name}}</div>
		
				<div style="float: right;"><i style="cursor: pointer;" ng-click="selectPark(parkObject)" class="material-icons">delete</i></div>
			
			</div>
		
		</div>
		
	      
		
		<div class="park-create">
			
			<div style="width: 60%; float: left;">
				<a ng-click="showCreationPark()" class="waves-effect waves-light btn"><i class="material-icons right">{{createParkIcon}}</i>{{createParkButton}}</a>
				
				<div ng-if="parkForm" class="input-field">
	       			<input id="park_name" ng-model="input.parkName" type="text" class="validate" style="width: 200px; margin-right: 10px;">
	       			<label for="park_name">Park Name</label>
	       			<a ng-click="createPark()" class="waves-effect waves-light btn">Create</a>
	       			<p ng-if="showCreateError" style="color: red; font-size: 12px;">{{errorCreateMessage}}</p>
	       			<p ng-if="showCreateValidation" style="color: green; font-size: 12px;">{{validationCreateMessage}}</p>
	       		</div>
			</div>
			
			<div style="width: 35%; float: right; text-align: center;">
	       		<a ng-if="deleteForm" style="background-color: red;" ng-click="deletePark()" class="waves-effect waves-light btn"><i class="material-icons right">check</i>confirm ?</a>
	       		<i ng-if="deleteForm" style="margin-top: 5px;float:right; cursor: pointer;" ng-click="cancelDeletion()" class="material-icons">cancel</i>
	       		<p ng-if="deleteForm"> Delete the PARK {{selectedParkId}}</p>
	       		<p ng-if="validationDeleteMessage" style="color: green;">Successfully deleted !</p>
	       		<p ng-if="errorDeleteMessage" style="color: red;">{{errorDeletionMessage}}</p>
			</div>
       		
		</div>
		
	
<jsp:include page="footer.jsp"/>
