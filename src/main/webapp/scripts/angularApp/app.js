var AngularSpringApp = {};

var App = angular.module('AngularSpringApp', ['AngularSpringApp.filters', 'AngularSpringApp.services', 'AngularSpringApp.directives', 'ngRoute']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {
    'use strict';

    $routeProvider.when('/cars', {
        templateUrl: 'cars/layout',
        controller: CarController,
        controllerAs: 'carCtrl'
    });

    $routeProvider.when('/trains', {
        templateUrl: 'trains/layout',
        controller: TrainController
    });
    
    $routeProvider.when('/products', {
        templateUrl: 'products/layout',
        controller: ProductController
    });

    $routeProvider.otherwise({redirectTo: '/cars'});
}]);
