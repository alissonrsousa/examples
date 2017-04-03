moduleAplicacao.directive('componentePaginacao', function() {
		
	return {
		template   : '<div id="ferramentas" class="">  '+
					 '	<button ng-click="voltarPrimeiraPagina()" data-placement="bottom" data-toggle="tooltip" title="Primeiro" class="btn btn-ferramenta btn-sm" type="button"><i id="btnVoltarPrimeiraPagina" class="icone-primeiro-disable"></i></button>  '+
	        		 '	<button ng-click="voltarPagina()" data-placement="bottom" data-toggle="tooltip" title="Anterior" class="btn btn-ferramenta btn-sm" type="button"><i id="btnVoltarPagina" class="icone-anterior-disable"></i></button>  '+
	        		 '	<span class="divider-vertical-big"></span>  '+
	        		 '	<div class="btn-ferramenta">  '+
	            	 '		<form id="paginacao" class="form-inline" role="form">  '+
	              	 '			<div class="form-group">Página</div>  '+
	              	 '			<div class="form-group"><input ng-model="paginaAtual" ng-change="atualizarPagina()" class="form-control input-sm col-xs-1" style="width: 40px !important;"></div>  '+
	              	 '			<div class="form-group">de {{quantidadePaginas}}</div>  '+
	            	 '		</form>  '+
	        		 '	</div>  '+
	        		 '	<span class="divider-vertical-big"></span>  '+
	        		 '	<button ng-click="avancarPagina()" data-placement="bottom" data-toggle="tooltip" title="Próximo" class="btn btn-ferramenta btn-sm" type="button"><i id="btnAvancarPagina" class="icone-proximo-disable"></i></button>  '+
	        		 '	<button ng-click="avancarUltimaPagina()" data-placement="bottom" data-toggle="tooltip" title="Último" class="btn btn-ferramenta btn-sm" type="button"><i id="btnAvancarUltimaPagina" class="icone-ultimo-disable"></i></button>  '+
					 '</div>',
		replace    : true,
		transclude : true,
		restrict   : 'E',
		scope      : {
			paginaAtual    : "=",
			tamanhoPagina  : "=",
			totalRegistros : "=",
			metodoBuscar   : '='
		},
		controller : function($scope) {

			$scope.$watch('totalRegistros', function(newVal, oldVal){
				if(newVal != oldVal){
					$scope.paginaAtual = 1;
				}
				$scope.totalRegistros = newVal;
				$scope.calculaQuantidadePaginas();
				$scope.habilitaDesabilitaBotoes();
			  });
			
			$scope.voltarPrimeiraPagina = function(){
				if($scope.paginaAtual && $scope.paginaAtual != 1){
					$scope.paginaAtual = 1;
					$scope.metodoBuscar($scope.getPrimeiroRegistroPagina(), $scope.tamanhoPagina);
					$scope.habilitaDesabilitaBotoes();
				}
			}
			$scope.voltarPagina = function(){
				if($scope.paginaAtual > 1){
					$scope.paginaAtual--;
					$scope.atualizarPagina();
				}
				$scope.habilitaDesabilitaBotoes();
			}
			$scope.avancarPagina = function(){
				if($scope.paginaAtual < $scope.quantidadePaginas){
					$scope.paginaAtual++;
					$scope.atualizarPagina();
				}
				$scope.habilitaDesabilitaBotoes();

			}
			$scope.avancarUltimaPagina = function(){
				if($scope.paginaAtual && ($scope.paginaAtual != $scope.quantidadePaginas)){
					$scope.paginaAtual = $scope.quantidadePaginas;
					$scope.metodoBuscar($scope.getPrimeiroRegistroPagina(), $scope.tamanhoPagina);
					$scope.habilitaDesabilitaBotoes();
				}
			}
			$scope.atualizarPagina = function(){
				if($scope.totalRegistros > 0){
					if($scope.paginaAtual > $scope.quantidadePaginas){
						$scope.paginaAtual = "";
					}
					else {
						$scope.metodoBuscar($scope.getPrimeiroRegistroPagina(), $scope.tamanhoPagina);
						$scope.habilitaDesabilitaBotoes();
					}
				}
				else{
					$scope.paginaAtual = "";
				}
			}
			$scope.calculaQuantidadePaginas = function(){
				var mode = $scope.totalRegistros % $scope.tamanhoPagina;
				$scope.quantidadePaginas = $scope.totalRegistros / $scope.tamanhoPagina;
				if(mode != 0){
					$scope.quantidadePaginas = parseInt($scope.quantidadePaginas)+1;
				}
			}
			$scope.getPrimeiroRegistroPagina = function() {
				var indice = ($scope.paginaAtual*$scope.tamanhoPagina)-($scope.tamanhoPagina-1);
				if(indice < 1){
					indice = 1;
				}
				$scope.primeiroRegistroPagina = indice;
				return indice;
			}
			$scope.habilitaDesabilitaBotoes = function(){
				if(!$scope.paginaAtual || ($scope.quantidadePaginas && $scope.quantidadePaginas < 2)){
					$("#btnVoltarPrimeiraPagina").removeClass("icone-primeiro");
					$("#btnVoltarPrimeiraPagina").addClass("icone-primeiro-disable");
					$("#btnVoltarPagina").removeClass("icone-anterior");
					$("#btnVoltarPagina").addClass("icone-anterior-disable");
					$("#btnAvancarUltimaPagina").removeClass("icone-ultimo");
					$("#btnAvancarUltimaPagina").addClass("icone-ultimo-disable");
					$("#btnAvancarPagina").removeClass("icone-proximo");
					$("#btnAvancarPagina").addClass("icone-proximo-disable");
				}
				else if($scope.paginaAtual == 1){
					$("#btnVoltarPrimeiraPagina").removeClass("icone-primeiro");
					$("#btnVoltarPrimeiraPagina").addClass("icone-primeiro-disable");
					$("#btnVoltarPagina").removeClass("icone-anterior");
					$("#btnVoltarPagina").addClass("icone-anterior-disable");
					$("#btnAvancarUltimaPagina").removeClass("icone-ultimo-disable");
					$("#btnAvancarUltimaPagina").addClass("icone-ultimo");
					$("#btnAvancarPagina").removeClass("icone-proximo-disable");
					$("#btnAvancarPagina").addClass("icone-proximo");
				}
				else if($scope.paginaAtual == $scope.quantidadePaginas){
					$("#btnVoltarPrimeiraPagina").removeClass("icone-primeiro-disable");
					$("#btnVoltarPrimeiraPagina").addClass("icone-primeiro");
					$("#btnVoltarPagina").removeClass("icone-anterior-disable");
					$("#btnVoltarPagina").addClass("icone-anterior");
					$("#btnAvancarUltimaPagina").removeClass("icone-ultimo");
					$("#btnAvancarUltimaPagina").addClass("icone-ultimo-disable");
					$("#btnAvancarPagina").removeClass("icone-proximo");
					$("#btnAvancarPagina").addClass("icone-proximo-disable");
				}
				else{
					$("#btnVoltarPrimeiraPagina").removeClass("icone-primeiro-disable");
					$("#btnVoltarPrimeiraPagina").addClass("icone-primeiro");
					$("#btnVoltarPagina").removeClass("icone-anterior-disable");
					$("#btnVoltarPagina").addClass("icone-anterior");
					$("#btnAvancarUltimaPagina").removeClass("icone-ultimo-disable");
					$("#btnAvancarUltimaPagina").addClass("icone-ultimo");
					$("#btnAvancarPagina").removeClass("icone-proximo-disable");
					$("#btnAvancarPagina").addClass("icone-proximo");
				}
			}
			
		}
	};
})