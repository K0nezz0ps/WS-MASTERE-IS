<jsp:include page="headerRefresh.jsp"/>

	<body ng-controller="roomProfileController">

		<a href="/WS-MASTERE-IS/park/{{currentRoom.idPark}}" class="waves-effect waves-light btn return-btn"><i class="material-icons left">skip_previous</i>GO BACK</a>
	
		<div class="park-title">
			<h3>Machine monitoring</h3>
			<i>Check machines statistics & apply changes</i>
		</div>
	
		<div class="container park-container" style="margin-top: 20px; margin-bottom: 20px;">
		
			<div ng-repeat="machine in historyList" style="border: 1px solid grey; margin: 4px; width: 250px; height: 200px; float: left;">
			
				<p style="font-weight: bold; margin-left: 5px;">{{machine.machineIp}}</p>
				<img ng-if="!isLaterThanFiveMinutes(machine.dateEvent)" style="float: right; top: 0;" src="/WS-MASTERE-IS/assets/images/ordi.png" alt="Machine {{machine.machineIp}}" height="64" width="64"/> 
				<img ng-if="isLaterThanFiveMinutes(machine.dateEvent)" style="float: right; top: 0;" src="/WS-MASTERE-IS/assets/images/ordi_off.png" alt="Machine {{machine.machineIp}}" height="64" width="64"/> 
				
				<ul style="margin-left: 5px;">
					<li>CPU : {{machine.cpu}} - : 
					
						<span ng-class="getStateColorClass(machine.cpuPercentage)">{{machine.cpuPercentage}}</span>
					
					</li>
					<li>RAM : {{machine.ram}} - : 
					
						<span ng-class="getStateColorClass(machine.ramPercentage)">{{machine.ramPercentage}}</span>
					
					</li>
					<li>Last update : {{machine.dateEvent}}</li>
				</ul>
				
				<!-- Button for the delete request -->
				<div style="text-align:center;"> 
					<a style="background-color: rgb(255, 99, 99); margin-left: auto; margin-right: auto;" ng-click="deleteMachine(machine)" class="waves-effect waves-light btn"><i class="material-icons right">cancel</i>confirm ?</a>
				</div>
			
			</div>
			
		</div>
		
<jsp:include page="footer.jsp"/>