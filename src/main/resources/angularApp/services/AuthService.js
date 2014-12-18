function AuthService($rootScope, $http, $cookieStore) {
    $rootScope.currentUser = $cookieStore.get('user') || null;
    $cookieStore.remove('user');

    return {

        login: function (user, callback) {
            var cb = callback || angular.noop;
            $http.post(AppConfig.getLoginUrl(),{
                j_username: user.username,
                j_password: user.password,
                remember_me_parameter: user.rememberMe
            }, function (user) {
                $rootScope.currentUser = user;
                return cb();
            }, function (err) {
                return cb(err.data);
            });
        },

        logout: function (callback) {
            var cb = callback || angular.noop;
            $http.get(AppConfig.getLogoutUrl(), function (res) {
                    $rootScope.currentUser = null;
                    return cb();
                },
                function (err) {
                    return cb(err.data);
                });
        },

        currentUser: function () {
            /*SessionService.get(function (user) {
                $rootScope.currentUser = user;
            });*/
        },

        getCurrentUser: function() {
            return $rootScope.currentUser;
        }
    };
}
angular.module('NDFSM_App.services.AuthService', [])
    .service('AuthService', ['$rootScope', '$http', '$cookieStore', AuthService]);