$(function() {
    $('input[name="tags"]').tagator();

    $(".cate-href").click(function() {
        var cateId = $(this).attr("cateId");
        var cateName = $(this).html();
        $('input[name="cateId"]').val(cateId);
        $('input[name="cateName"]').val(cateName);

        $(".cate-href").each(function() {
            if($(this).attr("class").indexOf("btn-info")>=0) {$(this).removeClass("btn-info");}
        });
        $(this).addClass("btn-info");
    });

    $("#dataForm").submit(function() {
        var title = $("input[name='title']").val();
        var cateId = $(".btn-info").attr('cateid');
        $("input[name='cateId']").val(cateId);

        if(title==null || $.trim(title)=='') {showDialog("请输入文章标题！"); return false;}
        //else if(cateId==null || $.trim(cateId)=='') {showDialog("请选择文章所属分类！"); return false;}
        if(cateId==null || cateId == ""){
            showDialog("请选择文章所属分类！"); return false;
        }

        var guide = $("textarea[name='guide']").val();
        if(guide == null || guide == ""){
            showDialog("请输入导读内容！"); return false;
        }
        var content= CKEDITOR.instances.editor1.getData();
        var mdCon=CKEDITOR.instances.editor1.document.getBody().getText();

        if(content==null || $.trim(content)=='') {
            showDialog("请认真输入文章内容！"); return false;
        }

//        alert(myEditormd.getValue());

        $("textarea[name='content']").text(content);
        $("textarea[name='mdContent']").text(mdCon);

        return true;
    });
});