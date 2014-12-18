function AuthService($rootScope, SessionService, $cookieStore) {
    $rootScope.currentUser = $cookieStore.get('user') || null;
    $cookieStore.remove('user');

    return {

        login: function (user, callback) {
            var cb = callback || angular.noop;
            SessionService.save({
                email: user.email,
                password: user.password,
                rememberMe: user.rememberMe
            }, function (user) {
                $rootScope.currentUser = user;
                return cb();
            }, function (err) {
                return cb(err.data);
            });
        },

        logout: function (callback) {
            var cb = callback || angular.noop;
            SessionService.delete(function (res) {
                    $rootScope.currentUser = null;
                    return cb();
                },
                function (err) {
                    return cb(err.data);
                });
        },

        currentUser: function () {
            SessionService.get(function (user) {
                $rootScope.currentUser = user;
            });
        },

        getCurrentUser: function() {
            return $rootScope.currentUser;
        }
    };
}
angular.module('NDFSM_App.services.AuthService', [])
    .service('AuthService', ['$rootScope', 'SessionService', '$cookieStore', AuthService]);