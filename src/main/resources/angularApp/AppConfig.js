/**
 * Created by SoulMonk on 18.12.2014.
 */

var AppConfig = (function () {
    var AppConfig = (function () {
        var loginUrl = '/j_spring_security_check',
            logoutUrl = '/j_spring_security_logout',
            root = '';

        return {
            setLoginUrl: function (url) {
                loginUrl = url;
                return this;
            },
            getLoginUrl: function () {
                return loginUrl;
            },
            setLogoutUrl: function (url) {
                logoutUrl = url;
                return this;
            },
            getLogoutUrl: function () {
                return logoutUrl;
            },
            setRoot: function (url) {
                root = url;
                return this;
            },
            getRoot: function () {
                return root;
            }
        }
    })();
    window.dispatchEvent(new Event('application.init'));
    return AppConfig;
})();