$(function() {
    $(".hot-article").each(function() {
        var length = $(this).attr("showLength");
        var thisObj = $(this);
        var html = '<h2>高访文章</h2>';
        $.get("/public/hotArt",{length:length}, function(res) {
        //alert(JSON.stringify(res));
            var resCon = res.content;
            html += '<ul class="cate-ul-list">';
            for(var i=0; i<resCon.length; i++) {
                var obj = resCon[i];
                html += '<li><a href="/web/article/detail/'+obj.id+'" title="'+obj.title+'"><i class="fa fa-chevron-right"></i> <span>'+obj.title+'</span></a>（'+obj.readCount+'）</li>';
            }
            html += '</ul>';
            $(thisObj).html(html);
        });
    });
});