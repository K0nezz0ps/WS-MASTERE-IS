<jsp:include page="headerRefresh.jsp"/>

	<header>
		JEE Mastère IngéSup Header
	</header>

	<body ng-controller="parkProfileController">
	
		<a href="/WS-MASTERE-IS/park">Go back</a>
	
		<h1>PARK </h1>
		
		<div style="border: 1px solid black" ng-repeat="room in roomList">
		
			<a style="cursor: pointer; border:1px solid grey;" href="/WS-MASTERE-IS/park/{{currentPark.id}}/{{room.id}}" >Room name : {{room.name}}</p>
		
			<ul>
				<li  style="cursor: pointer;" ng-repeat="machine in room.machineList">
					{{machine.machineIp}}
				</li>
			</ul>
		
		</div>	
		
<jsp:include page="footer.jsp"/>