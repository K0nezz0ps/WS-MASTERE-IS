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
	$scope.input              = {parkName: ""};				// Wanted input name for the created park
	$scope.errorCreateMessage = "";				// Displayed message in case of error
	$scope.validationCreateMessage = "";		// Displayed validation message
	$scope.deleteForm		  = false;
	$scope.selectedPark     = null;
	$scope.validationDeleteMessage = false;
	
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
		
		$scope.showCreateError = false;
		$scope.showCreateValidation = false;
		
		// Check if the name is invalid (empty, here)
		if($scope.input.parkName.trim() == ""){
			$scope.errorCreateMessage = "Invalid name";
			$scope.showCreateError = true;
			return;
		}
		else{
			
			// Request to create the new park
			$http.post("/WS-MASTERE-IS/rest/createPark?parkName=" + $scope.input.parkName)
				.then(function(response){
					console.log(response);
					
					if(response.status == 201){
						$scope.validationCreateMessage = "Created !";
						$scope.showCreateValidation = true;
						
						// Adding the new Park to the ParkObjectList
						$scope.loadedParkList.push(response.data);
					}
					else{
						$scope.errorCreateMessage = response.data;
						$scope.showCreateError = true;
					}
						
				});
		}
	}
	
	// Select a park while clicking in a check box
	$scope.selectPark = function(parkObject){

		$scope.selectedPark = parkObject;
		$scope.deleteForm = true;
		
	}
	
	// Request to delete the selected park
	$scope.deletePark = function() {
		
		$http.post("/WS-MASTERE-IS/rest/ParkDelete?parkId=" + $scope.selectedPark.park.id)
			.then(function(response){
				
				if(response.status > 204){
					$scope.errorDeleteMessage = true;
					$scope.errorDeletionMessage = response.data;
				}
				else{
					$scope.validationDeleteMessage = true;
					
					var indexOfCurrentPark = $scope.loadedParkList.indexOf($scope.selectedPark);
					$scope.loadedParkList.splice(indexOfCurrentPark, 1);
					
					$scope.cancelDeletion();
				}
				
			});
		
	}
	
	// Cancel the deletion display
	$scope.cancelDeletion = function() {
		$scope.deleteForm = false;
		$scope.validationDeleteMessage = false;
	}

});

/**
 * parkProfileController
 * For the /park/{id} page
 */
parkApp.controller('parkProfileController', function($scope, $rootScope, $http) {
	
	$scope.currentPark 	= currentPark;
	$scope.roomList 	= roomList;
	$scope.dataSwitchList = {};
	$scope.selectedRoom = null;
	
	$scope.input = {
			roomName : ""
	};
	
	
    $scope.$watch('roomList', function(model) {
        $scope.modelAsJson = angular.toJson(model, true);
    }, true);

    $scope.arrayDataSwitch = [];
    //send data to server
    $scope.logListEvent = function(action, index, external, type) {
        var message = external ? 'External ' : '';
        message += type + ' element was ' + action + ' position ' + index;
        console.log(message);
        $scope.arrayDataSwitch.push({machineId:type, targetRoomId: index});
    };
    
	$scope.editPark = function(){
		
		$scope.dataSwitchList.switchInfoList = $scope.arrayDataSwitch;
		$http.post("/WS-MASTERE-IS/rest/editPark",$scope.dataSwitchList)
		.then(function(response){
			console.log(response);
			
			window.location.href = "/WS-MASTERE-IS/park/" + $scope.currentPark.id;
		});
	}
	
	// Delete a room from a park
	$scope.deleteRoom = function(room) {
		
		if(confirm("La salle " + room.name + " sera supprimée.")){
			
			$http.post("/WS-MASTERE-IS/rest/RoomDelete?roomId="+room.id)
				.then(function(response){
					window.location.href = "/WS-MASTERE-IS/park/" + currentPark.id;
				});
		}
	}
	
	$scope.createRoom = function() {
		
		$scope.showCreateValidation = false;
		$scope.showCreateError = false;
		
		if($scope.input.roomName.trim() == ""){
			$scope.errorCreateMessage = "Invalid input.";
			$scope.showCreateError = true;
			return;
		}
		
		$http.post("/WS-MASTERE-IS/rest/RoomCreate?roomName="+$scope.input.roomName+"&idPark=" + $scope.currentPark.id)
			.then(function(response){
				
				if(response.status == 500){
					$scope.errorCreateMessage = "Internal server error.";
					$scope.showCreateError = true;
				}
			
				if(response.status == 201)
					window.location.href = "/WS-MASTERE-IS/park/" + currentPark.id;
				
				
				
			});
			
			
			
		
	}
    
});

/**
 * roomProfileController
 * For the /park/{parkId}/{roomId} page
 */
parkApp.controller('roomProfileController', function($scope, $rootScope, $http) {
	
	$scope.currentRoom = currentRoom;
	$scope.historyList = historyList;
	$scope.roomList    = roomList;
	$scope.input = {
			machineName : "",
			machineCpu  : "",
			machineRam  : ""
	};
	
	$scope.createMachine = function(){
		
		$scope.showCreateError = false;
		$scope.showCreateValidation = false;
		
		// Check if the name is invalid (empty, here)
		if($scope.input.machineIp.trim() == "" || $scope.input.machineRam.trim() == "" || $scope.input.machineCpu.trim() == ""){
			$scope.errorCreateMessage = "Invalid input.";
			$scope.showCreateError = true;
			return;
		}
		else{
			
			var data = {};
			data.machineIp = $scope.input.machineIp;
			data.machineCpu  = $scope.input.machineCpu;
			data.machineRam  = $scope.input.machineRam;
			data.roomId      = $scope.currentRoom.id;
			
			$http.post("/WS-MASTERE-IS/rest/MachineCreate", data)
				.then(function(response){
					
					if(response.status == 201)
						window.location.href = "/WS-MASTERE-IS/park/" + $scope.currentRoom.idPark + "/" + $scope.currentRoom.id;
					
				});

		}
	}
	
	$scope.deleteMachine = function(machine){
		
		if(confirm("The Machine with IP" + machine.machineIp + ", will be deleted.")){
			
			$http.post("/WS-MASTERE-IS/rest/MachineDelete?machineIp=" + machine.machineIp)
				.then(function(response){
					if(response.status == 200)
						window.location.href = window.location.pathname;
				});
			
		}
		
	}
	
	$scope.isLaterThanFiveMinutes = function(dateEvent){
		
		var currentDate = new Date();
		
		if(new Date(currentDate) - new Date(dateEvent) >= 300000)
			return true;
		
		return false;
		
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



