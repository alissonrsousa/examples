moduleAplicacao.controller("consulta",function($scope, 
											   tipoConsultaService,
											   localidadeService,
											   usuarioService,
											   estatisticaService,
											   motivoNaoEspacializadoService){
	
	var ng = $scope.ctrl_consulta = {};
	
	var pathView = "app/view/consulta/";
	ng.contentView = pathView+"feicoesgraficasmub.html";
	
	ng.idTipoConsultaSelecionado = 1;
	ng.estado = {};
	ng.municipio = {};
	ng.localidade = {};
	ng.situacaoUsuario = '';
	ng.usuario = {};
	ng.listUsuarios = [];
	
	ng.checkAllFeicoesGraficasMUB = false;
	ng.listAtributosSelecionadosFeicoesGraficasMUB = [];
	ng.listAtributosFeicoesGraficasMUB = [];
	ng.habilitarBtnExportarFeicoesGraficasMUB = false;
	
	ng.checkAllEspacializacaoDados = false;
	ng.listAtributosSelecionadosEspacializacaoDados = [];
	ng.listAtributosEspacializacaoDados = [];
	ng.habilitarBtnExportarEspacializacaoDados = false;
	
	ng.checkAllGerenciaEspacializacao = false;
	ng.listAtributosSelecionadosGerenciaEspacializacao = [];
	ng.listAtributosGerenciaEspacializacao = [];	
	ng.habilitarBtnExportarGerenciaEspacializacao = false;
	
	ng.lodTiposConsulta = function(){
		ng.isLoading = true;
		ng.listTiposConsulta = [];
		
		tipoConsultaService.findAll(function(itens){
			ng.listTiposConsulta = itens;
			if(itens && itens.length > 0){
				itens[0].checked = "true";
				ng.loadAtributos();
			}
		});
		
	}

	ng.loadEstados = function(){
		localidadeService.findEstados(function(itens){
			ng.listEstados = itens;
		});	
	}
	
	ng.loadMunicipios = function(){
		localidadeService.findMunicipiosByEstado(ng.estado, function(itens){
			ng.listMunicipios = itens;
		});	
	}	
	
	ng.loadLocalidades = function(){
		localidadeService.findLocalidadesByMunicipio(ng.municipio, function(itens){
			ng.listLocalidades = itens;
		});	
	}
	
	ng.loadUsuarios = function(){
		usuarioService.findByStatus(ng.situacaoUsuario, function(itens){
			ng.listUsuarios = itens;
		});	
	}	
	
	ng.loadAtributos = function(){
		
		tipoConsultaService.findAtributosById({id: 1}, function(itens){
			ng.listAtributosFeicoesGraficasMUB = itens;	
			ng.inicializaDadosAtributosFeicoesGraficasMUB();
			
			tipoConsultaService.findAtributosById({id: 2}, function(itens){
				ng.listAtributosEspacializacaoDados = itens;
				ng.inicializaDadosAtributosEspacializacaoDados();
				
				motivoNaoEspacializadoService.findAll(function(itens){
					ng.listAtributosGerenciaEspacializacao = itens;
					ng.inicializaDadosAtributosGerenciaEspacializacao();
					ng.isLoading = false;
				});					
			});	
		});		
	}	
	
	ng.inicializaDadosAtributosFeicoesGraficasMUB = function(){
		for(var indice in ng.listAtributosFeicoesGraficasMUB){
			ng.listAtributosFeicoesGraficasMUB[indice].checked = false;
			ng.listAtributosFeicoesGraficasMUB[indice].quantidade = "-";
		}
		ng.filtroFeicoesGraficasMUB = {};
	}	
	
	ng.inicializaDadosAtributosEspacializacaoDados = function(){
		for(var indice in ng.listAtributosEspacializacaoDados){
			ng.listAtributosEspacializacaoDados[indice].checked = false;
			ng.listAtributosEspacializacaoDados[indice].espacializado = "-";
			ng.listAtributosEspacializacaoDados[indice].naoEspacializado = "-";
			ng.listAtributosEspacializacaoDados[indice].porcentagem = "-";
		}
		ng.filtroEspacializacaoDados = {};
	}
	
	ng.inicializaDadosAtributosGerenciaEspacializacao = function(){
		for(var indice in ng.listAtributosGerenciaEspacializacao){
			ng.listAtributosGerenciaEspacializacao[indice].checked = false;
			ng.listAtributosGerenciaEspacializacao[indice].logradouro = "-";
			ng.listAtributosGerenciaEspacializacao[indice].estacao = "-";
			ng.listAtributosGerenciaEspacializacao[indice].caixaTerminal = "-";
			ng.listAtributosGerenciaEspacializacao[indice].secaoServico = "-";
		}
		ng.filtroGerenciaEspacializacao = {};
	}	
	
	ng.alterarTipoConsulta = function(idTipoConsulta){
		ng.idTipoConsultaSelecionado = idTipoConsulta;
		ng.checkAllFeicoesGraficasMUB = false;
		ng.checkAllEspacializacaoDados = false;
		ng.checkAllGerenciaEspacializacao = false;
		
		switch(idTipoConsulta) {
		    case 1:
		    	ng.contentView = pathView+"feicoesgraficasmub.html";
		        break;
		    case 2:
		    	ng.contentView = pathView+"espacializacaodados.html";
		        break;		        
		    default:
		    	ng.contentView = pathView+"gerenciaespacializacao.html";
		    	break;
		}
	}
	
	ng.aplicarFiltros = function(){
		
		var filtro = {
			idTipoConsulta 			 : ng.idTipoConsultaSelecionado,
			estado		   			 : ng.estado,
			municipio	   			 : ng.municipio,
			localidade    			 : ng.localidade,
			situacaoUsuario			 : ng.situacaoUsuario,
			descricaoSituacaoUsuario : ng.situacaoUsuario==""?"Todos":"Ativos",
			usuario        			 : ng.usuario,
			dataInicio     			 : ng.dataInicio,
			dataInicioFormatada		 : ng.dataInicio?ng.formatarData(ng.dataInicio):"",
			dataFim        			 : ng.dataFim,
			dataFimFormatada		 : ng.dataFim?ng.formatarData(ng.dataFim):""
		}
		
		switch (ng.idTipoConsultaSelecionado) {
			case 1:
				ng.filtroFeicoesGraficasMUB = filtro;
				break;
			case 2:
				ng.filtroEspacializacaoDados = filtro;
				break;			
	
			default:
				ng.filtroGerenciaEspacializacao = filtro;
				break;
		}
		
	}
	
	ng.formatarData = function(date) {
	    var d = new Date(date || Date.now()),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();

	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;

	    return [day, month, year].join('/');
	}	
	
	ng.checkAtributo = function(item, checked){
		switch (ng.idTipoConsultaSelecionado) {
			case 1:
				 	if(checked){
				 		ng.listAtributosSelecionadosFeicoesGraficasMUB.push(item.id);
				 	}
				 	else{
				 		var index = ng.listAtributosSelecionadosFeicoesGraficasMUB.indexOf(item.id);
				 		ng.listAtributosSelecionadosFeicoesGraficasMUB.splice(index, 1);
				 	}
				 	break;
				 	
			case 2:
				 	if(checked){
				 		ng.listAtributosSelecionadosEspacializacaoDados.push(item.id);
				 	}
				 	else{
				 		var index = ng.listAtributosSelecionadosEspacializacaoDados.indexOf(item.id);
				 		ng.listAtributosSelecionadosEspacializacaoDados.splice(index, 1);
				 	}
				 	break;		 	
	
			default:
				 	if(checked){
				 		ng.listAtributosSelecionadosGerenciaEspacializacao.push(item.id);
				 	}
				 	else{
				 		var index = ng.listAtributosSelecionadosGerenciaEspacializacao.indexOf(item.id);
				 		ng.listAtributosSelecionadosGerenciaEspacializacao.splice(index, 1);
				 	}
				 	break;
		}
	}
	
	ng.checkAllAtributos = function(){

		switch (ng.idTipoConsultaSelecionado) {
			case 1:
					ng.listAtributosSelecionadosFeicoesGraficasMUB = [];
					for(var indice in ng.listAtributosFeicoesGraficasMUB){
						if(ng.checkAllFeicoesGraficasMUB){
							ng.listAtributosFeicoesGraficasMUB[indice].checked = true;
							ng.listAtributosSelecionadosFeicoesGraficasMUB.push(ng.listAtributosFeicoesGraficasMUB[indice].id);
						}
						else{
							ng.listAtributosFeicoesGraficasMUB[indice].checked = false;
						}
					}
					break;
					
			case 2:
					ng.listAtributosSelecionadosEspacializacaoDados = [];
					for(var indice in ng.listAtributosEspacializacaoDados){
						if(ng.checkAllEspacializacaoDados){
							ng.listAtributosEspacializacaoDados[indice].checked = true;
							ng.listAtributosSelecionadosEspacializacaoDados.push(ng.listAtributosEspacializacaoDados[indice].id);
						}
						else{
							ng.listAtributosEspacializacaoDados[indice].checked = false;
						}
					}
				
			default:
					ng.listAtributosSelecionadosGerenciaEspacializacao = [];
					for(var indice in ng.listAtributosGerenciaEspacializacao){
						if(ng.checkAllGerenciaEspacializacao){
							ng.listAtributosGerenciaEspacializacao[indice].checked = true;
							ng.listAtributosSelecionadosGerenciaEspacializacao.push(ng.listAtributosGerenciaEspacializacao[indice].id);
						}
						else{
							ng.listAtributosGerenciaEspacializacao[indice].checked = false;
						}
					}
		}
	}
	
	ng.consultar = function(){
		
		var filtro = ng.getFiltroGenerico();
    	
		if(ng.isCamposObrigatoriosValidos(filtro)){
			ng.isLoading = true;					
			estatisticaService.getEstatisticas(filtro, function(itens){
				ng.aplicarEstatisticas(itens);
			});	
		}				
	}
	
	ng.getFiltroGenerico = function(){
		
		var listaAtributos = '';
		
		switch(ng.idTipoConsultaSelecionado) {
		    case 1:
		    	listaAtributos = ng.listAtributosSelecionadosFeicoesGraficasMUB.toString();
		    	var filtro = ng.filtroFeicoesGraficasMUB;
		        break;
		    case 2:
		    	listaAtributos = ng.listAtributosSelecionadosEspacializacaoDados.toString();
		    	var filtro = ng.filtroEspacializacaoDados;
		        break;			        
		    default:
		    	listaAtributos = ng.listAtributosSelecionadosGerenciaEspacializacao.toString();
		    	var filtro = ng.filtroGerenciaEspacializacao;
		    	break;
		    	
		}
		
		var localSelecionado = '';
		
		if(filtro.localidade && filtro.localidade.id){
			localSelecionado = filtro.localidade.nome;
		}
		else if(filtro.municipio && filtro.municipio.id){
			localSelecionado = filtro.municipio.nome;
		}
		else if(filtro.estado && filtro.estado.uf){
			localSelecionado = filtro.estado.nome;
		}		
		
    	filtro.idTipoConsulta = ng.idTipoConsultaSelecionado;
    	filtro.listaAtributos = listaAtributos;
    	filtro.localSelecionado = localSelecionado;	
    	
    	return filtro;
	}
	
	ng.isCamposObrigatoriosValidos = function(filtro){
		var mensagem = "";
		if(filtro.localSelecionado == ""){
			mensagem = "O campo UF é obrigatório.<br/>";
		}
		if(filtro.listaAtributos == ""){
			mensagem = mensagem+"É obrigatória a seleção de no mínimo 1 atributo.";
		}
		if(mensagem != ""){
			$scope.showAlert({width: 330, mensagem: mensagem});
			return false;
		}
		return true;
	}
	
	ng.aplicarEstatisticas = function(estatisticas){
		switch(ng.idTipoConsultaSelecionado) {
		    case 1:
		    	if(estatisticas.length > 0){
		    		ng.habilitarBtnExportarFeicoesGraficasMUB = true;
		    	}
		    	else{
		    		ng.habilitarBtnExportarFeicoesGraficasMUB = false;
		    	}
		    	for(var indice in ng.listAtributosFeicoesGraficasMUB){
		    		for(var indiceEstatisticas in estatisticas){
			    		if(ng.listAtributosFeicoesGraficasMUB[indice].id == estatisticas[indiceEstatisticas].ENTITYID_ATRIBUTO){
			    			ng.listAtributosFeicoesGraficasMUB[indice].quantidade = estatisticas[indiceEstatisticas].QTDE_TOTAL;
			    		}
		    		}
		    		if(ng.listAtributosSelecionadosFeicoesGraficasMUB.indexOf(ng.listAtributosFeicoesGraficasMUB[indice].id) == -1){
		    			ng.listAtributosFeicoesGraficasMUB[indice].quantidade = "-";
		    		}	
		    		var aux = ng.listAtributosSelecionadosFeicoesGraficasMUB.indexOf(ng.listAtributosFeicoesGraficasMUB[indice].id); 
		    		if((aux != -1) && (ng.listAtributosFeicoesGraficasMUB[indice].quantidade == '-')){
		    			ng.listAtributosFeicoesGraficasMUB[indice].quantidade = 0;
		    		}		    		
		    	}
		        break;
		        
		    case 2:	  
		    	if(estatisticas.length > 0){
		    		ng.habilitarBtnExportarEspacializacaoDados = true;
		    	}
		    	else{
		    		ng.habilitarBtnExportarEspacializacaoDados = false;
		    	}		    	
		    	for(var indice in ng.listAtributosEspacializacaoDados){
		    		for(var indiceEstatisticas in estatisticas){
			    		if(ng.listAtributosEspacializacaoDados[indice].id == estatisticas[indiceEstatisticas].ENTITYID_ATRIBUTO){
			    			ng.listAtributosEspacializacaoDados[indice].espacializado = estatisticas[indiceEstatisticas].QTDE_ESPACIALIZADO;
			    			ng.listAtributosEspacializacaoDados[indice].naoEspacializado = estatisticas[indiceEstatisticas].QTDE_TOTAL - estatisticas[indiceEstatisticas].QTDE_ESPACIALIZADO;
			    			ng.listAtributosEspacializacaoDados[indice].porcentagem = ((estatisticas[indiceEstatisticas].QTDE_ESPACIALIZADO/estatisticas[indiceEstatisticas].QTDE_TOTAL)*100).toFixed(2) + ' %';
			    		}
		    		}
		    		if(ng.listAtributosSelecionadosEspacializacaoDados.indexOf(ng.listAtributosEspacializacaoDados[indice].id) == -1){
		    			ng.listAtributosEspacializacaoDados[indice].espacializado = "-";
		    			ng.listAtributosEspacializacaoDados[indice].naoEspacializado = "-";
		    			ng.listAtributosEspacializacaoDados[indice].porcentagem = "-";
		    		}
		    		var aux = ng.listAtributosSelecionadosEspacializacaoDados.indexOf(ng.listAtributosEspacializacaoDados[indice].id); 
		    		if((aux != -1) && (ng.listAtributosEspacializacaoDados[indice].espacializado == '-')){
		    			ng.listAtributosEspacializacaoDados[indice].espacializado = 0;
		    			ng.listAtributosEspacializacaoDados[indice].naoEspacializado = 0;
		    			ng.listAtributosEspacializacaoDados[indice].porcentagem = '100.00 %';
		    		}
		    	}
		    	break;
		        
		    default:
		    	if(estatisticas.length > 0){
		    		ng.habilitarBtnExportarGerenciaEspacializacao = true;
		    	}
		    	else{
		    		ng.habilitarBtnExportarGerenciaEspacializacao = false;
		    	}		    	
		    	for(var indice in ng.listAtributosGerenciaEspacializacao){
		    		for(var indiceEstatisticas in estatisticas){
			    		if(ng.listAtributosGerenciaEspacializacao[indice].id == estatisticas[indiceEstatisticas].ID_MOTIVO){
			    			ng.listAtributosGerenciaEspacializacao[indice].logradouro = estatisticas[indiceEstatisticas].QTDE_LOGRADOURO;
			    			ng.listAtributosGerenciaEspacializacao[indice].estacao = estatisticas[indiceEstatisticas].QTDE_ESTACAO;
			    			ng.listAtributosGerenciaEspacializacao[indice].caixaTerminal = estatisticas[indiceEstatisticas].QTDE_CX_TERMINAL;
			    			ng.listAtributosGerenciaEspacializacao[indice].secaoServico = estatisticas[indiceEstatisticas].QTDE_SEC_SERVICO;
			    		}
		    		}
		    		if(ng.listAtributosSelecionadosGerenciaEspacializacao.indexOf(ng.listAtributosGerenciaEspacializacao[indice].id) == -1){
		    			ng.listAtributosGerenciaEspacializacao[indice].logradouro = "-";
		    			ng.listAtributosGerenciaEspacializacao[indice].estacao = "-";
		    			ng.listAtributosGerenciaEspacializacao[indice].caixaTerminal = "-";
		    			ng.listAtributosGerenciaEspacializacao[indice].secaoServico = "-";
		    		}	
		    		var aux = ng.listAtributosSelecionadosGerenciaEspacializacao.indexOf(ng.listAtributosGerenciaEspacializacao[indice].id); 
		    		if((aux != -1) && (ng.listAtributosGerenciaEspacializacao[indice].logradouro == '-')){
		    			ng.listAtributosGerenciaEspacializacao[indice].logradouro = 0;
		    			ng.listAtributosGerenciaEspacializacao[indice].estacao = 0;
		    			ng.listAtributosGerenciaEspacializacao[indice].caixaTerminal = 0;
		    			ng.listAtributosGerenciaEspacializacao[indice].secaoServico = 0;
		    		}		    		
		    	}
		    	break;
		}
		ng.isLoading = false;
	}
	
	ng.limpar = function(){
		ng.estado = {};
		ng.municipio = {};
		ng.localidade = {};
		ng.localSelecionado = '';
		ng.situacaoUsuario = '';
		ng.usuario = {};
		ng.dataInicio = '';
		ng.dataFim = '';
		
		switch (ng.idTipoConsultaSelecionado) {
			case 1:
				ng.inicializaDadosAtributosFeicoesGraficasMUB();
				break;
			case 2:
				ng.inicializaDadosAtributosEspacializacaoDados();
				break;
			default:
			case 1:
				ng.inicializaDadosAtributosGerenciaEspacializacao();
				break;
		}
	}
	
	ng.exportar = function(tipoExportacao){
		
		ng.isLoading = true;
		
		var filtro = ng.getFiltroGenerico();
		
    	filtro.tipoExportacao = tipoExportacao;
		var downloadID = (new Date()).getTime();
		filtro.downloadID = downloadID;
		filtro.descricaoFiltro = ng.getDescricaoFiltro(filtro);
		
		estatisticaService.exportar(filtro);
		ng.isLoading = false; // excluir
		estatisticaService.onDownloadCompleto(downloadID, function(){
			ng.isLoading = false;
		});	
	} 
	
	ng.exportarDetalhado = function(tipoExportacao, idAtributo, flgEspacializado){
		
		ng.isLoading = true;
		
		var filtro = ng.getFiltroGenerico();
		
    	filtro.tipoExportacao = tipoExportacao;
    	filtro.indEspacializado = flgEspacializado;
    	filtro.idAtributo = idAtributo;
		var downloadID = (new Date()).getTime();
		filtro.downloadID = downloadID;		
		filtro.descricaoFiltro = ng.getDescricaoFiltro(filtro);
		
		estatisticaService.exportarDetalhado(filtro);
		ng.isLoading = false; // excluir
		estatisticaService.onDownloadCompleto(downloadID, function(){
			ng.isLoading = false;
		});			
	}
	
	ng.getDescricaoFiltro = function(filtro){
		
		var descricaoFiltro = (filtro.estado && filtro.estado.uf?filtro.estado.uf:"") +
							  (filtro.municipio && filtro.municipio.nome?" >> "+filtro.municipio.nome:"")+
							  (filtro.localidade && filtro.localidade.nome?" >> "+filtro.localidade.nome:"")+
							  (filtro.usuario && filtro.usuario.nome?" >> "+filtro.usuario.nome:"")+
							  (filtro.dataInicio?" >> "+ng.formatarData(filtro.dataInicio):"")+
							  (filtro.dataFim?" até "+ng.formatarData(filtro.dataFim):"");
		
		return descricaoFiltro;
	}

	ng.lodTiposConsulta();
	ng.loadEstados();
	ng.loadUsuarios();
	
});