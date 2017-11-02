var parkApp = angular.module("parkApp", []);

/**
 * parkMainController
 * For the /park page
 */
parkApp.controller('parkMainController', function($scope, $rootScope, $http) {
	
	// Park(s) list
	$scope.loadedParkList = parkList;
	
	// Switch the view to the clicked park
	$scope.openParkView = function(parkId){
		window.location.href = "/WS-MASTERE-IS/park/" + parkId;
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

		// 
		
	}
	
	$scope.switchMachineRoom = function(machine) {
		
		// If no selectedValue for the new room id
		if($scope.selectedNewRoom)
			return;
		
		//TODO: http post request /rest/Machine to switch room
		
	}
	
	// Function that return the color class accorded to the given ComponentState
	$scope.getStateColorClass = function(state){

		switch(state){
		case "ALERT"	: return "alert-text";	  break;
		case "HEAVEN" 	: return "heaven-text";	  break;
		case "RAISED" 	: return "raised-text";	  break;
		case "AVERAGE" 	: return "average-text";  break;
		case "GOOD"		: return "good-text";	  break;
		case "VERYGOOD"	: return "verygood-text"; break;
		default: return "";
		}
		
	}
});



