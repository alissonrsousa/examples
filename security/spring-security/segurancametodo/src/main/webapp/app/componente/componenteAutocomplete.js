moduleAplicacao.directive('autocomplete', function($rootScope, $filter) {
    return {
        transclude: true,
        replace:true,
        restrict: 'E,A',
        template: '<span> '+
		          '  <input type="text" '+ 
		          '		    class="form-control input-sm" '+
		          '		    ng-model="inputText" '+ 
		          '		    ng-click="commandActivate()"  '+
		          '		    ng-change="metodoFiltrar(inputText)" '+
		          '			ng-required="obrigatorio" '+
		          '		  /> '+
		          '		  <div class="autocomplete options"> '+
		          '		      <div ng-repeat="item in filteredItems | limitTo:maxOptions"> '+
		          '		        <div  '+
		          '		            ng-class="{ autocomplete:1, item:1, highlight: ($index == state.highlightedIndex) }" '+
		          '		            ng-click="commandClick(item)"  '+
		          '		            ng-mouseover="highlightItem(item)" '+
		          '		            ng-transclude> '+
		          '		        </div> '+
		          '		      </div> '+
		          '		      <div ng-show="filteredItems.length == 0" class="autocomplete noitems"> '+
		          '		        {{noItemText}} '+
		          '		      </div>       '+
		          '		  </div> '+
	        	  '	</span>',
        isolate:false,
              
        scope: {
          model:'=ngModel',
          items:'=',
          inputText:"=",
          metodoFiltrar: '=',
          noItemText:'@testItemText',
          formatter:'&',
          keyField:'@',
          textField:'@',
          maxOptions:'@',
          noItemText:'@',
          obrigatorio:'='
        },
        
        link: function($scope, element, attrs) {
        	
          element.keydown(function(e){
        	  $scope.commandKeyDown(e);
  		  });
          
          element.keyup(function(e){
        	  $scope.commandKeyUp(e);
  		  });
          
          element.focus(function(e){
        	  $scope.commandActivate();
  		  });
          
          function isNullOrUndefined(o) {
            return !angular.isDefined(o) || o === null;
          }
          
          function valueOrDefault(o, defaultValue) {
            //console.log(isNullOrUndefined(o));
            //console.log(o);
            if(isNullOrUndefined(o)) {
              return defaultValue;
            }
            return o;
          }
          
          function nonEmptyValueOrDefault(o, defaultValue) {
            if(isNullOrUndefined(o) || o.toString() === '') {
              return defaultValue;
            }
            return o;
          }
          
          function guid() {
            function s4() {
              return Math.floor((1 + Math.random()) * 0x10000)
                         .toString(16)
                         .substring(1);
            }
            return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
                   s4() + '-' + s4() + s4() + s4();
          }
          
          $scope.filterItems = function() {
        	  $scope.filteredItems = $scope.items;
//            $scope.filteredItems = $filter('filter')($scope.items, function(o) {
//              if(!$scope.inputText || ($scope.inputText && $scope.inputText.length === 0 ))
//                return true;
//              
//              return (o[$scope.textField] || '').toUpperCase().indexOf($scope.inputText.toUpperCase()) >= 0;
//            });
          };
          
          $scope.commandKeyDown = function($event) {  
            if($event.which == 9 && $scope.state.isOpen) {
              //console.log('tab' +$scope.inputText);
              if($scope.filteredItems.length == 1) {
                $scope.commandDone();
              } else {
                $scope.commandHide();
              }
              return;
            }
          };
          
          $scope.commandKeyUp = function($event) { 
            if($event.which == 13 && $scope.state.isOpen) {
              $scope.commandDone();
              return;
            }
                  
            if(!$scope.state.isOpen) {
              $scope.commandActivate();
              return;
            }          
            
            if($event.which == 40 && $scope.state.isOpen) {
              $scope.commandDown();
              return; 
            }
              
            if($event.which == 38 && $scope.state.isOpen) {
              $scope.commandUp();
              return;
            }
              
            if($event.which == 27 && $scope.state.isOpen) {
              $scope.commandHide();  
              return;
            }
          };
                                          
          $scope.commandDown = function(){
            if( $scope.state.isOpen ) {
              if($scope.state.highlightedIndex >= ($scope.filteredItems.length-1)) 
                return;
              $scope.state.highlightedIndex++;
            } else {
              $scope.commandActivate();
            }
          };
          
          $scope.commandUp = function(){
            if($scope.state.highlightedIndex <= 0) 
                return;
                
            $scope.state.highlightedIndex--;
          };
          
          $scope.commandHide = function(){
            $scope.state.isOpen = false;
            if(angular.isDefined($scope.model[$scope.textField])) {
              $scope.inputText = $scope.model[$scope.textField];
            }
          };
          
          $scope.commandDone = function() {           
            if( !$scope.state.isOpen )
              return;
              
            $scope.model = ($scope.state.highlightedIndex < $scope.filteredItems.length)
                            ? $scope.filteredItems[$scope.state.highlightedIndex]
                            : null;
            
            if( $scope.model !== null && angular.isDefined($scope.model[$scope.textField])) {
              $scope.inputText = $scope.model[$scope.textField];
              if(angular.isDefined($scope.model['isNew']))  {
                delete $scope.model['isNew'];
              }
            } else {
              $scope.model = {};
              $scope.model[$scope.keyField] = -1;
              $scope.model[$scope.textField] = $scope.inputText;
              $scope.model['isNew'] = true;
            }
            
            $scope.state.isOpen = false;
          };
          
          $scope.commandActivate = function() {
            $scope.wasCommanded = true;
            $scope.state.highlightedIndex = 0;
            $scope.state.isOpen = true; 
            $scope.state.lastOpenGuid = guid();          
            $rootScope.$broadcast('autocomplete.open', $scope.state.lastOpenGuid);
          };
          
          $scope.commandClick = function(item) {
            $scope.highlightItem(item);
            $scope.commandDone();
          };
          
          $scope.highlightItem = function(item) {
            $scope.state.highlightedIndex = -1;
            for( var i=0; i<$scope.filteredItems.length; ++i) {
              if( $scope.filteredItems[i][$scope.keyField] === item[$scope.keyField]) {
                $scope.state.highlightedIndex = i;    
                break;
              }
            }          
          };     
          
          var optionsBox = element.find('.autocomplete.options');        
          $scope.inputText = '';
          
          $scope.state = { 
            highlightedIndex: 0, 
            isOpen: false,
            selectedItem: { },
            lastOpenGuid: '',
            wasCommanded: false
          }
          
          var defaultValues = {
            keyField: 'id',
            textField: 'text',
            maxOptions: 10,
            noItemText: 'No matches found'
          };
          
          for(var k in defaultValues) {
            (function (attrName) {
              attrs.$observe(attrName, function(value){
                if(isNullOrUndefined(value)) {
                  $scope[attrName] = defaultValues[attrName];
                }
              });
            })(k);
          }
          
          optionsBox.css({opacity: 0.0, visibility: "hidden"});
          
          $scope.$watch('inputText', function(){
            $scope.filterItems();          
          });
          
          $scope.$watch('state.isOpen', function(value){
            if($scope.state.isOpen) {              
                optionsBox.css({opacity: 0.0, visibility: "visible"}).animate({opacity: 1.0, visibility: "visible"});
                
            } else {
                optionsBox.css({opacity: 0.0, visibility: "hidden"});              
            }          
          });          
          
          $scope.$on('autocomplete.open', function(e, guid) {
            if($scope.state.isOpen && guid !== $scope.state.lastOpenGuid) {
              $scope.commandHide();
            }
          });
        }
      };
  });
