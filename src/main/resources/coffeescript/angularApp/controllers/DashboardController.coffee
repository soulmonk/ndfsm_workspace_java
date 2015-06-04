'use strict'
class DashboardController
  constructor: ($scope, $state) ->
    $scope.Page.setTitle $state.current.title

angular.module('NDFSM_App').controller 'DashboardController', DashboardController