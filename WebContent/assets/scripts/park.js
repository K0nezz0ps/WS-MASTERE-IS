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
parkApp.controlle('parkProfileController', function($scope, $rootScope, $http) {
	
	$scope.testValue = test;
	
});

