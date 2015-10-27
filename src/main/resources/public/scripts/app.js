var app = angular.module('justAuth', [ 'ngCookies', 'ngResource',
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
	}).when('/userlist', {
		templateUrl : 'views/userlist.html',
		controller : 'UserList'
	}).when('/user', {
		templateUrl : 'views/user/userinfo.html',
		controller : 'UserInfo'
	}).otherwise({
		redirectTo : '/'
	})
});

app.controller('ListCtrl', function($scope, $http, $cookieStore, $location) {
	var token = $cookieStore.get('token');
	if(typeof token == "undefined" || $cookieStore.get('token').length<1){
		$location.path('/login');
	}
	
	$http.get('/token'+'/'+token).success(function(data) {
		if(data === '"notFoundToken"' || data === '"tokenExpired"'|| data === '"tokenUpdateFail"' || data === '"unknownError"'){
			$location.path('/login');
		}else if(data !== '"success"'){
			$cookieStore.put('token', data);
		}
	});

	$http.get('/boards', {headers: {'token': $cookieStore.get('token')}}).success(function(data) {
		console.log(data);
		$scope.boards = data;
	}).error(function(data, status) {
		console.log('Error ' + data)
	})

	$scope.boardStatusChanged = function(board) {
		console.log(board);
		$http.put('/boards/' + board.id, board).success(function(data) {
			console.log('status changed');
		}).error(function(data, status) {
			console.log('Error ' + data)
		})
	}
	$scope.logout = function(){
			$http.delete('/token'+'/'+token).success(function(data) {
				console.log(data);
				$location.path('/login');
			}).error(function(data, status) {
				console.log(data);
			})
	}
	$scope.custom = true;
		
});
app.controller('CreateCtrl', function ($scope, $http, $location, $cookieStore) {
	
    $scope.createboard = function () {
        console.log($scope.board);
        $http.post('/boards', $scope.board, {headers: {'token': $cookieStore.get('token')}}).success(function (data) {
            $location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});

app.controller('UserList', function ($scope, $http, $location ,$cookieStore) {
	var token = $cookieStore.get('token');
	$http.get('/users', {headers: {'token': $cookieStore.get('token')}}).success(function (data) {
		console.log(data);
		$scope.users = data;
	}).error(function (data, status) {
        console.log('Error ' + data)
    });
	$scope.logout = function(){
		$http.delete('/token'+'/'+token).success(function(data) {
			console.log(data);
			$location.path('/login');
		}).error(function(data, status) {
			console.log(data);
		})
}

});


app.controller('UserInfo', function ($scope, $http, $location ,$cookieStore, $window) {
	var token = $cookieStore.get('token');
	$http.get('/user', {headers: {'token': $cookieStore.get('token')}}).success(function(data) {
		console.log(data);
		$scope.user = data;
	}).error(function (data, status) {
        console.log('Error ' + data)
    });
	
	$scope.leave =function(){
		$http.delete('/user', {headers: {'token': $cookieStore.get('token')}}).success(function(data) {
			console.log(data);
			$cookieStore.remove('token');
			$window.alert('회원이 탈퇴되었습니다.');
			$location.path('/');
		}).error(function (data, status) {
	        console.log('Error ' + data)
	    });
	}

	$scope.logout = function(){
		$http.delete('/token'+'/'+token).success(function(data) {
			console.log(data);
			$location.path('/login');
		}).error(function(data, status) {
			console.log(data);
		})
}

});

app.controller('LoginCtrl', function($scope, $http, $location, $cookieStore, $window) {
	$http.get('/key').success(function(data, status, headers, config) {
		$cookieStore.put('publicKey', JSON.parse(headers('publickey')));
		});
	$scope.changeSingUpPage = function() {
		$location.path('/sign');
	}
	$scope.login = function(data) {
		var user = "{"
				+"\"user_id\" : \""+$scope.user_id+"\" ,"
				+"\"user_password\" : \""+$scope.user_password+"\""
			+"}";
		var publicKey = $cookieStore.get('publicKey');
		var cryptographer = new Jose.WebCryptographer();
		cryptographer.setContentEncryptionAlgorithm("A128GCM");
		var public_rsa_key = Jose.Utils.importRsaPublicKey(publicKey, "RSA-OAEP");
		var encrypter = new JoseJWE.Encrypter(cryptographer, public_rsa_key);
		encrypter.encrypt(user).then(function(result) {
			$http.post('/login', result).success(function(data) {
				if(data.length>100){ // 추후 인터페이스 변경(?)
					$cookieStore.put('token', data);
					$location.path('/');
				}else{
					$window.confirm('error_code : '+data);
				}
			}).error(function(response) {
			   console.log("error");
			  });
		});	
	}
});


app.controller('sginCtrl', function($scope, $http, $location, $cookieStore, $window) {
		$http.get('/key').success(function(data, status, headers, config) {
			$cookieStore.put('publicKey', JSON.parse(headers('publickey')));
			});
	$scope.createuser = function(data) {
		if($scope.user_password != $scope.user_confirm_password){
			console.log($scope.user_password);
			console.log($scope.user_confirm_password);
		}else{
		var payload = "{"
				+"\"user_id\" : \""+$scope.user_id+"\" ,"
				+"\"user_password\" : \""+$scope.user_password+"\" ,"
				+"\"user_email\" : \""+$scope.user_email+"\""
			+"}";
				var publicKey = $cookieStore.get('publicKey');
				var cryptographer = new Jose.WebCryptographer();
				cryptographer.setContentEncryptionAlgorithm("A128GCM");
				var public_rsa_key = Jose.Utils.importRsaPublicKey(publicKey, "RSA-OAEP");
				var encrypter = new JoseJWE.Encrypter(cryptographer, public_rsa_key);
				encrypter.encrypt(payload).then(function(result) {
			$http.post('/sign', result).success(function(data) {
				$http.post('/login', result).success(function(token) {
					if(token.length>100){ // 추후 인터페이스 변경(?)
						$cookieStore.put('token', token);
						$location.path('/');
					}else{
						$window.confirm('error_code : '+data);
					}
				});
				console.log(data);
			});
		});
		}
		
	}
});