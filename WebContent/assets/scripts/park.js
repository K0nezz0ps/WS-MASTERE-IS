var parkApp = angular.module("parkApp", []);

/**
 * parkMainController
 * For the /park page
 */
parkApp.controller('parkMainController', function($scope, $rootScope, $http) {
	
	// Park(s) list
	$scope.loadedParkList = parkList;
	
	$scope.displayParkList = function() {
		console.log("hello");
	}
	
});

/**
 * parkProfileController
 * For the /park/{id} page
 */
parkApp.controller('parkProfileController', function($scope, $rootScope, $http) {
	
	$scope.currentPark 	= currentPark;
	$scope.roomList 	= roomList;
	
});

/**
 * roomProfileController
 * For the /park/{parkId}/{roomId} page
 */
parkApp.controller('roomProfileController', function($scope, $rootScope, $http) {
	
	$scope.currentRoom = currentRoom;
	$scope.historyList = historyList;
	$scope.roomList    = roomList;
	
	$scope.selectedNewRoom = null;
	
	$scope.deleteMachine = function(machine){
		//TODO: http delete request /rest/Machine with machine id
	}
	
	$scope.switchMachineRoom = function(machine) {
		
		// If no selectedValue for the new room id
		if($scope.selectedNewRoom)
			return;
		
		//TODO: http post request /rest/Machine to switch room
		
	}
	
});



