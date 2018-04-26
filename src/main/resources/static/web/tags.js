$(function() {
    $(".show-tags").each(function() {
        var thisObj = $(this);
        var html = '<h2 class="tag-title">站内标签</h2>';
        $.get("/public/listTags",{}, function(res) {
            for(var i=0; i<res.length; i++) {
                var obj = res[i];
                html += '<a href="/?tag='+obj.name+'" title="'+obj.name+'" class="btn ">'+obj.name+'</a>&nbsp;';
            }
            $(thisObj).html(html);
        });
    });
});