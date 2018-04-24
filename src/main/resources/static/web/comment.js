$(function() {
    $(".add-comment-btn").click(function() {
        var content = $(".comment-content").val();
        if($.trim(content)=='') {
            showDialog("请认真输入点评内容");
        } else {
            $(".comment-content").val("");
            var artId = $(this).attr("artId"); var artTitle = $(this).attr("artTitle");
            $.post("/web/article/addComment", {artId:artId, artTitle:artTitle, content:content}, function(res) {
                if(res=='1') {alert("点评成功，感谢您的参与！"); window.location.reload();}
            }, "json");
        }
    });
});