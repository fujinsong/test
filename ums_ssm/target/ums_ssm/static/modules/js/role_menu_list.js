$(document).ready(function () {
    $(".save_role").click(function () {
        //先获取被选中的权限
        var menuId = $('input[name="id"]:checked').val();
        var roleId = $(".roleId").val();
        // console.log(roleId)
        $.post(
            $('.ctx').val()+"user/update_role",
            {
                "roleId":roleId,
                "menuId":menuId
            },
            function(data){
                if(data.status!=0){
                    layer.msg(data.msg, {time: 1500});
                }else{
                    //添加数据成功，关闭弹出窗之前，刷新列表页面的数据
                    parent.window.location.href=$('.ctx').val()+"role/list";
                    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                    parent.layer.close(index);
                }
            }
        );
    });
    $(".selectAll").click(function () {
        var isChecked = $(this).prop("checked");
        $("input[name='id']").prop("checked", isChecked);
    })
});