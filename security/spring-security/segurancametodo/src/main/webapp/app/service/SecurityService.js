moduleAplicacao.factory('securityService', function($http) {
	return {
		getUsuario : function(callback){
//			$http.get('rest/security/getUserSession').success(function(user){
				if(callback){
					callback({userName : "alisson.sousa"});
				}
//			});
		},
		
		getModulos : function(callback){
//			$http.get('proxy/security/sessao/listarModulos', {
//				params : {
//					chave: window.usuario.chave
//				}
//			}).success(function(modulos){
				if(callback){
					callback([]);
				}
//			});
		}
//		,
//		
//		logout : function(callback){
//			$http.post('rest/security/logout').success(function(sucesso){
//				if(callback){
//					callback(eval(sucesso));
//				}
//			});
//		},
//		
//		keepAlive : function(callback){
//			$http.post('rest/security/keepAlive').success(function(success){
//				if(callback){
//					callback(success);
//				}
//			});
//		}
	};
});