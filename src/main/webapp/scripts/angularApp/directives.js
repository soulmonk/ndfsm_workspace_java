/* Directives */

var AppDirectives = angular.module('AngularSpringApp.directives', []);

AppDirectives.directive('appVersion', ['version', function (version) {
    return function (scope, elm, attrs) {
        elm.text(version);
    };
}]);

AppDirectives.directive('focus', function () {
	  return function (scope, element, attrs) {
		attrs.$observe('focus', function (newValue) {
		  newValue === 'true' && element[0].focus();
		});
	  };
});