var app = angular.module('justTagServer', [ 'ngCookies', 'ngResource',
		'ngSanitize', 'ngRoute' ]);

app.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'views/user/login.html',
		controller : 'LoginCtrl'
	}).when('/sign', {
		templateUrl : 'views/user/signup.html',
		controller : 'CreateCtrl'
	}).when('/list', {
		templateUrl : 'views/list.html',
		controller : 'ListCtrl'
	}).when('/create', {
		templateUrl : 'views/create.html',
		controller : 'CreateCtrl'
	}).when('/sign2', {
		templateUrl : 't.html',
		controller : 'sign2'
	}).otherwise({
		redirectTo : '/'
	})
});

app.controller('ListCtrl', function($scope, $http) {
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

app.controller('LoginCtrl', function($scope, $http, $location, RSAService) {
	$http.post('/sign').success(function(data) {
		RSAService.encrypt('asd').then(function(result) {
			console.log(result);
			$http.post('/conn', result).success(function(data) {
				console.log(data);
			}).error(function(data, status) {
				console.log(data);
			})

		});
	});

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
				console.log(data);
			});
		});
		
		
	}
});




app.controller('sign2', function($scope, $http, RSAService) {

	$scope.ReadCookie = function() {
		$http.post('/sign').success(function(data) {
			RSAService.encrypt('asd').then(function(result) {
				console.log(result);
				$http.post('/conn', result).success(function(data) {
					console.log(data);
				}).error(function(data, status) {
					console.log(data);
				})

			});
		});
	}

});

app.controller('CreateCtrl', function($scope, $http, $location, RSAService) {
	$scope.createuser = function(data) {
		if($scope.user_password != $scope.user_confirm_password){
		//	$windows.alret('비밀번호가 틀립니다.');
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

app.service('RSAService',
		function($cookieStore) {
			this.encrypt = function(payload) {
				var publicKey = $cookieStore.get('publicKey');
				var key = JSON.parse(publicKey);
				var rsa_key = {
					"n" : key.n,
					"e" : key.e
				};
				var cryptographer = new Jose.WebCryptographer();
				cryptographer.setContentEncryptionAlgorithm("A128GCM");
				var public_rsa_key = Jose.Utils.importRsaPublicKey(rsa_key,
						"RSA-OAEP");
				var encrypter = new JoseJWE.Encrypter(cryptographer,
						public_rsa_key);
				var encryptData = encrypter.encrypt(payload);
				return encryptData;
			};
		});