moduleAplicacao.directive('filtro', function() {
	return {
		restrict: 'E',
		template: '<div style="position:relative;display:block;">' +
				  '<div class="filtros-ativos">' +
				  '    	<div class="lbl-filtros-ativos">Filtros ativos</div>' +
				  '    	<div class="conj-filtros" ng-repeat="(index, item) in itens">' +
				  '        	<span class="titulo-filtro"><a title="{{item.tooltip}}">{{item.text}}</a></span>' +
				  '    		<button type="button" class="close-filtro" ng-click="deleteItem(index)" ng-show="item.delete">&times;</button>' +
				  '    	</div>' +
				  '</div>' +
				  '	<div class="btn-group pull-right" ng-show="showButtonFiltros" style="position:absolute;right:2px;top:2px;">' +
				  '		<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">' +
				  ' 		<span class="caret"></span>' +
				  '		</button>' +
				  '		<ul class="dropdown-menu pull-right" role="menu">' +
				  ' 		<li role="presentation" ng-class="{disabled:!item.delete}" ng-repeat="(index, item) in itens">' +
				  ' 			<a ng-show="item.delete" role="menuitem" title="{{item.tooltip}}" ng-click="deleteItem(index)"><i class="icone-excluir-item"></i> {{item.text}}</a>' +
				  ' 			<a ng-show="!item.delete" role="menuitem" title="{{item.tooltip}}"><i class="icone-espaco"></i> {{item.text}}</a>' +
				  ' 		</li>' +
				  '		</ul>' +
				  '	</div>' +
				  '	</div>',
		scope: {
			itens: '=',
			fnDelete: '='
		},
		link : function(scope, element, attrs){
			var timeoutId = window.setInterval(function() {
				if($(".filtros-ativos").get(0).scrollHeight != $(".filtros-ativos").height()){
					scope.$apply(function () {
						scope.showButtonFiltros = true;
                    });
				}else{
					scope.$apply(function () {
						scope.showButtonFiltros = false;
                    });
				}
		    }, 250);
			
			element.on('$destroy', function() {
				window.clearInterval(timeoutId);
			});
		},
		controller : function($scope) {
			
			$scope.showButtonFiltros = false;
			
			$scope.deleteItem = function(index){
				if($scope.fnDelete($scope.itens[index])){
					$scope.itens.splice(index, 1);
				}
			}
		}
	};
});