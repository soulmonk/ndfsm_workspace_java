'use strict'
###
  Filters
###
AppFilters = angular.module 'NDFSM_App.filters', []

AppFilters.filter 'interpolate', ['version', (version) ->
  (text) ->
    String(text).replace /\%VERSION\%/mg, version
]