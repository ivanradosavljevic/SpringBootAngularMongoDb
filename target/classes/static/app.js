App.controller('docController', ['$scope', '$rootScope', 'docService', '$http', function($scope, $rootScope, docService, $http, ) {

    $scope.file = '';
    $scope.ingestors = {};
    $scope.totalNumberOfRecords = '';
    $scope.ingestors.data = [];
    $scope.upload = function() {
        var file = $scope.file;
        docService.saveDoc(file)
            .then(
                function(response) { 
                alert(response.message);                  
                    $http.get("http://localhost:8080/doc/").success(
                        function(response) {
                            $rootScope.docList = response;                             
                        });
                },
                function(errResponse) {					 
                     $scope.data.error = { message: error, status: status};
                     console.log(errResponse);
                }
            );
    }
    $scope.selected = [];

    $scope.query = {
        order: 'clientName',
        limit: 20,
        page: 1
    };
    $scope.logPagination = function(page, limit) {
        console.log('page: ', page);
        console.log('limit: ', limit);
        $http({
            method: 'GET',
            url: 'http://localhost:8080/doc/rest/' + page + '/' + limit
        }).then(function successCallback(response) {
            $scope.ingestors.data = response.data;
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    }
    $scope.details = function(item) {
        $scope.ingestors.dataForAlert = [];
        $http({
            method: 'GET',
            url: 'http://localhost:8080/doc/rest/' + item.clientId
        }).then(function successCallback(response) {
            $scope.ingestors.dataForAlert = response.data;
            var sum = 0;
            var listOfNames = 'List of names:\n';
            for (var i = 0; i < $scope.ingestors.dataForAlert.length; i++) {
                var ingestor = $scope.ingestors.dataForAlert[i];
                sum += ingestor.amount;
                listOfNames += ingestor.fileName + '\n'
            }

            alert('Client name: ' + item.clientName + '\nCreated on: '+item.inputDate+'\nTotal Records: '+$scope.ingestors.dataForAlert.length+'\nAmount: ' + sum + '\n' + listOfNames);
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    };
	 $scope.logOrder = function (order) {
    	console.log('order: ', order);
  	};
    $scope.getIngestors = function() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/doc/rest/init'
        }).then(function successCallback(response) {
        	$scope.totalNumberOfRecords = response.data.numberOfRecords;
            $scope.ingestors.data = response.data.list;
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    }
    
    $scope.getIngestors();
}]);

App.constant('urls', {
    DOC_URL: 'http://localhost:8080/doc/'
});
App.directive('fileModel', ['$parse', function($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function() {
                scope.$apply(function() {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

App.run(function($rootScope, $http) {
    $http.get("http://localhost:8080/doc/").success(
        function(response) {
            $rootScope.docList = response;
        });
});