moduleAplicacao.controller("topo", function($scope, $http, securityService){
	
	var ng = $scope.ctrl_topo = {};
	
	ng.logout = function(){
		securityService.logout(function(sucesso){
			window.location.href = window.location.href;	
		});
	}
});