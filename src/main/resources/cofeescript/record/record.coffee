$j = jQuery

class Exception
  constructor: (@message) ->

class Logger
  _enable = true

  @log: (args...) ->
    console.log(args) if _enable

  @error: (message) ->
    throw new Exception(message)