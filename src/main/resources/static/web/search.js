$(function() {
    $(".model-search-div").each(function() {
        var thisObj = $(this);
        $.get("/public/articleCount", {}, function(res) {
            $(thisObj).find("input").attr("placeholder", "在 "+ res + " 篇文章中搜索");
        });
    });

    $(".model-search-div .search-btn").click(function() {
        var condition = $(this).parents(".input-group").find("input").val();
        search(condition);
    });

    $(".model-search-div .input-group input").keyup(function(event) {
        var keyCode = event.keyCode;
        if(keyCode==13) {
            var condition = $(this).val();
            search(condition);
        }
    });
});

function search(condition) {
    if($.trim(condition)=='') {
        showDialog("可输入标题或内容搜索文章，即将跳转至首页");
        setTimeout(function() {
            window.location.href = "/";
        }, 2500);
    }
    else {
        window.location.href = "/?condition="+condition;
    }
}