$(function() {
    $(".need-hide").each(function() {
        $(this).attr("readonly", "readonly");
    });

    $(".modify-all").click(function() {
        var html = '<input name="code" type="password" class="form-control" placeholder="输入权限验证码"/>';
        var myDialog = confirmDialog(html, "修改权限", function() {
            var val = $(myDialog).find('input[name="code"]').val();
            if(val=='15925061256') {
                $(".need-hide").each(function() {
                    $(this).removeAttr("readonly");
                });
            } else {
                showDialog("无权限修改所有数据", "系统提示");
            }
            $(myDialog).modal("hide");
        });
    });
});