<jsp:include page="headerRefresh.jsp"/>

	<header>
		JEE Mastère IngéSup Header
	</header>

	<body ng-controller="parkMainController">
	
	<ul>
		<li ng-repeat="park in loadedParkList">
			<a href="/WS-MASTERE-IS/park/{{park.id}}">Park : {{park.roomIds}}</a>
		</li>
	</ul>
	
	<button ng-click="displayParkList()">Display</button>
	
<jsp:include page="footer.jsp"/>
