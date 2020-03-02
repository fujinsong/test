$(document).ready(function () {
    if(self.frameElement!=null && self.frameElement.tagName=="FRAME"){
       window.top.location.href=$(".ctx").val() + "login_page";
       return;
    }
    var islogin = $(".loginStatus").val();
    if(islogin!=null && islogin!='' && islogin=='yes'){
       //已经登录，直接跳转到主页面
       window.location.href=$(".ctx").val() + "admin";
       return;
    }

    var time = 10;
    $(".sendSms").click(function () {
        // 异步请求服务端发送验证码
        var url = $(".ctx").val() + "send/msg";
        var mobile = $(".mobile").val();
        var pattern = /^1[3|4|5|7|8]\d{9}$/;
        if (mobile == null || !pattern.test(mobile)) {
            alert("请输入正确格式的手机号");
            return;
        }
        $.post(
            url,
            {
                "mobile": mobile
            },
            function (data) {
                if (data.status == 1) {
                    //失败
                    alert("发送失败");
                }
            }
        );
        //如果不加入该判断，则会出现在倒计时期间不断的点击发生不断的加快（其实就是你点了多少次就重复多少次该函数）的问题，
        // 用setTimeout（）方法不加此判断也是一样的
        if (time == 10) {
            var time1 = setInterval(function () {
                if (time > 0) {
                    time--;
                    $(".sendSms").attr("disabled", "true");
                    $(".sendSms").html("重新发送(" + time + "s)");
                } else {
                    $(".sendSms").html("发送短信验证码");
                    $(".sendSms").removeAttr("disabled");
                    time = 10;
                    clearInterval(time1);
                }
            }, 6000);
        }

    });


    //表单验证
    $(".loginForm").validate({
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
            mobile: {
                required: true
            },
            verification: {
                required: true
            }
        },
        messages: {
            mobile: {
                required: "手机号不能为空"
            },
            verification: {
                required: "验证码不能为空"
            }
        },
        errorPlacement: function (error, element) {//验证消息放置的地方
            error.appendTo(element.siblings("span[class='err-class']"));//错误提示文本显示在当前文本框的兄弟span中
        },
        submitHandler: function (form) {
            var url = $(".loginForm").attr("action");
            var params = $(".loginForm").serialize();
            $.post(
                url,
                params,
                function (data) {
                    if (data.status!=0) {
                        alert(data.msg);
                    }
                    if (data.status==0) {
                        //登录成功，跳转到主页面
                        window.location.href = $(".ctx").val() + "admin";
                    }
                }
            );
        }
    });
});
