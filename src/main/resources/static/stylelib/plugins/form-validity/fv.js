/*!
 * $ 自定义表单验证插件
 * pangbin@ucas.ac.cn
 */
;(function($) {

    $.extend($.fn, {
        'vform' : function () {
           
            if (this.length === 0) {
                console.log("vform.js : Nothing selected, can't validate, returning nothing.");
                return;
            }else{
                //console.log($(this));

                console.log('///op');
                // for (var i=0; i<this.length;i++) {
                //     var $this = $(this);
                    
                //     //console.log($(this[i]));
                //     //console.log($(this[i]).find("input[type='text'],input[type='password'],input[type='email'],input[type='url'],select,textarea").length);
                //     //console.log($(this[i]).find("select,textarea").length);
                //     var formbox= $(this[i]);
                //     console.log($this);
                //     //formbox.pass = false;

                //     console.log(this[i].id);
                //$(this).attr('id')

                for (var i=0; i<this.length;i++) {
                    var formname = ['111',222,$(this[i]).attr('id')];
                    $(this[i]).find('button[type=submit]').data('formname', formname);
                }

                $(this).on('click','button[type=submit]',function(){
                    //console.log($.validator.vvv());
                    var n = $(this).data('formname');
                    console.log(n);
                    console.log('click button to submit! form Name='+ n[2]);
                    console.log(this);
                    return false;
                });
            };
        },
    });

    $.extend({
        validator: {
            pass: false,
            vvv: function(){
                return false;
            },
            methods : {
                required: function(valll){
                    return valll;
                },
            },
            messages : {
                required: "This field is required.",
                remote: "Please fix this field.",
                email: "Please enter a valid email address.",
                url: "Please enter a valid URL.",
                date: "Please enter a valid date.",
                dateISO: "Please enter a valid date (ISO).",
                number: "Please enter a valid number.",
                digits: "Please enter only digits.",
                creditcard: "Please enter a valid credit card number.",
                equalTo: "Please enter the same value again.",
                maxlength: "Please enter no more than {0} characters.",
                minlength: "Please enter at least {0} characters.",
                rangelength: "Please enter a value between {0} and {1} characters long.",
                range: "Please enter a value between {0} and {1}.",
                max: "Please enter a value less than or equal to {0}.",
                min: "Please enter a value greater than or equal to {0}."
            }
        }
    });

    $('.js-vform').vform();
}(jQuery));

//$('.js-vform').vform();