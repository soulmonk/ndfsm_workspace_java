'use strict';


AppDirectives = angular.module 'NDFSM_App.directives', []

AppDirectives.directive 'appVersion', [ 'version', (version) ->
  (scope, elm, attrs) ->
    elm.text version
]

AppDirectives.directive 'focus', () ->
  (scope, element, attrs) ->
    attrs.$observe 'focus', (newValue) ->
      newValue == 'true' && element[0].focus()
