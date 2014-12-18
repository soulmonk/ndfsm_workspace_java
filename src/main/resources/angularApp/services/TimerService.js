function TimerService($interval) {
    var self = this;
    self.intervals = [];

    this.delay = 10000;

    this.addInterval = function ($scope, fn, delay) {
        if (typeof delay == "undefined") delay = this.delay;
        var intervalPromise = $interval(fn, delay);
        $scope.$on('$destroy', function () {
            $interval.cancel(intervalPromise);
        });
        return intervalPromise;
    };
}
angular.module('NDFSM_App.services.TimerService', [])
    .service('TimerService', ['$interval', TimerService]);