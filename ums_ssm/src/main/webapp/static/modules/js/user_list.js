$(document).ready(function () {
    /*冻结用户*/
    $(".freeze").click(function () {
        // 获取操作类型
        var type = $(this).attr("accesskey");
        if (type == 'yes') {
            if (!window.confirm("确定要冻结该用户吗？")) {
                return;
            }
        }
        // 无论冻结是否成功，刷新列表页面
        var url = $(".ctx").val() + "user/freeze";
        // 获取被冻结用户的id
        var userId = $(this).attr("about");
        $.post(
            url,
            {
                "id": userId,
                "freezeFlag":type
            }, function (data) {
                layer.msg(data.msg, {time: 1500},
                    function () {
                        // 提示框消失后，跳转到列表页
                        window.location.href = $(".ctx").val() + "user/list";
                    });
            }
        );
    });
    //弹出框：修改角色
    $(".update_role").click(function () {
        var userId = $(this).attr("about");
        layer.open({
            type: 2,
            skin: 'layui-layer-rim', //加上边框
            area: ['88%', '88%'], //第一个参数是宽度，第二个参数是高度
            content: $(".ctx").val() + "role/list?layerFlag=yes&status=0&userId="+userId
        });
    });
});