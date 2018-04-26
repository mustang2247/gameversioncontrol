$(function() {

    $("#my-editormd").find("textarea").text($("textarea[name='mdContent']").text());

    var myEditormd = editormd("my-editormd", {
        width           : "100%",
        dialogMaskBgColor : "#000",
        height          : "600px",
        //autoHeight      : true,
        path            : "/editor.md-master/lib/",
        htmlDecode      : "style,script,iframe",
        imageUpload : true,
        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        saveHTMLToTextarea : true,    // 保存 HTML 到 Textarea
        tocm            : true,         // Using [TOCM]
        tex             : true,
        emoji           : true,
        taskList        : true,
        flowChart       : true,
        sequenceDiagram : true,
        toolbar         : false
    });

    $("#dataForm").submit(function() {
        var content = myEditormd.getHTML();
        var mdCon = myEditormd.getMarkdown();
        if(content==null || $.trim(content)=='') {
            showDialog("请认真输入内容！"); return false;
        }

        $("textarea[name='content']").text(content);
        $("textarea[name='mdContent']").text(mdCon);

        return true;
    });
});