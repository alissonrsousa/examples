moduleAplicacao.factory('motivoNaoEspacializadoService', function($http) {
	
	return {
		
		findAll : function(callback){
			$http.post('rest/motivoNaoEspacializado/findAll', null).success(function(itens){
				if(callback){
					callback(itens);
				}
			});
		}	
	
	}
	
});