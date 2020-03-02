$(document).ready(function () {
    // 手机号码验证
    jQuery.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        return this.optional(element) || (length == 11 && /^(1[3|4|5|7|8]\d{9})$/.test(value));
    }, "请正确填写您的手机号码。");
    //表单验证
    $(".inputForm").validate({
        onfocusin: function (element) {
            $(element).valid();
        },
        onfocusout: function (element) {
            $(element).valid();
        },
        onclick: function (element) {
            $(element).valid();
        },
        onkeyup: function (element) {
            $(element).valid();
        },
        rules: {
            userName: {
                required: true,
                rangelength:[2,20],
                remote: {
                    type: "post",//数据请求方式
                    url: $(".ctx").val() + "user/checkUserName",//异步验证路径
                    data: {
                        "id": function () {
                            return $(".id").val();
                        },
                        "userName":function () {
                            return $(".userName").val();
                        }
                    },
                    dataType: "html",
                    dataFilter: function (data) {//AJAX异步返回数据
                        data = eval("("+data+")");
                        //账号已经存在，需要提示，当前返回false才能触发下面的提示
                        if(data.status == 0){
                            return false;
                        }
                        return true;//返回false的时候，需要提示信息
                    }
                }
            },
            realName: {
                required: true,
                rangelength: [2,20]
            },
            mobile:{
                required: true,
                isMobile: true,
                remote: {
                    type: "post",//数据请求方式
                    url: $(".ctx").val() + "user/checkMobile",//异步验证路径
                    data: {
                        "id": function () {
                            return $(".id").val();
                        },
                        "mobile":function () {
                            return $(".mobile").val();
                        }
                    },
                    dataType: "html",
                    dataFilter: function (data) {//AJAX异步返回数据
                        data = eval("("+data+")");
                        //账号已经存在，需要提示，当前返回false才能触发下面的提示
                        if(data.status == 0){
                            return false;
                        }
                        return true;//返回false的时候，需要提示信息
                    }
                }
            }
        },
        messages: {
            userName: {
                required: "账号不能为空",
                rangelength:$.validator.format( "长度必须在{0}到{1}之间" ),
                remote: "账号已经存在"
            },
            realName: {
                required: "真实姓名不能为空",
                rangelength: $.validator.format( "长度必须在{0}到{1}之间" )
            },
            mobile:{
                required: "手机号不能为空",
                isMobile: "手机号格式不正确",
                remote: "手机号已经被使用过了"
            }
        },
        errorPlacement: function (error, element) {//验证消息放置的地方
            error.appendTo(element.siblings("span[class='err-class']"));//错误提示文本显示在当前文本框的兄弟span中
        },
        submitHandler: function (form) {
            var url = $(".inputForm").attr("action");
            var params = $(form).serialize();
            $.post(
                url,
                params,
                function (data) {
                    if (data.status == 1) {
                        layer.msg(data.msg, {icon: 5});//默认3秒后消失
                    }
                    if (data.status == 0) {//操作成功，跳转到列表面
                        // 1.5 秒后消失
                        layer.msg(data.msg, {icon: 1},
                            function () {
                                //提示框消失后，跳转到列表页
                                window.location.href = $(".ctx").val() + "user";
                            });
                    }
                }
            );
        }
    });
});