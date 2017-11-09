<jsp:include page="headerRefresh.jsp" />

<body ng-controller="parkProfileController">

	<a href="/WS-MASTERE-IS/park" class="waves-effect waves-light btn return-btn"><i class="material-icons left">skip_previous</i>GO BACK</a>
	
	<div class="park-title">
		
		<h3>Room Management</h3>
		<i>Click on the wanted room or switch machines position</i>
	
	</div>
	<div class="container" style="margin-top: 20px; margin-bottom: 20px;">
	
		<div ng-repeat="room in roomList" style="float: left; margin: 10px;">
			<div class="col s12 m3">
				<div class="card blue-grey darken-1">
					<div style="background-color: #e2e2e2; border: 3px solid grey;" class="card-content white-text">
					
						<!-- ROOM NAME -->
						<span class="card-title" style="text-align: center;">
							<a style="cursor: pointer; font-weight: bold; color: black;" href="/WS-MASTERE-IS/park/{{currentPark.id}}/{{room.id}}">{{room.name}} </a>
						</span>
						
						<!-- ROOM CONTENT -->
						<div class="simpleDemo">
							<ul dnd-list="room.machineList" dnd-inserted="logListEvent('inserted at', {{room.id}}, external, item.id)">
							
								<li style="background-color: #e2e2e2; border: 2px solid white; margin-bottom: 5px;" ng-repeat="item in room.machineList" dnd-draggable="item"
									dnd-moved="room.machineList.splice($index, 1)"
									dnd-effect-allowed="move" dnd-selected="models.selected = item"
									ng-class="{'selected': models.selected === item}">
	
	
									<div style="cursor: pointer;" class="card green darken-1">
										<div class="card-content white-text"><img src="/WS-MASTERE-IS/assets/images/ordi.png" alt="machine" height="36" width="36"/>
											<span style="bottom: 10px; position: relative;">{{item.machineIp}}</span></div>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
	</div>
	</div>
	
	<a style="position: absolute; right: 200px; top: 120px; background-color: rgb(46, 169, 39);" ng-click="editPark()" class="waves-effect waves-light btn ng-binding"><i class="material-icons right ng-binding">control_point</i>appliquer</a>
	
	<!-- Delete ROOM -->
	<div style="position: absolute; right: 200px; top: 200px;">
		<select ng-model="selectedRoom">
			<option ng-repeat="room in roomList" ng-value="{{room}}">{{room.name}}</option>
		</select>
		
		<a style="background-color: rgb(46, 169, 39);" ng-click="deleteRoom()" class="waves-effect waves-light btn ng-binding"><i class="material-icons right ng-binding">control_point</i>DELETE</a>
	
	</div>

	<jsp:include page="footer.jsp" />