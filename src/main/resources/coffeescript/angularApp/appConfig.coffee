'use strict'
@ConfigService = do () ->
  root: ''
  getRoot: () ->
    console.log 'ConfigService:getRoot'
    @root
  setRoot: (url) ->
    console.log 'ConfigService:setRoot'
    @root = url
    @

window.dispatchEvent new Event('application.init')