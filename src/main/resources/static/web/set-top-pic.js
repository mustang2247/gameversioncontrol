$(function() {
    $(window).resize(function() {
        setTopPic();
    });
    setTopPic();
});
function setTopPic() {
    setTimeout(function() {
        var imgs = $("#responsiveness").find("img");
        if(imgs.length>0) {
            var width = imgs[0].width;
            var height = imgs[0].height;
            //alert(width+"==="+height);

            $('#responsiveness').swipeslider({
                transitionDuration: 600,
                autoPlayTimeout: 10000,
                sliderHeight: height+'px'
            });
        }
    }, 300);
}