<%--
  Created by IntelliJ IDEA.
  User: xiaozhi
  Date: 2019/12/17
  Time: 16:20
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- use EL-Expression-->
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>学生管理|注册页面</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/static/layui/css/layui.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/loginAndRegister.css" rel="stylesheet">
</head>
<script>
    layui.use(['layer'],function () {
        var layer = layui.layer;
    });
    $(function () {
        // 注册事件
        $('#registerBtn').click(function () {
            register();
        });
    });

    // 校验用户名、密码、用户
    function validateCode() {
        //输入的用户名
        var name = $('#userName').val();
        //表单输入的密码
        var password = $('#password').val();
        //确认密码
        var wellPassword = $('#wellPassword').val();
        //选择的用户
        var userType = $('#Select option:selected').val();
        if ($.trim(name) == '' || $.trim(name).length<=0){
            layer.alert("用户名不能为空");
            return false;
        }
        if ($.trim(password) == '' || $.trim(password).length<=0){
            layer.alert("密码不能为空");
            return false;
        }
        if ($.trim(wellPassword) == '' || $.trim(wellPassword).length<=0){
            layer.alert("确认密码不能为空");
            return false;
        }
        if ($.trim(userType)==''||$.trim(userType).length<=0){
            layer.alert("请选择用户角色");
            return false;
        }
        if (password!==wellPassword){
            layer.alert("两次密码输入不一致!");
            return false;
        }
        return true;
    }

    // 注册流程
    function register() {
        if (!validateCode()){
            //阻断提示
        } else {
            //输入的用户名
            var name = $('#userName').val();
            //表单输入的密码
            var password = $('#password').val();
            //选择的用户
            var userType = $('#Select option:selected').val();

            console.log(name+" "+userType+" "+password);
            var params = {};
            params.name = name;
            params.password = password;
            params.userType = userType;
            var registerLoadIndex = layer.load(2);
            $('#registerBtn').val("正在注册...");
            $.ajax({
                type:'post',
                url:"${pageContext.request.contextPath}/system/register",
                dataType:'json',
                contentType:"application/json; charset=utf-8",//此处不能省略
                data:JSON.stringify(params),
                success:function (data) {
                    layer.close(registerLoadIndex);
                    if (data.success){
                        window.location.href = "${pageContext.request.contextPath}/system/goLogin";
                    }else {
                        $.messager.alert("提示", data.msg);
                    }
                },
                error:function () {
                    layer.close(registerLoadIndex);
                    alert("无法注册!")

                }
            });

        }

    }
</script>
<body>
<div class="wrap">
    <img src="${pageContext.request.contextPath}/static/images/back.jpg" class="imgStyle">
    <div class="loginForm">
        <form id="form">
            <div class="logoHead">
                <h2>学生管理系统|注册页面</h2>
            </div>
            <div class="usernameWrapDiv">
                <div class="usernameLabel">
                    <label>用户名:</label>
                </div>
                <div class="usernameDiv">
                    <i class="layui-icon layui-icon-username adminIcon"></i>
                    <input id="userName" class="layui-input adminInput" type="text" name="username" placeholder="输入用户名" >
                </div>
            </div>
            <div class="usernameWrapDiv">
                <div class="usernameLabel">
                    <label>密码:</label>
                </div>
                <div class="passwordDiv">
                    <i class="layui-icon layui-icon-password adminIcon"></i>
                    <input id="password" class="layui-input adminInput" type="password" name="password" placeholder="输入密码">
                </div>
            </div>
            <div class="usernameWrapDiv">
                <div class="usernameLabel">
                    <label>确认密码:</label>
                </div>
                <div class="passwordDiv">
                    <i class="layui-icon layui-icon-password adminIcon"></i>
                    <input id="wellPassword" class="layui-input adminInput" type="password" name="wellPassword" placeholder="输入密码">
                </div>
            </div>
            <div class="usernameWrapDiv">
                <div class="usernameLabel">
                    <label>用户:</label>
                </div>
                <div class="passwordDiv">
                    <select id="Select" class="layui-select">
                        <option value="">请选择...</option>
                        <option value="1">管理员</option>
                        <option value="2">教师</option>
                        <option value="3">学生</option>
                    </select>
                </div>
            </div>

            <div class="usernameWrapDiv">
                <div class="submitDiv">
                    <input id="registerBtn" type="button" class="submit layui-btn layui-btn-primary" value="注册">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
