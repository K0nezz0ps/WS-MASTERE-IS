var parkApp = angular.module("parkApp", ['dndLists']);

/**
 * parkMainController
 * For the /park page
 */
parkApp.controller('parkMainController', function($scope, $rootScope, $http) {
	
	$scope.loadedParkList     = parkList;		// Park(s) list
	$scope.createParkButton   = "Create Park"; 	// Display name of the button
	$scope.createParkIcon     = "control_point";// Display icon in the button
	$scope.parkForm           = false; 	  		// Boolean to display the creation park form
	$scope.inputParkName      = "nameOfPark";	// Wanted input name for the created park
	$scope.errorCreateMessage = "";				// Displayed message in case of error
	$scope.validationCreateMessage = "";		// Displayed validation message
	$scope.deleteForm		  = false;
	
	// Switch the view to the clicked park
	$scope.openParkView = function(parkId){
		window.location.href = "/WS-MASTERE-IS/park/" + parkId;
	}
	
	// Show/Hide the div with the Creation Park Form
	$scope.showCreationPark = function(){
		
		$scope.parkForm = !$scope.parkForm;
		
		if($scope.parkForm){
			$scope.createParkButton = "Cancel add";
			$scope.createParkIcon   = "remove_circle_outline";
		}
		else{
			$scope.createParkButton = "Create Park";
			$scope.createParkIcon   = "control_point";
		}
			
	}
	
	// POST Request to create a park, display error in case of bad input or wrong request
	$scope.createPark = function(){
		
		console.log($scope.inputParkName);
		
		$scope.showCreateError = false;
		$scope.showCreateValidation = false;
		
		// Check if the name is invalid (empty, here)
		if($scope.inputParkName.trim() == ""){
			$scope.errorCreateMessage = "Invalid name";
			$scope.showCreateError = true;
			return;
		}
		else{
			
			// Request to create the new park
			$http.post("/WS-MASTERE-IS/rest/createPark?parkName=" + $scope.inputParkName)
				.then(function(response){
					console.log(response);
					
					if(response.status == 201){
						$scope.validationCreateMessage = "Created !";
						$scope.showCreateValidation = true;
						
						//TODO: add the new park in the $scope.loadedParkList
					}
					else{
						$scope.errorCreateMessage = response.data;
						$scope.showCreateError = true;
					}
						
				});
		}
	}
	
	// Select a park while clicking in a check box
	$scope.selectPark = function(parkId){
		console.log("selected : " + parkId);
	}
	
	// Show/hide the button to confirm deletion of a park
	$scope.showDeletePark = function(){
		$scope.deleteForm = !$scope.deleteForm;
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
	$scope.deleteMachine = function(machine){

		// 
		
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



