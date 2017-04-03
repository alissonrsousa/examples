moduleAplicacao = angular.module('aplicacao', ['ui.mask', 'ui.validate', 'angularFileUpload']); 

moduleAplicacao.controller("init", function($scope, $compile, $window, securityService){
	
	var ng = $scope.ctrl_init = {};
	
	Util.changeHeightSite();
	
	$(window).resize(function(){
		Util.changeHeightSite();
	});
	
	$("#linkActionMenuLayers").click(function(){
		if($(this).hasClass('closeLinkMenu')){
			$(this).removeClass('closeLinkMenu');
		}else{
			$(this).addClass('closeLinkMenu');
		}
		$( "#blocoContentLayers" ).toggle(400);
	});
	
	$scope.openWindow = function(config){
		var configDefault = {
			windowClass: 'panel-item1',
			width:500,
			height:500,
			modal:false,
			windowNumber:1,
			minimize:'#botoes-caixas',
			containment: '#corpo',
			itemToolbarMinimizedClass: 'btn-menu-item1'
		};
		
		var cfg = $.extend(configDefault,config);
		cfg.windowClass = 'panel ' + cfg.windowClass;
		cfg.itemToolbarMinimizedClass = 'btn btn-menu-basico btn-sm ' + cfg.itemToolbarMinimizedClass;
		
		var janela;
		
		if(cfg.windowNumber == 1){
			janela = $("#modal-"+cfg.controller);
			
			if(janela.window("isOpen") === true){
				janela.window("moveToTop");
				janela.window("unminimize");
			}else{
				janela = $('<div id="modal-'+cfg.controller+'" ng-controller="'+cfg.controller+'" ng-include="ctrl_'+cfg.controller+'.contentView" style="height:100%;"></div>');
				
				var fnLink = $compile(janela);
				fnLink($scope);
				
				janela.window(cfg);
			}			
		}
		else{
			janela = $("#modal"+cfg.windowNumber+"-"+cfg.controller);
			
			while(janela.window("isOpen") === true){
				janela = $("#modal"+cfg.windowNumber+"-"+cfg.controller);
			}
			
			janela = $('<div id="modal'+cfg.windowNumber+'-'+cfg.controller+'" ng-controller="'+cfg.controller+'" ng-include="ctrl_'+cfg.controller+'.contentView'+cfg.windowNumber+'" style="height:100%;"></div>');
			
			var fnLink = $compile(janela);
			fnLink($scope);
			
			janela.window(cfg);			
		}
		

	}

	$scope.showAlert = function(config){
		
		var configDefault = {
			tipo: 'alert-warning', //alert-danger, alert-info ou alert-warning
			width: 200,
			mensagem: '',
			showButtonClose: true,
			timeoutClose: 5000,
			modal: true
		};
			
		var cfg = $.extend(configDefault,config);
		
		var div = $('<div class="alert" style="z-index: 9999 !important;display:none;position:absolute;top:20px;left:50%;margin-left:-'+(Math.ceil(cfg.width/2))+'px;"></div>');
		var buttonClose = $('<button aria-hidden="true" data-dismiss="alert" class="close" type="button">×</button>');
		
		div.addClass(cfg.tipo);
		div.width(cfg.width);
		if(cfg.showButtonClose){
			div.append(buttonClose);
		}
		
		div.append(cfg.mensagem);
		
		$("body").append(div);
		div.show(500, function(){
			window.setTimeout(function(){
				div.hide(500);
			},cfg.timeoutClose);
		});
	}
	
	$scope.showConfirm = function(config){
		
		var configDefault = {
			tipo: 'alert-danger', //alert-danger, alert-info ou alert-warning
			width: 200,
			mensagem: '',
			controller: '',
			metodoConfirmar: '',
			showButtonClose: true
		};
		
		var cfg = $.extend(configDefault,config);
		
		var div = $('<div id="modal-confirm" class="alert '+cfg.tipo+' fade in" style="z-index: 9999 !important;display:none;position:absolute;top:11px;left:40%;margin-left:-'+(Math.ceil(cfg.width/2))+'px;">'+
				    	'<button id="btnCloseModalConfirm" type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'+
						'<div align="center"><h4>Atenção!</h4></div><p>'+cfg.mensagem+'</p>'+
						'<p><div align="center"><button onclick="angular.element(\'#modal-'+cfg.controller+'\').scope().ctrl_'+cfg.controller+'.'+cfg.metodoConfirmar+'(); $(\'#btnCloseModalConfirm\').click()" type="button" class="btn btn-danger">Sim</button>'+
						'   <button type="button" class="btn btn-default" onclick="$(\'#btnCloseModalConfirm\').click()">Cancelar</button></div></p>'+
					'</div>');
		
		$("#modal-"+cfg.controller).append(div);
		div.show();
	}

	$scope.statusBotao = {};
	
	setInterval(function() {
		securityService.keepAlive();
	}, 180 * 1000);

});