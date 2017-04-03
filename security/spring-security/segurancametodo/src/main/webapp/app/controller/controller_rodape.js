moduleAplicacao.controller("rodape",function($rootScope, $scope, $window, securityService){
	
	$scope.itensSelecionados = 0;
	
	securityService.getUsuario(function(usuario){
		$window.usuario = $scope.ctrl_init.usuario = usuario;
		

		securityService.getModulos(function(modulos){
			$scope.ctrl_init.modulos = modulos;
		});

		LoadMessage.destroy();
	});

});