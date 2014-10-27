'use strict';

/* Services */

var AppServices = angular.module('NDFSM_App.services', [
    'NDFSM_App.services.AuthService',
    'NDFSM_App.services.TimerService',
    'NDFSM_App.services.SessionService'
]);

AppServices.value('version', '0.1');
