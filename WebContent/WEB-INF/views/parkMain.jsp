<jsp:include page="headerRefresh.jsp" />

<body ng-controller="parkProfileController">

	<a href="/WS-MASTERE-IS/park" class="waves-effect waves-light btn return-btn"><i class="material-icons left">skip_previous</i>GO BACK</a>
	
	<div class="park-title">
		
		<h3>Room Management</h3>
		<i>Click on the wanted room or switch machines position</i>
	
	</div>
	<div class="container" style="margin-top: 20px; margin-bottom: 20px;">
	
		<div ng-repeat="room in roomList" style="float: left;">
			<div class="col s12 m3">
				<div class="card blue-grey darken-1">
					<div class="card-content white-text">
					
						<!-- ROOM NAME -->
						<span class="card-title" style="text-align: center;">
							<a style="cursor: pointer; font-weight: bold; color: white;" href="/WS-MASTERE-IS/park/{{currentPark.id}}/{{room.id}}">{{room.name}} </a>
						</span>
						
						<!-- ROOM CONTENT -->
						<div class="simpleDemo">
							<ul dnd-list="room.machineList" dnd-inserted="logListEvent('inserted at', {{room.id}}, external, item.id)">
							
								<li ng-repeat="item in room.machineList" dnd-draggable="item"
									dnd-moved="room.machineList.splice($index, 1)"
									dnd-effect-allowed="move" dnd-selected="models.selected = item"
									ng-class="{'selected': models.selected === item}">
	
	
									<div class="card green darken-1">
										<div class="card-content white-text">{{item.id}} /
											{{item.machineIp}}</div>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
	</div>
	</div>
	
	<a style="position: absolute; right: 200px; top: 120px;" ng-click="editPark()" class="waves-effect waves-light btn ng-binding"><i class="material-icons right ng-binding">control_point</i>Modifier</a>
	
	<jsp:include page="footer.jsp" />