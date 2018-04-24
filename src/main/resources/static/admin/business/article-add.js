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
        //var cateId = $("a[name='cateid']").val();
        //console.log();

        if(title==null || $.trim(title)=='') {showDialog("请输入文章标题！"); return false;}
        //else if(cateId==null || $.trim(cateId)=='') {showDialog("请选择文章所属分类！"); return false;}
        var cateId = $(".btn-info").attr('catid');
        $("input[name='cateId']").val(cateId);

        if(cateId==null || cateId == ""){
            showDialog("请选择文章所属分类！"); return false;
        }

        var guide = $("textarea[name='guide']").val();
        if(guide == null || guide == ""){
            showDialog("请输入导读内容！"); return false;
        }
        var file = $("input[name='file']").val();
        if(file==null || file==""){
            showDialog("请选择文章图片！"); return false;
        }

        var content= CKEDITOR.instances.editor1.getData();
        var mdCon=CKEDITOR.instances.editor1.document.getBody().getText();


        if(content==null || $.trim(content)=='') {
            showDialog("请认真输入文章内容！"); return false;
        }
//        alert(myEditormd.getValue());
        $("textarea[name='content']").text(content);
        $("textarea[name='mdContent']").text(mdCon);

        //校验文章标题是否重复
        var boolean;
        $.ajax({
            url:"checkTitle",    //请求的url地址
            //dataType:"json",   //返回格式为json
            async:false,//请求是否异步，默认为异步，这也是ajax重要特性
            data:{"title":title},    //参数值,键值对
            type:"GET",   //请求方式
            success:function(data){
                //请求成功时处理
                if(""!= data){
                    boolean = 1;
                }
            },
            error:function(){
                //请求出错处理
            }
        });
        if(boolean==1){
            showDialog("该文章已存在！");
            return false;
        }else{
            return true
        }
    });


});