'use strict'

App = angular.module 'NDFSM_App', [
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
  ]

App.config ['$stateProvider', '$urlRouterProvider', '$httpProvider', 'flashProvider', ($stateProvider, $urlRouterProvider, $httpProvider, flashProvider) ->
  $httpProvider.interceptors.push ($q) ->
    responseError: (rejection) ->
      if rejection.status == 0
        alert 'Connection lost!!!'
        window.location = "/"
        return
      $q.reject(rejection)

  flashProvider.errorClassnames.push 'alert-danger'

  $stateProvider.state 'layout',
    url: '/'
    templateUrl: window.AppConfig.getRoot() + '/resources/_views/base.html'
    controller: () ->
      jQuery('#side-menu').metisMenu()
    title: 'Base layout'

  $stateProvider.state 'layout.dashboard',
    url: 'dashboard'
    templateUrl: window.AppConfig.getRoot() + '/resources/_views/dashboard.html'
    controller: 'DashboardController'
    title: 'Dashboard'

  $urlRouterProvider.otherwise '/'
]