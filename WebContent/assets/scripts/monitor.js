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
	$http.get("/WS-MASTERE-IS/machine?parkId=6&selectMode=all").then(function(response){
			console.log(response);
		});
	
});