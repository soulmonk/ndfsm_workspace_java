###
 About modularisation and namespaces: http://spin.atomicobject.com/2011/04/01/namespace-a-coffeescript-nugget/
###

namespace = (target, name, block) ->
  [target, name, block] = [(if typeof exports isnt 'undefined' then exports else window), arguments...] if arguments.length < 3
  top = target
  target = target[item] or= {} for item in name.split '.'
  block target, top

namespace "APP", (exports) ->

  exports.execLog = ->
    console.log(new Date)