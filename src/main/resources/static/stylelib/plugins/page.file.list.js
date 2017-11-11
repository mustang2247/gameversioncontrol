$(document).ready(function() {

    $('.b-files-list dd').mouseover(function() {
        $(this).addClass('hover');
    }).mouseout(function(){
        $(this).removeClass('hover');
    }).click(function(){
        var ddthis = $(this);
        var ddck = $(this).find('.m-file-name input[type="checkbox"]');
        if (ddck.prop("checked")) {
            $('.b-files-headers :checkbox').prop('checked',false);
            ddthis.removeClass('rele');
            ddck.prop("checked",false);
            if ($('.b-files-list .rele').length <= 0){
                $('.bpab-morebar').removeClass('show');
            }
        }else{
            ddthis.addClass('rele');
            ddck.prop("checked",true);
            $('.bpab-morebar').addClass('show');
        }
    }).dblclick(function(){
        var ddfilename = $(this).find('.mfn-name');
        var ddtype = ddfilename.attr('data-type');
        var ddurl = ddfilename.attr('href');
        if (ddtype === "folder"){
            location.href = ddurl;
        }else{
            //弹出框显示文件内容
            var modal = $.scojs_modal({
                title: '文件名',
                content: "<div class=\"m-iframe-content\">这里是内容！！！</div>"
            });
            modal.show();
        }
    });

    //checkbox事件
    $('.b-files-list input[type="checkbox"]').click(function(event) {
        event.stopPropagation();
    }).change(function(event) {
        if ($(this).prop("checked")) {
            $(this).prop("checked",true);
            $(this).closest('dd').addClass('rele');
            $('.bpab-morebar').addClass('show');
        }else{
            $('.b-files-headers :checkbox').prop('checked',false);
            $(this).prop("checked",false);
            $(this).closest('dd').removeClass('rele');
            if ($('.b-files-list .rele').length <= 0){
                $('.bpab-morebar').removeClass('show');
            }
        }
    });


    //checkbox all select
    $('.js-fack').on("click", function() {
        var alldd = $('.b-files-list dd');
        if (this.checked) {
            alldd.addClass('rele').find(':checkbox').prop("checked",true);
            $('.bpab-morebar').addClass('show');
        }else{
            alldd.removeClass('rele').find(':checkbox').prop("checked",false);
            $('.bpab-morebar').removeClass('show');
        }
    });


    $('.mdb-share-btn').click(function(){
        $(this).scojs_modal({title: '分享到'});
        //event.stopPropagation();
        return false;
    });


});