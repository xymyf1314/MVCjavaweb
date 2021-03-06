<%@include file="header.jsp" %>
<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="x-admin-sm">

<head>
    <meta charset="UTF-8">
    <title>管理员操作日志</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
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
        <button class="layui-btn" onclick="x_admin_show('添加用户','./admin-add.jsp')"><i class="layui-icon"></i>添加
        </button>
        <span class="x-right" style="line-height:40px">共有数据：88 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>管理员ID</th>
            <th>管理员名</th>
            <th>所做操作</th>
            <th>用户原id</th>
            <th>原用户名</th>
            <th>用户原密码</th>
            <th>用户原会员等级</th>
            <th>用户原手机号</th>
            <th>用户原地址</th>
            <th>用户原注册时间</th>
            <th>用户原账户状态</th>
            <th>操作时间</th>
            <th>回滚</th>
        </thead>
        <tbody>
        <c:forEach items="${adminOperationLogs}" var="adminOperationLog">
            <tr>
                <td>
                    <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i
                            class="layui-icon">&#xe605;</i></div>
                </td>
                <td class="aid">${adminOperationLog.id}</td>
                <td>${adminOperationLog.AName}</td>
                <td>${adminOperationLog.operation}</td>
                <td>${adminOperationLog.uid}</td>
                <td>${adminOperationLog.userName}</td>
                <td>${adminOperationLog.userPassword}</td>
                <td>${adminOperationLog.userGrade}</td>
                <td>${adminOperationLog.userPhone}</td>
                <td>${adminOperationLog.userAddress}</td>
                <td>${adminOperationLog.userRegisterDate}</td>
                <td>
                  <c:if test="${adminOperationLog.userDisable==1}">禁用</c:if>
                  <c:if test="${adminOperationLog.userDisable==0}">正常</c:if>
                </td>
                <td class="operationTime">${adminOperationLog.operationTime}</td>
                <td>
                        <%--              <a title="编辑"  onclick="x_admin_show(this,'<%=request.getContextPath()%>/load.admin?id=${adminLog.id}')" href="javascript:;">--%>
                        <%--                <i class="layui-icon">&#xe642;</i>--%>
                        <%--              </a>--%>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a title="回滚" onclick="member_del(this,'${adminOperationLog.operationTime}')" href="javascript:;">
                        <i class="layui-icon">&#xe640;</i>
                    </a>
                </td>
            </tr>
        </c:forEach>
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
        layer.confirm('确认要回滚吗？', function (index) {
            var children = $(obj).parents("tr").children('.aid').text();
            var operationTime = $(obj).parents("tr").children('.operationTime').text();
            console.log(children);
            console.log("___________");
            //发异步删除数据
            $.ajax({
                url: "<%=request.getContextPath()%>/rollback.admin",
                data: {
                    "aid": children,
                    "operationTime": operationTime
                },
                traditional: true,
                type: "POST"

            });
            $(obj).parents("tr").remove();
            layer.msg('已回滚!', {icon: 1, time: 1000});
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