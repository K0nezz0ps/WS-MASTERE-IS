var parkApp = angular.module("parkApp", []);

/**
 * monitorMainController (home page)
 */
parkApp.controller('parkMainController', function($scope, $rootScope, $http) {
	
	// Park(s) list
	$scope.loadedParkList = parkList;
	
	$scope.displayParkList = function() {
		console.log("hello");
	}
	
});