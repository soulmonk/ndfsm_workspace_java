/**
 * Created by soulmonk on 29.08.14.
 */
var SessionService = function ($resource) {

    return $resource(AppConfig.getLoginUrl());
};
angular.module('NDFSM_App.services.SessionService', [])
    .service('SessionService', ['$resource',SessionService]);