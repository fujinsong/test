$(document).ready(function () {
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
            name: {
                required: true,
                rangelength:[2,20],
                remote: {
                    type: "post",//数据请求方式
                    url: $(".ctx").val() + "menu/checkMenuName",
                    data: {
                        "id": function () {
                            return $(".id").val();
                        },
                        "name":function () {
                            return $(".name").val();
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
            url: {
                required: true,
                rangelength:[2,30],
                remote: {
                    type: "post",//数据请求方式
                    url: $(".ctx").val() + "menu/checkMenuUrl",
                    data: {
                        "id": function () {
                            return $(".id").val();
                        },
                        "url":function () {
                            return $(".url").val();
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
            name: {
                required: "权限名不能为空",
                rangelength:$.validator.format( "长度必须在{0}到{1}之间" ),
                remote: "权限已经存在"
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
                                window.location.href = $(".ctx").val() + "menu/list";
                            });
                    }
                }
            );
        }
    });
});