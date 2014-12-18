'use strict';

/* Directives */

var AppFactories = angular.module('NDFSM_App.factories', []);

AppFactories.factory('Page', function() {
    var title = 'Nodemoney';
    return {
        title: function() { return title; },
        setTitle: function(newTitle) { title = newTitle }
    };
});