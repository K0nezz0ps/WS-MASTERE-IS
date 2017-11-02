<jsp:include page="headerRefresh.jsp"/>

	<header>
		JEE Mastère IngéSup Header
	</header>

	<body ng-controller="roomProfileController">
		
		<a href="/WS-MASTERE-IS/park/{{currentRoom.idPark}}">Go back</a>
	
		<div ng-repeat="machine in historyList" style="border: 1px solid grey; margin: 4px;">
		
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
			
			<!-- Select to choose a new room for the machine -->
			<select ng-model="selectedNewRoom">
				<option ng-repeat="room in roomList">{{room.name}}</option>
			</select>
			<button ng-click="switchMachineRoom(machine)">Switch machine room</button>
		
		</div>		
		
<jsp:include page="footer.jsp"/>