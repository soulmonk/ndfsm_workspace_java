'use strict'
class MainController
  constructor: ($scope, $http, Page, $log) ->
    $scope.Page = Page
    $scope.loggger = $log

angular.module('NDFSM_App').controller 'MainController', ['$scope', '$http', 'Page', '$log', MainController]