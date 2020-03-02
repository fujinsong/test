$(document).ready(function () {
    //弹出框：给权限
    $(".update_menu").click(function () {
        var roleId = $(this).attr("about");
        layer.open({
            type: 2,
            skin: 'layui-layer-rim', //加上边框
            area: ['88%', '88%'], //第一个参数是宽度，第二个参数是高度
            content: $(".ctx").val() + "menu/list?layerFlag=yes&status=0&roleId="+roleId
        });
    });
});