
// ==================================分享
jQuery(function ($) {
    $("#kuaixun_list").on("taphold", 'p', function (event) {
        event.preventDefault();
        var obj = $(this).parent().next().children('.share_btn');
        shareKuaixun(obj.data('news-id'), obj.data('news-time'));
    });
    $('#kuaixun_list').on('click', '.share_btn', function (event) {
        shareKuaixun($(this).data('news-id'), $(this).data('news-time'));
    });

    $('p').bind('contextmenu', function (e) {
        e.preventDefault();
    })
});

function shareKuaixun(newsid, issueMonth) {
    if (!$.isNumeric(newsid) || !$.isNumeric(issueMonth)) {
        return false;
    }
    var imgURL = "/ws/image/newsflash/" + issueMonth + "/share" + newsid + ".png?v=4";
    $('#qcode_tip img').attr("src", imgURL);
    $('#overlay').click(hideTip);
    showTip();
}

function showTip() {
    $('#qcode_tip').css("display", "block");
    $('#overlay').css("display", "block");
    $('.top_tips').css('display', 'block');
}

function hideTip() {
    $('#overlay').css("display", "none");
    $('#qcode_tip').css("display", "none");
    $('.top_tips').css('display', 'none');
}

//======================

/**
 * 微信分享接口调用
 */
jQuery(function ($) {
    if (bsj_ready) return;
    bsj_ready = true;

    var data = wx_share;
    var url = '/home/wechatjssdk/share';
    $.ajax({
        url: url,
        type: 'post',
        timeout: 5000,
        data: data
    }).done(function (data, status, response) {
        share_obj = data;
        var join_symbol = '&';
        if (share_obj.url.indexOf('?') < 0) {
            join_symbol = '?';
        }
        share_obj.url += join_symbol + 'r=a' + new Date().getTime();

        wxJssdkStart();
        // var hm = document.createElement("script");
        // hm.src = "/static/home/js/wx-share.js";
        // var s = document.getElementsByTagName("script")[0];
        // s.parentNode.insertBefore(hm, s);
    }).fail(function (response, status, messsage) {
    });

    function wxJssdkStart() {
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: share_obj.appid,
            timestamp: share_obj.timestamp,
            nonceStr: share_obj.noncestr,
            signature: share_obj.sha1,
            jsApiList: ["onMenuShareTimeline", "onMenuShareAppMessage", "onMenuShareQQ", "onMenuShareWeibo", "onMenuShareQZone"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
        wx.error(function (res) {
        });
        wx.ready(function () {
            wx.onMenuShareTimeline({
                title: share_obj.title, // 分享标题
                link: share_obj.url, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                imgUrl: share_obj.imgUrl, // 分享图标
                success: function () {
                    // alert('成功');
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                    // lert("取消了")
                }
            });
            wx.onMenuShareAppMessage({
                title: share_obj.title, // 分享标题
                desc: share_obj.desc, // 分享描述
                link: share_obj.url, // 分享链接
                imgUrl: share_obj.imgUrl, // 分享图标
                success: function () {
                    // alert('成功');
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                    // lert("取消了")
                }
            });
        });
    }

// jquery ready.End
});

