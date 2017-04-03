moduleAplicacao.factory('localidadeService', function($http) {
	
	return {
		
		findEstados : function(callback){
			$http.post('rest/localidade/findEstados', null).success(function(itens){
				if(callback){
					callback(itens);
				}
			});
		},
		
		findMunicipiosByEstado : function(filtro, callback){
			$http.post('rest/localidade/findMunicipiosByEstado', filtro).success(function(itens){
				if(callback){
					callback(itens);
				}
			});
		},
		
		findLocalidadesByMunicipio : function(filtro, callback){
			$http.post('rest/localidade/findLocalidadesByMunicipio', filtro).success(function(itens){
				if(callback){
					callback(itens);
				}
			});
		}		
	
	}
	
});