

// ==================================

var sTop = 0;
var h2Top = 0;
var page = 1;	//当前页数
var maxPage = 1000;	//默认最大加载页数
var curTagid = 0; //默认是全部快讯
var loadNewPage = 1;

$(document).ready(function () {
    $(window).scroll(scrollEvent);
});

function reloadPage() {
    $(window).scrollTop(0);
    window.location.reload();
}

function scrollEvent() {
    sTop = $(this).scrollTop();
    showDateTip();
    var h1 = $(document).scrollTop();
    var h2 = $(document).height() - $(window).height();
    if (h1 >= h2 - 350) {
        //$('#debug').html("滚动条已经到达底部 h1=" + h1 + "  h2=" + h2);
        if (loadNewPage == 1) {	//当loadNewPage为1时，才加载，避免一次发送多条请求
            page = page + 1;
            if (page <= maxPage) {
                getNextPage(curTagid, page);
                loadNewPage = 0;
            }
        }
    }
}

//实现刷新功能
// var maxNewsid = 5817;
// // maxNewsid = 6;
// setInterval(getNewNum, 60000);
// function getNewNum() {
//     var url = "/api/app/getNewNum/?last_newsid=" + maxNewsid;
//     var newNum = 0;
//     $.getJSON(url, function (data) {
//         if (data) {
//             data = $.parseJSON(data);
//             newNum = data.newNum;
//             if (newNum > 0) {
//                 if ($('#dateTip').css("display") == "none") {
//                     reloadPage();
//                 } else {
//                     $('#dateTip div').html('您有<font color="red">' + newNum + '</font>条新消息！');
//                 }
//             }
//         }
//     });
// }

function showDateTip() {
    $(".dateitem h2").each(function (i) {
        h2Top = $(this).offset().top;
        if (h2Top - sTop < 0) {
            $('#dateTip').css("display", "block");
            $('#dateTip h2').html($(this).html());
        }
        if (sTop < 40) {
            $('#dateTip').css("display", "none");
        }
    });
}

var textLineNum = 4;

function dealBtnStatus() {
    $("section p").each(function (i) {
        var reg = /px/g;
        var lineHeight = parseInt($(this).css("line-height").replace(reg, ''));
        var pHeight = parseInt($(this).height());
        // alert(pHeight + " lineHeight=" +lineHeight + " " + $(this).html());
        // alert($(this).html());
        if (pHeight <= textLineNum * lineHeight && $(this).css("-webkit-line-clamp") != textLineNum) {
            // alert(pHeight);
            $(this).parent().children('div').css("display", "none");
            $(this).css("height", "auto");
        } else {
            $(this).css("-webkit-line-clamp", textLineNum.toString());
            $(this).css("height", 1.5 * textLineNum + "em");
            $(this).parent().children('div').css("display", "none");
        }
    });
}

$(document).ready(function () {
    dealBtnStatus();
});

var TextBox = '';
var openBtn = '显示全部';
var closeBtn = '收起全部';

<!--控制字数超出隐藏-->
function showMoreText(wt) {
    var reg = /px/g;
    var lineHeight = parseInt($(wt).children("p").css("line-height").replace(reg, ''));
    // alert($(wt).children("p").height());
    if ($(wt).children("p").height() < textLineNum * lineHeight) {	//如果少于3行的高度，不需要展开和收缩
        return false;
    }
    if ($(wt).children("div").children("span").text() == openBtn) {
        $(wt).children("div").html('<span class="more more2"><em></em>' + closeBtn + '</span>');
        TextBox = $(wt).children("p");
        TextBox.css("overflow", "auto");
        TextBox.css("display", "block");
        TextBox.css("height", "auto");
        TextBox.css("-webkit-box-orient", "inherit");
        TextBox.css("-webkit-line-clamp", "inherit");
    } else if ($(wt).children("div").children("span").text() == closeBtn) {
        $(wt).children("div").html('<span class="more more1"><em></em>' + openBtn + '</span>');
        TextBox = $(wt).children("p");
        TextBox.css("overflow", "hidden");
        TextBox.css("display", "-webkit-box");
        TextBox.css("height", 1.5 * textLineNum + "em");
        TextBox.css("-webkit-box-orient", "vertical");
        TextBox.css("-webkit-line-clamp", textLineNum.toString());
    }
}

//===========websocket===========
var Game = {};

Game.socket = null;
Game.updateNews = function (datas, datetime) {
    // Game.entities.innerHTML = "";
    $("#kuaixun_list_items").html("");
    var myDate = new Date();//获取系统当前时间
    $("#kuaixun_list_items").append("<h2>" + datetime + "&middot;今天 </h2>");

    if (datas != null && datas.length > 0) {
        for (var i = 0; i < datas.length; i++) {
            var data = datas[i];
            // Console.log(data.content);
            var cdate = new Date(data.createDate);
            var art = "<article>" +
                "<h3><em></em>" + cdate.getHours() + ":" + cdate.getMinutes() + "</h3>" +
                "<section onclick=\"showMoreText(this);\">" +
                "<p class=\"text_show\">" + data.content + "</p>" +
                "<div class=\"text_show\" style=\"display: none;\" ><span class=\"more more1\"><em></em>显示全部</span></div>" +
                "</section>" +

                "<div class=\"sec_bottom_btn clearfix\">" +
                "<a href=\"#\" class=\"share_btn\" data-news-id=" + data.id + " data-news-time=" + datetime + " title=\"aaaaa\">分享</a>" +
                "</div>" +

                "</article>";
            // Console.log(art);

            $("#kuaixun_list_items").append(art);
            // Game.entities.appendChild(art);
        }
    }
};

Game.initialize = function () {
    this.entities = document.getElementById('kuaixun_list_items');
    if (Game.entities == null) {
        Console.log('没有组件kuaixun_list_items.');
        return;
    }
    Game.connect();
};

Game.connect = (function () {
    Game.socket = new SockJS("/newsws");

    Game.socket.onopen = function () {
        Console.log('Info: WebSocket connection opened.');
        // Console.log('Info: Press an arrow key to begin.');
        setInterval(function () {
            // Prevent server read timeout.
            Game.socket.send('ping');
        }, 5000);
    };

    Game.socket.onclose = function () {
        Console.log('Info: WebSocket closed.');
        // Game.stopGameLoop();
    };

    Game.socket.onmessage = function (message) {
        // console.log('message', message.data);
        // 解析json数据
        var packet = eval('(' + message.data + ')');
        var messageType = packet.type;
        // Console.log(messageType);
        Game.updateNews(packet.data, packet.datetime);
    };

});

//打印log
var Console = {};
Console.log = (function (message) {
    console.log(message);
});

Game.initialize();
