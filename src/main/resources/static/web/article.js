$(function() {
    $(".article-tags").each(function() {
        var vals = $.trim($(this).html());
        var val_arr = vals.split(",");
        var html = ' ';
        for(var i=0; i<val_arr.length;i++) {
            html += '<a href="/?tag='+val_arr[i]+'" class="article-tag-href">'+val_arr[i]+'</a>';
            if(i<val_arr.length-1) {html += ',';}
        }
        $(this).html(html);
    });
});