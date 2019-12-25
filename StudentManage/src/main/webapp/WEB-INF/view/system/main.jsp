<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>学生管理|主页面</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <link href="${pageContext.request.contextPath}/static/layui/css/layui.css" rel="stylesheet">
    <style type="text/css">
        .layui-tab ul.layui-tab-title li:nth-child(1) i {
            display: none;
        }
    </style>
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">学生管理系统</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <!-- 获取用户登录类型 -->
                    <c:choose>
                        <c:when test="${userType==1 }">管理员 : </c:when>
                        <c:when test="${userType==2 }">学生 : </c:when>
                        <c:when test="${userType==3}">教师 : </c:when>
                    </c:choose>
                    <%-- 从Session中获取登录用户的用户名	--%>
                    <span style="color:red; font-weight:bold;">${user.name}</span>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">修改密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/system/logout">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a  href="javascript:;">学生管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-id="1" data-title="学生列表" data-url="/student/goStudentListView"
                               class="site-demo-active" data-type="tabAdd">学生列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">教师管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-id="2" data-title="教师列表" data-url="/teacher/goTeacherListView"
                               class="site-demo-active" data-type="tabAdd">教师列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">班级管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-id="3" data-title="班级列表" data-url="/clazz/goClazzListView"
                               class="site-demo-active" data-type="tabAdd" >班级列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">年级管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-id="4" data-title="年级列表" data-url="/grade/goGradeListView"
                               class="site-demo-active" data-type="tabAdd" >年级列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">管理员管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-id="5" data-title="管理员列表" data-url="${pageContext.request.contextPath}/admin/goAdminListView"
                               class="site-demo-active" data-type="tabAdd">管理员列表</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <!--tab标签-->
    <div class="layui-tab" lay-filter="demo" lay-allowclose="true" style="margin-left: 200px;">
        <ul class="layui-tab-title">
            <li class="layui-this">系统首页</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <jsp:include page="intro.jsp"></jsp:include>
            </div>
        </div>
    </div>

    <div class="layui-footer" align="center">
        <!-- 底部固定区域 -->
        学生信息管理平台 &copy person.小智 | 版权所有
    </div>
</div>
<script>
    //JavaScript代码区域
    layui.use(['element', 'layer', 'jquery'], function () {
        var element = layui.element;
        var $ = layui.$;

        // 配置tab实践在下面无法获取到菜单元素
        $('.site-demo-active').on('click', function () {
            var dataid = $(this);
            //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
            if ($(".layui-tab-title li[lay-id]").length <= 0) {
                //如果比零小，则直接打开新的tab项
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
            } else {
                //否则判断该tab项是否以及存在
                var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
                $.each($(".layui-tab-title li[lay-id]"), function () {
                    //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                    if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                        isData = true;
                    }
                })
                if (isData == false) {
                    //标志为false 新增一个tab项
                    active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
                }
            }
            //最后不管是否新增tab，最后都转到要打开的选项页面上
            active.tabChange(dataid.attr("data-id"));
        });

        var active = {
            //在这里给active绑定几项事件，后面可通过active调用这些事件
            tabAdd: function (url, id, name) {
                //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
                //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
                element.tabAdd('demo', {
                    title: name,
                    content: '<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>',
                    id: id //规定好的id
                })
                FrameWH();  //计算ifram层的大小
            },
            tabChange: function (id) {
                //切换到指定Tab项
                element.tabChange('demo', id); //根据传入的id传入到指定的tab项
            },
            tabDelete: function (id) {
                element.tabDelete("demo", id);//删除
            }
        };
        function FrameWH() {
            var h = $(window).height();
            $("iframe").css("height",h+"px");
        }
    });
</script>
</body>
</html>
