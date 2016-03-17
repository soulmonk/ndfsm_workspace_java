'use strict'
###
  Factories
###
AppFactories = angular.module 'NDFSM_App.factories', []

AppFactories.factory 'Page', () ->
  title = 'NDFSM App'
  title: () ->
    title
  setTitle: (newTitle) ->
    title = newTitle
    @