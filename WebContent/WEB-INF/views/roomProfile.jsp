<jsp:include page="headerRefresh.jsp"/>

	<body ng-controller="roomProfileController">

		<a href="/WS-MASTERE-IS/park/{{currentRoom.idPark}}" class="waves-effect waves-light btn return-btn"><i class="material-icons left">skip_previous</i>GO BACK</a>
	
		<div class="park-title">
			<h3>Machine monitoring</h3>
			<i>Check machines statistics & apply changes</i>
		</div>
	
		<div class="container park-container" style="margin-top: 20px; margin-bottom: 20px;">
		
			<div ng-repeat="machine in historyList" style="border: 1px solid grey; margin: 4px; width: 250px; float: left;">
			
				<p style="font-weight: bold;">{{machine.machineIp}}</p>
				
				<ul>
					<li>CPU : {{machine.cpu}} - : 
					
						<span ng-class="getStateColorClass(machine.cpuPercentage)">{{machine.cpuPercentage}}</span>
					
					</li>
					<li>RAM : {{machine.ram}} - : 
					
						<span ng-class="getStateColorClass(machine.ramPercentage)">{{machine.ramPercentage}}</span>
					
					</li>
					<li>Last update : {{machine.dateEvent}}</li>
				</ul>
				
				<!-- Button for the delete request -->
				<button ng-click="deleteMachine(machine)">Delete from room</button>
			
			</div>
			
		</div>
		
<jsp:include page="footer.jsp"/>