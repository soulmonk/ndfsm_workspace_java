/**
 * Created by soulmonk on 16.07.14.
 */

/**
 * BlockController
 * @constructor
 */
var LoginController = function ($scope, $location, $state, flash) {
    // If user is logged in send them to home page
    if ($scope.AuthService.getCurrentUser()) {
        $state.go('layout.dashboard');
    }

    // attempt login to your api
    $scope.attemptLogin = function () {
//        console.log(JSON.stringify($scope.loginForm.$error));
        if (!$scope.loginForm.email.$valid) {
            alert('Yours key is too short. Min length is 10 symbols.');
            return;
        }
        if (!$scope.loginForm.password.$valid) {
            alert('Yours key is too short. Min length is 10 symbols.');
            return;
        }
        if (!$scope.loginForm.$valid) {
            alert('Not valid Form');
            return;
        }
        $scope.AuthService.login({
            email: $scope.credentials.email,
            password: $scope.credentials.password,
            rememberMe: $scope.credentials.rememberMe
        }, function (err) {
            if (err) {
                flash.error = err;
            } else {
                $state.go('layout.dashboard');
            }

        });
    };
};