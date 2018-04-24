$(function() {
    $(".show-notice").each(function() {
        var thisObj = $(this);
//        var html = '<h2>公告</h2>';
        var html = '';
        $.get("/public/listNotice",{}, function(res) {
            for(var i=0; i<res.length; i++) {
                var obj = res[i];
                html += '<div class="container-fluid my-border single-article" style="border-bottom:2px #b36600 solid">';
                html += obj.content;
                html += "</div>";
            }
            $(thisObj).html(html);
        });
    });
});