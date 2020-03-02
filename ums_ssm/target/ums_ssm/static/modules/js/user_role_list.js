$(document).ready(function () {
    $(".save_role").click(function () {
        //先获取被选中的角色
        var roleId = $('input[name="id"]:checked').val();
        var userId = $(".userId").val();
        // console.log(roleId)
        $.post(
            $('.ctx').val()+"user/update_role",
            {
                "roleId":roleId,
                "id":userId
            },
            function(data){
                // layer.msg(data.msg, {time: 1500});
                if(data.status!=0){
                    // alert(result.message);
                    layer.msg(data.msg, {time: 1500});
                }else{
                    //添加数据成功，关闭弹出窗之前，刷新列表页面的数据
                    parent.window.location.href=$('.ctx').val()+"user/list";
                    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                    parent.layer.close(index);
                }
            }
        );
    });
});