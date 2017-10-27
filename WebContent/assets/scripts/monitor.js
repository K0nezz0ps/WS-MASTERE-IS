var monitorApp = angular.module("monitorApp", []);

/**
 * monitorMainController (home page)
 */
monitorApp.controller('monitorMainController', function($scope, $rootScope, $http) {
	
	// Loaded list of Machine
	$scope.machineList     = null;
	
	// Selected values by the user
	$scope.selectedParkId  = null;
	$scope.selectedMachine = null;
	
	// Loading all the machine in the current park
	$http.get("/WS-MASTERE-IS/machine?parkId=1&selectMode=all")
		.then(function(response){
			$scope.machineList = response.data;
		});
	
	// Test for RestController /rest/Machine
	$http.get("/WS-MASTERE-IS/rest/Machine", {id: 1, name: "hello"})
		.then(function(response){
			console.log(response);
		}); 
	
	// Delete a machine from the park
	$scope.deleteSelectedMachine = function(){
		
		console.log("Deleting : " + $scope.selectedMachine);
		
		if($scope.selectedMachine == null)
			return;
		
		// Data for DELETE : only machine id
		var data = {id: $scope.selectedMachine.id}
		
		// Sending DELETE request
		$http.delete("/rest/Machine", data)
			.then(function(response) {

				if(response != null)
					console.log(response.data);
				
		});
		
	}
	
});