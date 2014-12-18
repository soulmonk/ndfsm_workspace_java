/**
 * Created by soulmonk on 29.08.14.
 */
var SessionService = function ($resource) {
    return $resource('/account/session');
};
angular.module('NDFSM_App.services.SessionService', [])
    .service('SessionService', ['$resource',SessionService]);