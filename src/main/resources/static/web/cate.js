$(function() {
    $(".show-cate").each(function() {
        var thisObj = $(this);
        var html = '<h2 class="cate-title">分 类</h2>';
        $.get("/public/listCates",{}, function(res) {
           // alert(JSON.stringify(res));
            html += '<ul class="cate-ul-list">';
            for(var i=0; i<res.length; i++) {
                var obj = res[i];
                if(obj.cateId != undefined && obj.amount != undefined) {
                    html += '<li><a href="/?cateId='+obj.cateId+'"><i class="fa fa-caret-right"></i>'+obj.cateName+'</a>（'+obj.amount+'）</li>';
                }
            }
            html += '</ul>';
            $(thisObj).html(html);
        });
    });
});