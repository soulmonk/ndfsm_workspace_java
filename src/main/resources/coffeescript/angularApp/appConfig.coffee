'use strict'
window.AppConfig = do () ->
  root: ''
  getRoot: () ->
    console.log 'AppConfig:getRoot'
    @root
  setRoot: (url) ->
    console.log 'AppConfig:setRoot'
    @root = url
    @

window.dispatchEvent new Event('application.init')