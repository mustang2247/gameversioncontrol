$(function() {
    $(".read-count").each(function() {
        var thisObj = $(this);
        $.get("/public/articleReadCount", {}, function(res) {
            if (res != null)
            {
                $(thisObj).find("b").html(res);
            }
        });
    });
});