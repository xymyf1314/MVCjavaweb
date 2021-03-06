<%@include file="header.jsp" %>
<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="x-admin-sm">

<head>
    <meta charset="UTF-8">
    <title>类别列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery3.2.1.js"></script>
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <script type="text/javascript" src="./js/cookie.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <%--<div class="layui-row">--%>
    <%--<form class="layui-form layui-col-md12 x-so">--%>
    <%--<input class="layui-input" placeholder="开始日" name="start" id="start">--%>
    <%--<input class="layui-input" placeholder="截止日" name="end" id="end">--%>
    <%--<input type="text" name="username"  placeholder="请输入用户名" autocomplete="off" class="layui-input">--%>
    <%--<button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>--%>
    <%--</form>--%>
    <%--</div>--%>
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加根类别','./rootcategories-add.jsp')"><i class="layui-icon"></i>添加根类别
        </button>
        <span class="x-right" style="line-height:40px">共有数据：88 条</span>
    </xblock>
    <script>
        $(function () {

            $.ajax({
                url: "<%=request.getContextPath()%>/productServlet",
                dataType: 'json',
                async: "post",
                data: {
                    method: "findToTree"
                },
                success: function (categories) {
                    // var eval1 = eval(categories);
                    console.log(categories);
                    findToTree(categories);
                }

            });

            // 定义一个递归函数
            function findToTree(categories) {
                var table = $("#table");
                for (var i in categories) {
                    var c = categories[i];
                    var tr = "<tr>\n" +
                        "            <td>\n" +
                        "                <div class=\"layui-unselect layui-form-checkbox\" lay-skin=\"primary\" data-id='2'>\n" +
                        "                    <i class=\"layui-icon\">&#xe605;</i>\n" +
                        "                </div>\n" +
                        "            </td>\n" +
                        "            <td class=\"aid\">" + c.id + "</td>\n" +
                        "            <td>" + c.categoryName + "</td>\n" +
                        "            <td>" + c.categoryDescription + "</td>\n" +
                        "            <td>" + c.categoryParentId + "</td>\n" +
                        "            <td>\n" +
                        (c.leaf == 1 ? "是" : "不是") +
                        "            </td>\n" +
                        "            <td>" + c.grade + "</td>\n" +
                        "            <td>\n" +
                        "                <a title=\"添加子类别\"\n" +
                        "                   onclick=\"x_admin_show('添加子类别','<%=request.getContextPath()%>/productServlet?method=load&id=" + c.id + "')\"\n" +
                        "                   href=\"javascript:;\">\n" +
                        "                    <i class=\"iconfont\">&#xe6b9;</i>\n" +
                        "                </a>\n" +
                        "                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                        "                <a title=\"删除\" onclick=\"member_del(this,'要删除的id')\" href=\"javascript:;\">\n" +
                        "                    <i class=\"layui-icon\">&#xe640;</i>\n" +
                        "                </a>\n" +
                        "            </td>\n" +
                        "        </tr>";
                    if (c.leaf == 0) {
                        // 不是叶子结点
                        $(tr).appendTo(table);
                        //递归
                        findToTree(c.children);
                    } else {
                        $(tr).appendTo(table);
                    }
                }
            }
        });

    </script>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>类别ID</th>
            <th>类别名称</th>
            <th>类别描述</th>
            <th>父类别ID</th>
            <th>是否是叶子结点</th>
            <th>类别级别</th>
            <th>操作</th>
        </thead>
        <tbody id="table">

        </tbody>
    </table>
    <div class="page">
        <div>
            <a class="prev" href="">&lt;&lt;</a>
            <a class="num" href="">1</a>
            <span class="current">2</span>
            <a class="num" href="">3</a>
            <a class="num" href="">489</a>
            <a class="next" href="">&gt;&gt;</a>
        </div>
    </div>

</div>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            // elem: '#end' //指定元素
        });
    });


    /*用户-删除*/
    function member_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            var children = $(obj).parents("tr").children('.aid').text();
            console.log(children);
            console.log("___________");
            //发异步删除数据
            $.ajax({
                url: '<%=request.getContextPath()%>/del.admin',
                data: {
                    "aid": children
                },
                traditional: true,
                type: "POST"

            });
            $(obj).parents("tr").remove();
            layer.msg('已删除!', {icon: 1, time: 1000});
        });
    }


    function delAll(argument) {

        var data = tableCheck.getData();

        layer.confirm('确认要删除吗？' + data, function (index) {
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
    }
</script>
<script>var _hmt = _hmt || [];
(function () {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();</script>
<script>
    // function  toload(id){
    //   layer.open({
    //     type: 2,
    //     title: '修改页',
    //     shadeClose: true,
    //     shade: 0.4,
    //     area: ['380px', '70%'],
    //     content: '/servletTest03_war/load.demo01?id='+id, //iframe的url
    //     end:function(){
    //     }
    //   });
    // }
</script>
</body>

</html>