moduleAplicacao.factory('tipoConsultaService', function($http) {
	
	return {
		
		findAll : function(callback){
			$http.post('rest/tipoConsulta/findAll', null).success(function(itens){
				if(callback){
					callback(itens);
				}
			});
		},
		
		findAtributosById : function(filtro, callback){
			$http.post('rest/tipoConsulta/findAtributosById', filtro).success(function(itens){
				if(callback){
					callback(itens);
				}
			});
		}		
	
	}
	
});