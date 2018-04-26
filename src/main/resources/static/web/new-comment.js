$(function() {
    $(".new-comment").each(function() {
        var length = $(this).attr("showLength");
        var thisObj = $(this);
        var html = '<h2>最新点评</h2>';
        $.get("/public/newComment",{length:length}, function(res) {
        //alert(JSON.stringify(res));
            var resCon = res.content;
            html += '<ul class="comment-ul-list">';
            for(var i=0; i<resCon.length; i++) {
                var obj = resCon[i];
                html += '<li>在 <a href="/web/article/detail/'+obj.artId+'" title="'+obj.artTitle+'">《<span>'+obj.artTitle+'</span>》</a> 中点评：'+obj.content+'</li>';
            }
            html += '</ul>';
            $(thisObj).html(html);
        });
    });
});