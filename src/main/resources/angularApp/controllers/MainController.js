var MainController = function($scope, $http, Page, AuthService, TimerService, $location, flash, $log, $timeout) {
    $scope.Page = Page;
    $scope.AuthService = AuthService;
    $scope.TimerService = TimerService;
    $scope.logger = $log;

    $scope.logoutUser = function() {
        AuthService.logout(function(err) {
            if (err) {
                flash.error = err;
                $timeout(function() { $location.path('/'); } , 1000);
            } else {
                $location.path('/login');
            }
        });
    };

    $scope.isLoggedIn = function() {
        return $scope.AuthService.getCurrentUser() === null;
    };
};