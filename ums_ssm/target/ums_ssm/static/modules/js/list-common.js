$(document).ready(function () {
    /**
     * 删除数据
     */
    $(".delete_data").click(function () {
        if (window.confirm("确认要删除该条数据吗")) {
            var baseUrl = $(".ctx").val() + $(this).attr("name");
            $.post(
                baseUrl + "/delete",
                {
                    id: $(this).attr("title")
                },
                function (data) {
                    if (!data.status==0) {
                        layer.msg(data.msg, {icon: 1},
                            function () {
                                //提示框消失后，跳转到列表页
                                window.location.href = baseUrl;
                            });
                    }else if (data.status==1) {//操作成功，跳转到列表面
                        layer.msg(data.msg, {icon: 5},
                            function () {
                                //提示框消失后，跳转到列表页
                                window.location.href = baseUrl;
                            });
                    }
                }
            );
        }
    });
    //分页
    /**
     * 首页
     */
    $(".goHome,.search-btn").click(function () {
        $(".pageNo").val(1);
        $(".form-search").submit();//提交查询表单
    });
    /**
     * 上一页
     */
    $(".goBack").click(function () {
        var pageNo = $(".pageNo").val();
        pageNo = parseInt(pageNo);
        pageNo = pageNo - 1;
        if (pageNo < 1) {
            pageNo = 1;
        }
        $(".pageNo").val(pageNo);
        $(".form-search").submit();//提交查询表单
    });
    /**
     * 下一页
     */
    $(".goNext").click(function () {
        var pageNo = $(".pageNo").val();
        var total = $(".totalPage").val();
        pageNo = parseInt(pageNo);
        total = parseInt(total);
        pageNo = pageNo + 1;
        if (pageNo > total) {
            pageNo = total;
        }
        $(".pageNo").val(pageNo);
        $(".form-search").submit();//提交查询表单
    });
    /**
     * 末页
     */
    $(".goEnd").click(function () {
        $(".pageNo").val($(".totalPage").val());
        $(".form-search").submit();//提交查询表单
    });
    /**
     * 去第几页
     */
    $(".goPageBtn").click(function () {
        var goNo = $(".goNo").val();
        var total = $(".totalPage").val();
        if (isNaN(goNo)) {
            alert("请输入阿拉伯数字类型的页数");
            $(".goNo").val($(".pageNo").val());
            return;
        }
        goNo = parseInt(goNo);
        total = parseInt(total);
        if (goNo < 1) {
            goNo = 1;
        }
        if (goNo > total) {
            goNo = total;
        }
        $(".pageNo").val(goNo);
        $(".form-search").submit();//提交查询表单
    });
});