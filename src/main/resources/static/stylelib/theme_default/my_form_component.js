var Script = function () {

    //iCheck
    // $('input[type=checkbox],input[type=radio]').iCheck({
    //     checkboxClass: 'icheckbox_minimal',
    //     radioClass: 'iradio_minimal',
    //     increaseArea: '20%' // optional
    // });

    //chosen select
    $(".chzn-select").chosen(); 
    $(".chzn-select-deselect").chosen({allow_single_deselect:true});


    //tag input

    function onAddTag(tag) {
        alert("Added a tag: " + tag);
    }
    function onRemoveTag(tag) {
        alert("Removed a tag: " + tag);
    }

    function onChangeTag(input,tag) {
        alert("Changed a tag: " + tag);
    }

    $(function() {

        $('#tags_1').tagsInput({width:'auto', defaultText : '添加标签'});
        $('#tags_2').tagsInput({
            width: '250',
            defaultText : '添加标签',
            onChange: function(elem, elem_tags)
            {
                var languages = ['php','ruby','javascript'];
                $('.tag', elem_tags).each(function()
                {
                    if($(this).text().search(new RegExp('\\b(' + languages.join('|') + ')\\b')) >= 0)
                        $(this).css('background-color', '#eee');
                });
            }
        });

        // Uncomment this line to see the callback functions in action
        //			$('input.tags').tagsInput({onAddTag:onAddTag,onRemoveTag:onRemoveTag,onChange: onChangeTag});

        // Uncomment this line to see an input with no interface for adding new tags.
        //			$('input.tags').tagsInput({interactive:false});
    });



    //color picker

    $('.cp1').colorpicker({
        format: 'hex'
    });
    $('.cp2').colorpicker();



    //time picker

    $('#timepicker1, #timepicker3').timepicker();

    $('#timepicker2, #timepicker4').timepicker({
        minuteStep: 1,
        template: 'modal',
        showSeconds: true,
        showMeridian: false
    });


    //clock face time picker

    $('#clockface_1').clockface();

    $('#clockface_2').clockface({
        format: 'HH:mm',
        trigger: 'manual'
    });

    $('#clockface_2_toggle-btn').click(function (e) {
        e.stopPropagation();
        $('#clockface_2').clockface('toggle');
    });




    //date picker
    if (top.location != location) {
        top.location.href = document.location.href ;
    }
    $(function(){
        window.prettyPrint && prettyPrint();
        $('#dp1').datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd'
        });
        $('#dpYears').datepicker({language: 'zh-CN'});
        $('#dpMonths').datepicker({language: 'zh-CN'});
        $('#datetimepicker').datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd'
        }).on('changeDate',function(ev) {
            alert(ev.date);
        });
        
        //$('.date').datepicker({language: 'zh-CN'});

        var startDate = new Date(2012,1,20);
        var endDate = new Date(2012,1,25);
        $('#dp4').datepicker()
            .on('changeDate', function(ev){
                if (ev.date.valueOf() > endDate.valueOf()){
                    $('#alert').show().find('strong').text('The start date can not be greater then the end date');
                } else {
                    $('#alert').hide();
                    startDate = new Date(ev.date);
                    $('#startDate').text($('#dp4').data('date'));
                }
                $('#dp4').datepicker('hide');
            });
        $('#dp5').datepicker()
            .on('changeDate', function(ev){
                if (ev.date.valueOf() < startDate.valueOf()){
                    $('#alert').show().find('strong').text('The end date can not be less then the start date');
                } else {
                    $('#alert').hide();
                    endDate = new Date(ev.date);
                    $('#endDate').text($('#dp5').data('date'));
                }
                $('#dp5').datepicker('hide');
            });

        // disabling dates
        var nowTemp = new Date();
        var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

        var checkin = $('#dpd1').datepicker({
            onRender: function(date) {
                return date.valueOf() < now.valueOf() ? 'disabled' : '';
            }
        }).on('changeDate', function(ev) {
                if (ev.date.valueOf() > checkout.date.valueOf()) {
                    var newDate = new Date(ev.date)
                    newDate.setDate(newDate.getDate() + 1);
                    checkout.setValue(newDate);
                }
                checkin.hide();
                $('#dpd2')[0].focus();
            }).data('datepicker');
        var checkout = $('#dpd2').datepicker({
            onRender: function(date) {
                return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
            }
        }).on('changeDate', function(ev) {
                checkout.hide();
            }).data('datepicker');
    });



    //daterange picker

    //设定语言
    Date.CultureInfo = {
        name: "zh-CN",
        englishName: "China (Simplified Chinese)",
        nativeName: "China (Simplified Chinese)",
        dayNames: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
        abbreviatedDayNames: ["日", "一", "二", "三", "四", "五", "六"],
        shortestDayNames: ["日", "一", "二", "三", "四", "五", "六"],
        firstLetterDayNames: ["S", "M", "T", "W", "T", "F", "S"],
        monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        abbreviatedMonthNames: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
        amDesignator: "上午",
        pmDesignator: "下午"
    };

    $('#reservation').daterangepicker();
    $('.m-date-reportrange').daterangepicker(
        {
            ranges: {
                '今天': ['today', 'today'],
                '昨天': ['yesterday', 'yesterday'],
                '最近七天': [Date.today().add({ days: -6 }), 'today'],
                '最近30天': [Date.today().add({ days: -29 }), 'today'],
                '本月': [Date.today().moveToFirstDayOfMonth(), Date.today().moveToLastDayOfMonth()],
                '上个月': [Date.today().moveToFirstDayOfMonth().add({ months: -1 }), Date.today().moveToFirstDayOfMonth().add({ days: -1 })]
            },
            opens: 'right',
            format: 'yyyy/MM/dd',
            startDate: Date.today().add({ days: -29 }),
            endDate: Date.today(),
            minDate: '2012/01/01',
            maxDate: '2013/12/31',
            locale: {
                applyLabel: '确认',
                fromLabel: '从',
                toLabel: '到',
                customRangeLabel: '自定义范围',
                daysOfWeek: ['日', '一', '二', '三', '四', '五','六'],
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                firstDay: 1
            },
            showWeekNumbers: true,
            buttonClasses: ['btn-danger']
        },
        function(start, end) {
            $('.m-date-reportrange span').html(start.toString('yyyy年MM月d日') + ' - ' + end.toString('yyyy年MM月d日'));
        }
    );

    //Set the initial state of the picker label
    $('.m-date-reportrange span').html(Date.today().add({ days: -29 }).toString('yyyy年MM月d日') + ' - ' + Date.today().toString('yyyy年MM月d日'));




    //toggle button
    // window.prettyPrint && prettyPrint();
    $('#mySwitch').on('switch-change', function (e, data) {
        var $el = $(data.el)
            , value = data.value;
        console.log(e, $el, value);
        alert(value+ '\n看看调试控制台!');
    });
    $('#label-toggle-switch').on('click', function(e, data) {
        $('.label-toggle-switch').bootstrapSwitch('toggleState');
    });
    $('.label-toggle-switch').on('switch-change', function (e, data) {
        alert(data.value);
    });
    $('#label2-toggle-switch').on('switch-change', function(e, data) {
        alert(data.value);
    });

    // STATE
    $('#toggle-state-switch-button').on('click', function () {
        $('#toggle-state-switch').bootstrapSwitch('toggleState');
    });
    $('#toggle-state-switch-button-on').on('click', function () {
        $('#toggle-state-switch').bootstrapSwitch('setState', true);
    });
    $('#toggle-state-switch-button-off').on('click', function () {
        $('#toggle-state-switch').bootstrapSwitch('setState', false);
    });
    $('#toggle-state-switch-button-status').on('click', function () {
        alert($('#toggle-state-switch').bootstrapSwitch('status'));
    });

    //WYSIWYG Editor

    $('.wysihtmleditor5').wysihtml5();

}();