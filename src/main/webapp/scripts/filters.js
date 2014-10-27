'use strict';

/* Filters */

var AppFilters = angular.module('NDFSM_App.filters', [
    'NDFSM_App.filters.bytes'
]);

AppFilters.filter('interpolate', ['version', function (version) {
    return function (text) {
        return String(text).replace(/\%VERSION\%/mg, version);
    }
}]);
