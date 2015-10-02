var app = angular.module('justTagServer', [ 'ngCookies', 'ngResource',
		'ngSanitize', 'ngRoute' ]);

app.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'views/list.html',
		controller : 'ListCtrl'
	}).when('/login', {
		templateUrl : 'views/user/login.html',
		controller : 'LoginCtrl'
	}).when('/sign', {
		templateUrl : 'views/user/signup.html',
		controller : 'sginCtrl'
	}).when('/create', {
		templateUrl : 'views/create.html',
		controller : 'CreateCtrl'
	}).otherwise({
		redirectTo : '/'
	})
});

app.controller('ListCtrl', function($scope, $http, $cookieStore, $location) {
	if(typeof $cookieStore.get('token') == "undefined"){
		$location.path('/login');
	}
		
	
	$http.get('/api/v1/todos').success(function(data) {
		$scope.todos = data;
	}).error(function(data, status) {
		console.log('Error ' + data)
	})

	$scope.todoStatusChanged = function(todo) {
		console.log(todo);
		$http.put('/api/v1/todos/' + todo.id, todo).success(function(data) {
			console.log('status changed');
		}).error(function(data, status) {
			console.log('Error ' + data)
		})
	}
		
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
app.controller('LoginCtrl', function($scope, $http, $location, RSAService, $cookieStore) {
	RSAService.getKey();
	$scope.changeSingUpPage = function() {
		$location.path('/sign');
	}
	$scope.login = function(data) {
		var user = "{"
				+"\"user_id\" : \""+$scope.user_id+"\" ,"
				+"\"user_password\" : \""+$scope.user_password+"\""
			+"}";
		RSAService.encrypt(user).then(function(result) {
			$http.post('/login', result).success(function(data) {
				$cookieStore.put('token', data);
				$location.path('/');
			}).error(function(response) {
			   console.log("error");
			  });
		});	
	}
});


app.controller('sginCtrl', function($scope, $http, $location, RSAService) {
	RSAService.getKey();
	$scope.createuser = function(data) {
		if($scope.user_password != $scope.user_confirm_password){
			console.log($scope.user_password);
			console.log($scope.user_confirm_password);
		}else{
		var users = "{"
				+"\"user_id\" : \""+$scope.user_id+"\" ,"
				+"\"user_password\" : \""+$scope.user_password+"\" ,"
				+"\"user_email\" : \""+$scope.user_email+"\""
			+"}";
		RSAService.encrypt(users).then(function(result) {
			$http.post('/user', result).success(function(data) {
				console.log(data);
			});
		});
		}
		
	}
});

app.service('RSAService', function($cookieStore, $http) {

	this.getKey = function() {
		$http.get('/key').success(function(data) {
			$cookieStore.put('publicKey', data);
			});
		};
			this.encrypt = function(payload) {
				this.getKey();
				var publicKey = $cookieStore.get('publicKey');
				var cryptographer = new Jose.WebCryptographer();
				cryptographer.setContentEncryptionAlgorithm("A128GCM");
				var public_rsa_key = Jose.Utils.importRsaPublicKey(publicKey, "RSA-OAEP");
				var encrypter = new JoseJWE.Encrypter(cryptographer, public_rsa_key);
				var encryptData = encrypter.encrypt(payload);
				$cookieStore.remove('publicKey');
				return encryptData;
			};
});
