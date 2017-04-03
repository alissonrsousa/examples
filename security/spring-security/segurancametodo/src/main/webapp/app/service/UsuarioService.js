moduleAplicacao.factory('usuarioService', function($http) {
	
	return {
		
		findAll : function(callback){
			$http.post('rest/usuario/findAll', null).success(function(itens){
				if(callback){
					callback(itens);
				}
			});
		},
		
		findByStatus : function(situacaoUsuario, callback){
			$http.post('rest/usuario/findByStatus', situacaoUsuario).success(function(itens){
				if(callback){
					callback(itens);
				}
			});
		}		
	
	}
	
});