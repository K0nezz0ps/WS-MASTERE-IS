<jsp:include page="headerRefresh.jsp"/>

	<body ng-controller="roomProfileController">

		<a href="/WS-MASTERE-IS/park/{{currentRoom.idPark}}" class="waves-effect waves-light btn return-btn"><i class="material-icons left">skip_previous</i>GO BACK</a>
	
		<div class="park-title">
			<h3>Machine monitoring</h3>
			<i>Check machines statistics & apply changes</i>
		</div>
	
		<div class="container park-container" style="margin-top: 20px; margin-bottom: 20px;">
		
			<div ng-repeat="machine in historyList" style="border: 1px solid grey; margin: 4px; width: 250px; height: 200px; float: left;">
			
				<p ng-if="machine.machineIp.length > 0" style="font-weight: bold; margin-left: 5px;">{{machine.machineIp}}</p>
				<p ng-if="!machine.machineIp.length > 0" style="font-weight: bold; margin-left: 5px;">N/A.N/A</p>
				<img ng-if="!isLaterThanFiveMinutes(machine.dateEvent)" style="float: right; top: 0;" src="/WS-MASTERE-IS/assets/images/ordi.png" alt="Machine {{machine.machineIp}}" height="64" width="64"/> 
				<img ng-if="isLaterThanFiveMinutes(machine.dateEvent)" style="float: right; top: 0;" src="/WS-MASTERE-IS/assets/images/ordi_off.png" alt="Machine {{machine.machineIp}}" height="64" width="64"/> 
				
				<ul style="margin-left: 5px;">
					<li>CPU ({{machine.cpu}}Ghz) : 
					
						<span ng-class="getStateColorClass(machine.cpuPercentage)">{{machine.cpuPercentage}}</span>
					
					</li>
					<li>RAM ({{machine.ram}}Go) : 
					
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
		
		<div style="width: 150px; position: absolute; top: 160px; right: 100px;">
		
			<!-- Machine IP -->
			<input id="machine_name" ng-model="input.machineIp" placeholder="Machine IP" type="text" class="validate" style="width: 200px; margin-right: 10px;">
			<label for="machine_name"></label>
			
			<!-- CPU -->
			<input id="machine_cpu" ng-model="input.machineCpu" placeholder="CPU Ghz" type="text" class="validate" style="width: 200px; margin-right: 10px;">
			<label for="machine_cpu"></label>
			
			<!-- RAM -->
			<input id="machine_ram" ng-model="input.machineRam" placeholder="RAM Go" type="text" class="validate" style="width: 200px; margin-right: 10px;">
			<label for="machine_ram"></label>
			
			<a ng-click="createMachine()" class="waves-effect waves-light btn">Create</a>
			<p ng-if="showCreateError" style="color: red; font-size: 12px;">{{errorCreateMessage}}</p>
			<p ng-if="showCreateValidation" style="color: green; font-size: 12px;">{{validationCreateMessage}}</p>
		</div>
		
<jsp:include page="footer.jsp"/>