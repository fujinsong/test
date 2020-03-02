function showdrop(id) {
    //每次添加一栏，循环次数加一
    for (var j = 1; j <= 11; j++) {
        var a = document.getElementById("ul" + j);
        if (a.id == id) {

            a.style.display = "inline";
        } else
            a.style.display = "none";
    }

}

function setlis(tid, href) {
    for (var j = 1; j <= 4; j++) {
        var a = document.getElementById("ul" + j).getElementsByTagName("li");

        for (var i = 0; i < a.length; i++) {
            if (a[i].id == tid) {
                a[i].style.color = "#00688B";

            } else {
                a[i].style.color = "#19AADA";
            }
        }
    }
    top.mainFrame.location.href = href;
}

$(document).ready(function () {
    function getTree() {
        var tree = [
            {
                text: "用户管理",
                href: $(".ctx").val()+"user/list"
            },
            {
                text: "角色管理",
                href: $(".ctx").val()+"role/list"
            }
            ,
            {
                text: "菜单管理",
                href: $(".ctx").val()+"menu/list"
            }
        ];
        return tree;
    }

    $('#tree').treeview({
        data: getTree()/*,
        enableLinks:true*/,
        selectedBackColor: "#B4DEF2",
        backColor: "#F5F5F5"
    });
    /**
     *  bootstrap treeview实现target功能，iframe中打开页面
     */
    $('#tree').on('nodeSelected', function (event, data) {
        console.log(JSON.stringify(data));
        console.log(data.text);
        console.log(data.href);
        if (data.href != null && data.href != '') {
            parent.document.getElementById("mainFrame").src = data.href;
        }
    });
});