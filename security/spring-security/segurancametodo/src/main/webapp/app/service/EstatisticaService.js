moduleAplicacao.factory('estatisticaService', function($http) {
	
	
	var converterFiltro = function(filtro){
		var filtroConvertido = {
			idTipoConsulta : filtro.idTipoConsulta,
			listaAtributos : filtro.listaAtributos,
			estado		   : filtro.estado && filtro.estado.uf?filtro.estado.uf:"",
			idMunicipio	   : filtro.municipio && filtro.municipio.id?filtro.municipio.id:"",
			idLocalidade   : filtro.localidade && filtro.localidade.id?filtro.localidade.id:"",
			idUsuario      : filtro.usuario && filtro.usuario.idUsuario?filtro.usuario.idUsuario:"",
			dataInicio     : filtro.dataInicio?filtro.dataInicio:"",
			dataFim        : filtro.dataFim?filtro.dataFim:"",
			localSelecionado : filtro.localSelecionado	
		}
		return filtroConvertido;
	}
	
	return {
		
		getEstatisticas : function(filtro, callback){
			var filtroConvertido = converterFiltro(filtro);
			$http.post('rest/estatistica/getEstatisticas', filtroConvertido).success(function(itens){
				if(callback){
					callback(itens);
				}
			});
		},
		
		exportar : function(filtro){
			
			var filtroConvertido = converterFiltro(filtro);
			filtroConvertido.tipoExportacao = filtro.tipoExportacao;
			filtroConvertido.downloadID = filtro.downloadID;
			filtroConvertido.descricaoFiltro = filtro.descricaoFiltro;
			
			if(filtro.tipoExportacao == 'excel'){
				var url = '/geoestatistica/rest/estatistica/exportarExcel?idTipoConsulta='+filtroConvertido.idTipoConsulta+
																		 '&listaAtributos='+filtroConvertido.listaAtributos+
																		 '&estado='+filtroConvertido.estado+
																		 '&idMunicipio='+filtroConvertido.idMunicipio+
																		 '&idLocalidade='+filtroConvertido.idLocalidade+
																		 '&idUsuario='+filtroConvertido.idUsuario+
																		 '&dataInicio='+filtroConvertido.dataInicio+
																		 '&dataFim='+filtroConvertido.dataFim+
																		 '&tipoExportacao='+filtroConvertido.tipoExportacao+
																		 '&localSelecionado='+filtroConvertido.localSelecionado+
																		 '&descricaoFiltro='+filtroConvertido.descricaoFiltro+
																		 '&downloadID='+filtroConvertido.downloadID;				
			}
			else{
				var url = '/geoestatistica/rest/estatistica/exportarPDF?idTipoConsulta='+filtroConvertido.idTipoConsulta+
																		 '&listaAtributos='+filtroConvertido.listaAtributos+
																		 '&estado='+filtroConvertido.estado+
																		 '&idMunicipio='+filtroConvertido.idMunicipio+
																		 '&idLocalidade='+filtroConvertido.idLocalidade+
																		 '&idUsuario='+filtroConvertido.idUsuario+
																		 '&dataInicio='+filtroConvertido.dataInicio+
																		 '&dataFim='+filtroConvertido.dataFim+
																		 '&tipoExportacao='+filtroConvertido.tipoExportacao+
																		 '&localSelecionado='+filtroConvertido.localSelecionado+
																		 '&descricaoFiltro='+filtroConvertido.descricaoFiltro+
																		 '&downloadID='+filtroConvertido.downloadID;						
			}
			
			$idown = $('<iframe>', {id: 'idown', src: url}).hide().appendTo('body');
		
		},
		
		exportarDetalhado : function(filtro){
			
			var filtroConvertido = converterFiltro(filtro);
			filtroConvertido.tipoExportacao = filtro.tipoExportacao;
			filtroConvertido.indEspacializado = filtro.indEspacializado;
			filtroConvertido.idAtributo = filtro.idAtributo;
			filtroConvertido.downloadID = filtro.downloadID;
			filtroConvertido.descricaoFiltro = filtro.descricaoFiltro;
			
			if(filtro.tipoExportacao == 'excel'){
				var url = '/geoestatistica/rest/estatistica/exportarExcelDetalhado?indEspacializado='+filtroConvertido.indEspacializado+
																				 '&idAtributo='+filtroConvertido.idAtributo+
																				 '&estado='+filtroConvertido.estado+
																				 '&idMunicipio='+filtroConvertido.idMunicipio+
																				 '&idLocalidade='+filtroConvertido.idLocalidade+
																				 '&idUsuario='+filtroConvertido.idUsuario+
																				 '&dataInicio='+filtroConvertido.dataInicio+
																				 '&dataFim='+filtroConvertido.dataFim+
																				 '&tipoExportacao='+filtroConvertido.tipoExportacao+
																				 '&descricaoFiltro='+filtroConvertido.descricaoFiltro+
																				 '&downloadID='+filtroConvertido.downloadID;				
			}
			else{
				var url = '/geoestatistica/rest/estatistica/exportarPDFDetalhado?indEspacializado='+filtroConvertido.indEspacializado+
																				 '&idAtributo='+filtroConvertido.idAtributo+
																				 '&estado='+filtroConvertido.estado+
																				 '&idMunicipio='+filtroConvertido.idMunicipio+
																				 '&idLocalidade='+filtroConvertido.idLocalidade+
																				 '&idUsuario='+filtroConvertido.idUsuario+
																				 '&dataInicio='+filtroConvertido.dataInicio+
																				 '&dataFim='+filtroConvertido.dataFim+
																				 '&tipoExportacao='+filtroConvertido.tipoExportacao+
																				 '&descricaoFiltro='+filtroConvertido.descricaoFiltro+
																				 '&downloadID='+filtroConvertido.downloadID;						
			}
			
			$idown = $('<iframe>', {id: 'idown', src: url}).hide().appendTo('body');
		
		},		
		
		onDownloadCompleto : function(downloadID, callback){
            var cookiePattern = new RegExp(("downloadID=" + downloadID), "i");
            
            var cookieTimer = setInterval(checkCookies, 500);
 
            function checkCookies() {
                if ( document.cookie.search(cookiePattern) >= 0) {
                    clearInterval(cookieTimer);
                    if(callback){
                    	callback();
                    }
                }
            }	
		}		
	
	}
	
});