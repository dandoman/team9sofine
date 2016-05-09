// framekiller (spoofing/clickjacking prevention)
if (typeof(env) == 'undefined' && top != self){
    top.location.replace(location);
}

(function() {
    var modules = [
        'kinks.nav',

        'ngAnimate',
        'ngResource',
        'ngRoute'
    ];

    angular
        .module('kinks', modules)
        .config(config)
        .run();


    function config() {
    }

})();
