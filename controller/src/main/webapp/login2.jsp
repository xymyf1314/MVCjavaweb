<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<!doctype html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>后台登录-X-admin2.1</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="js/jquery3.2.1.js"></script>
    <script src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <script type="text/javascript" src="./js/cookie.js"></script>
    <script src="https://ssl.captcha.qq.com/TCaptcha.js"></script>
    <style>
        #out {
            position: absolute;
            bottom: 130px;

        }


    </style>
    <script>
        $(window.callback = function (res) {
            console.log(res)
            // res（未通过验证）= {ret: 1, ticket: null}
            // res（验证成功） = {ret: 0, ticket: "String", randstr: "String"}
            if (res.ret === 0) {
                // alert(res.ticket)   // 票据
                <%--$.post("<%=request.getContextPath()%>/yzm.admin", {--%>
                <%--    ticket: res.ticket,--%>
                <%--    randstr: res.randstr--%>
                <%--}, function (slid) {--%>
                <%--    if (slid.)--%>
                <%--})--%>
                $.ajax({
                    url: "<%=request.getContextPath()%>/yzm.admin",
                    type: "post",
                    dataType: "json",
                    async: true,
                    data: {
                        ticket: res.ticket,
                        randstr: res.randstr
                    },
                    success: function (slid) {
                        // alert(slid);
                        if (slid.CaptchaMsg == "OK") {
                            $("#login").removeAttr("disabled");
                        }
                    }
                });
            }
        })

        $(function () {
// $.ajax({
//     url: "https://ssl.captcha.qq.com/ticket/verify"
//     data:{
//
//     }
// })
        })
    </script>
</head>
<body class="login-bg">
<% String msg = (String) request.getAttribute("msg");%>
<div class="login layui-anim layui-anim-up">
    <div class="message">网上商城后台管理</div>
    <div id="darkbannerwrap">
    </div>
    <span style="color: red;font-size: 18px"><%=msg == null ? "" : msg%></span>
    <form action="<%=request.getContextPath()%>/login2.admin" method="post" class="layui-form">
        <input name="aname" placeholder="用户名" type="text" lay-verify="required" class="layui-input">
        <hr class="hr15">
        <input name="apwd" lay-verify="required" placeholder="密码" type="password" class="layui-input">

        <hr style="clear: both" class="hr15">
        <hr style="clear: both" class="hr15">
        <hr style="clear: both" class="hr15">

        <input id="login" value="登录" style="width:100%;" type="submit" disabled="disabled">
        <hr class="hr20">
    </form>
    <div id="out">
        <!--点击此元素会自动激活验证码-->
        <!--id : 元素的id(必须)-->
        <!--data-appid : AppID(必须)-->
        <!--data-cbfn : 回调函数名(必须)-->
        <!--data-biz-state : 业务自定义透传参数(可选)-->
        <button  id="TencentCaptcha" data-appid="2096417182" data-cbfn="callback">验证</button>
    </div>
</div>

<script>
    // $(function  () {
    //     layui.use('form', function(){
    //       var form = layui.form;
    //       // layer.msg('玩命卖萌中', function(){
    //       //   //关闭后的操作
    //       //   });
    //       //监听提交
    //       form.on('submit(login)', function(data){
    //         // alert(888)
    //         layer.msg(JSON.stringify(data.field),function(){
    //             location.href='index.html'
    //         });
    //         return false;
    //       });
    //     });
    // })

</script>


<!-- 底部结束 -->
<script>
    //百度统计可去掉
    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
</body>
</html>