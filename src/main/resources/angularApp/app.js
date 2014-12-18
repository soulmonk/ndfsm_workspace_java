/**
 * Created by soulmonk on 16.07.14.
 */

var NDFSM_App = {};

var App = angular.module('NDFSM_App', [
    'NDFSM_App.filters',
    'NDFSM_App.services',
    'NDFSM_App.directives',
    'NDFSM_App.factories',
    'ngRoute',
    'ngCookies',
    'ui.router',
    'ngTable',
    'ngResource',
    'angular-flash.service',
    'angular-flash.flash-alert-directive'
]);

// Declare app level module which depends on filters, and services
App.config(['$stateProvider', '$urlRouterProvider', '$httpProvider', 'flashProvider', function ($stateProvider, $urlRouterProvider, $httpProvider, flashProvider) {
    $httpProvider.interceptors.push(function($q) {
        return {
            responseError: function(rejection) {
                if(rejection.status == 0) {
                    alert('Connection lost!!!');
                    window.location = "/";
                    return;
                }
                return $q.reject(rejection);
            }
        };
    });

    flashProvider.errorClassnames.push('alert-danger');
    $stateProvider.state('login', {
        url: '/login',
        templateUrl: AppConfig.PREFIX + '/resources/_views/additional/login.html',
        controller: LoginController,

        // You do not need to be logged in to go to this route.
        requireLogin: false
    });
    $stateProvider.state('layout', {
        url: '/',
        templateUrl: AppConfig.PREFIX + '/resources/_views/base.html',
        controller: function() {
            jQuery('#side-menu').metisMenu();
        },
        title: 'Base layout',
        // You must be logged into go to this route.
        requireLogin: true
    });
    $stateProvider.state('layout.dashboard', {
        url: 'dashboard',
        templateUrl: AppConfig.PREFIX + '/resources/_views/dashboard.html',
        controller: DashboardController,
        title: 'Dashboard',
        requireLogin: true
    });
    $urlRouterProvider.otherwise('/');

}]);

App.run(['$rootScope', 'AuthService', '$location', '$state', function($rootScope, AuthService, $location, $state){
    //watching the value of the currentUser variable.
    $rootScope.$watch('currentUser', function(currentUser) {
        // if no currentUser and on a page that requires authorization then try to update it
        // will trigger 401s if user does not have a valid session
        if (!currentUser && (['/', '/login', '/logout'].indexOf($location.path()) == -1 )) {
            AuthService.currentUser();
        }
    });

    $rootScope.$on('$stateChangeStart',
        function(event, toState/*, toParams, fromState, fromParams*/){
            if (toState.requireLogin && !AuthService.getCurrentUser()) {
                $state.go('login');
                event.preventDefault();
            }
        });
    $rootScope.$on("$locationChangeSuccess", function(event, next, current) {

    });
}]);
