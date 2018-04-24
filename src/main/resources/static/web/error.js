$(function() {
    var time = 3;
    setInterval(function() {
        if(time-- <=0 ) {
            window.location.href = "/";
        }
    }, 1000);
});