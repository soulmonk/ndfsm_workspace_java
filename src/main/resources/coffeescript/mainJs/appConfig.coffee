'use strict'
@.AppConfig = do () ->
  root: ''
  getRoot: () ->
    console.log 'AppConfig:getRoot'
    @root
  setRoot: (url) ->
    console.log 'AppConfig:setRoot'
    @root = url
    @

@.dispatchEvent new Event('application.init')