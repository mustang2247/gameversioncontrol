$(function() {
    $(".no-pic").each(function() {
        var height = $(this).parents(".single-article").height();
        $(this).css({"border":"1px #ddd solid", "height":(height-35)+"px", "margin-top":"10px"});
        $(this).css({"border-radius":"5px", "background":"#f9f9f9", "color":"#999", "font-size":"20px"});
        $(this).css({"text-align":"center", "line-height":(height-35)+"px"});

        $(this).html("无图片");
    });

    $(".has-pic").each(function() {
        var height = $(this).parents(".single-article").height();
        var picPath = $(this).find("a").attr("picPath");
        $(this).find("a").html('<img src="'+picPath+'" style="height:'+(height-10)+'px; width:100%;border-radius:5px;"/>');
    });
});