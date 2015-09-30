/**
 * Created by shekhargulati on 10/06/14.
 */

var app = angular.module('todoapp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/list.html',
        controller: 'ListCtrl'
    }).when('/create', {
        templateUrl: 'views/create.html',
        controller: 'CreateCtrl'
    }).when('/sign2', {
    	templateUrl: 't.html',
    	controller: 'sign2'
    }).when('/conn2', {
    	templateUrl: 't.html',
    	controller: 'conn2'
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('ListCtrl', function ($scope, $http) {
    $http.get('/api/v1/todos').success(function (data) {
        $scope.todos = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })

    $scope.todoStatusChanged = function (todo) {
        console.log(todo);
        $http.put('/api/v1/todos/' + todo.id, todo).success(function (data) {
            console.log('status changed');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }    
});
app.controller('sign2', function ($scope, $http, $window, $cookieStore) {
	
	 $scope.ReadCookie = function () {
	        $http.post('/sign').success(function (data) {
	        	
	        	
	        	var asd = $cookieStore.get('publicKey');
	       // 	asd = asd.replace(/([\[:])?(\d+)([,\}\]])/g, "$1\"$2\"$3");
	        	var kkk = JSON.parse(asd);
	        	
	        	var rsa_key = {
		        		"n": kkk.n,
		        		"e": kkk.e
		        	};
	        	var cryptographer = new Jose.WebCryptographer();
	        	cryptographer.setContentEncryptionAlgorithm("A128GCM");
	        	var public_rsa_key = Jose.Utils.importRsaPublicKey(rsa_key, "RSA-OAEP");
	        	var encrypter = new JoseJWE.Encrypter(cryptographer, public_rsa_key);
	        	encrypter.encrypt("okay").then(function(result) {
	        		console.log(result);
	        		  $http.post('/conn', result).success(function (data) {
	        			  console.log(data);
	        		    }).error(function (data, status) {
	        		    	console.log(data);
	        		    })      			        		
	        	    });
	        })
	    }

});

app.controller('conn2', function ($scope, $http, $window){
	    $http.get('/conn'
	    ).success(function (data) {
	    	$window.alert(data);
	    }).error(function (data, status) {
	    	$window.alert(data);
	    })
	

});




app.controller('CreateCtrl', function ($scope, $http, $location) {
    $scope.todo = {
        done: false
    };
    $scope.createTodo = function () {
        console.log($scope.todo);
        $http.post('/api/v1/todos', $scope.todo).success(function (data) {
            $location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});