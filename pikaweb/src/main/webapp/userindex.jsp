<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>应用首页</title>
    <link rel="shortcut icon" href="http://www.mopaas.com/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="jslib/admin.css" media="all">
    <link rel="stylesheet" type="text/css" href="jslib/wcheng.css" media="all">
    <link rel="stylesheet" type="text/css" href="jslib/master.css" media="all">
    <link rel="stylesheet" type="text/css" href="jslib/listapp.css" media="all">
    <link rel="stylesheet" type="text/css" href="jslib/code.css" media="all">
    <link rel="stylesheet" type="text/css" href="jslib/delApp.css" media="all">
    <link rel="stylesheet" type="text/css" href="jslib/upgrade.css" media="all">
    <link rel="stylesheet" type="text/css" href="jslib/domain.css" media="all">
    <link rel="stylesheet" type="text/css" href="jslib/jquery-ui-1.css"
          media="all">
    <script type="text/javascript" src="jslib/jquery-1.js"></script>
    <script type="text/javascript" src="jslib/jquery.js"></script>
    <script type="text/javascript" src="jslib/jquery-ui-1.js"></script>
    <script type="text/javascript" src="jslib/app.js"></script>
    <script type="text/javascript" src="jslib/form.js"></script>
    <script type="text/javascript">
    </script>
</head>

<body>

<!-- 提示框  -->
<div id="messageBox" style="display:none">
    <p id="messageContent" style="margin-top:5px"></p>
</div>

<!--顶部-->
<div class="header">
    <div class="header_2">
        <div class="home_logo">
            <img src="images/logo_zh-CN.png"
                 onclick="location.href='./portal.jsp';">
        </div>
        <div class="home_header_title">
            <div class="home_header_title_1"><a href="./userindex.jsp" class="bar_title">定位监控</a>
            </div>
            <div class="home_header_title_1"><a href="./etc/nofind.jsp" class="bar_title">统计报表</a>
            </div>
            <div class="home_header_title_1"><a href="./etc/nofind.jsp" class="bar_title">设备管理</a>
            </div>
            <div class="home_header_title_1"><a href="./etc/nofind.jsp" class="bar_title">套餐管理</a>
            </div>
            <div class="home_header_title_1"><a id="helpdocs" class="bar_title" style="cursor:pointer"
                                                href="./etc/nofind.jsp"
                                                target="_blank">文档中心</a></div>
            <div class="home_header_title_1"><a id="homeforum" class="bar_title" style="cursor:pointer"
                                                href="./etc/nofind.jsp" target="_blank">论坛</a></div>
        </div>
        <div class="right">
            <div class="left">
                <div class="left">
                    <img id="remind_img" src="images/haveMessage.png"
                         class="header_remind_img" onclick="document.location.href='./etc/nofind.jsp'">
                    <!--  onclick="document.location.href='../user/remind.jsp'" onclick="remindInMaintenance()"   -->
                </div>
                <div style="position:relative;">
                    <div class="remind_img_num_absolute">
                        <span id="remind_img_num">1</span>
                    </div>
                </div>
            </div>
            <div id="login_info" class="user_list">
                <div title="huangwb" style="padding-left: 42.5px;" id="userInfo" class="user_list_name">
                    <div class="user_list_name_icon"></div>
                    <div id="email" class="user_list_name_text">${userinfo.username}</div>
                </div>
                <div class="clear"></div>
                <div id="operate_list" style="position: relative; display: none;">
                    <div class="user_info_operate_list_absolult" style="z-index:10">
                        <!-- <div onclick="document.location.href='../user/user_info.jsp'">个人信息</div> -->
                        <div onclick="window.location.href='./etc/nofind.jsp'">我的优惠</div>
                        <div onclick="window.location.href='./etc/nofind.jsp'">支付详细</div>
                        <div onclick="window.location.href='./etc/nofind.jsp'">修改密码</div>
                        <div onclick="checkSMSauthor()">短信服务</div>
                        <div id="logout">退出登录</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>


</script>


<link rel="stylesheet" type="text/css" href="images/servicemanage.css" media="all">
<%--<div class="managemen_column">
    <!-- 用户登录信息  显示在头部 -->
    <div id="appId" style="display:none">12494</div>
    <div id="appState" style="display:none">STARTED</div>
    <div id="serviceCount" style="display:none">20</div>
    <div class="left">
        <div class="appinfo_1_1icon_inside"><img src="images/icon-javaweb.png">
        </div>
        <div class="appinfo_1_1url"><a href="http://hwbtest.sturgeon.mopaas.com/" target="_blank">hwbtest</a></div>
    </div>
</div>--%>
<div class="maincontent">
    <div class="managemen_buttonlist left" style="width:17%">
        <div id="app_detail" class="managemen_button managemen_button_select app_detail_select app_detail">
            车辆监控
        </div>
        <div id="app_serv" class="managemen_button app_serv">车辆管理</div>
        <div id="app_env" class="managemen_button app_env">轨迹回放</div>
        <div id="app_code" class="managemen_button app_code">报警信息</div>
        <div id="app_dom" class="managemen_button app_dom">域名管理</div>
        <div id="app_status" class="managemen_button app_status">警报日志</div>
        <div id="app_del" class="managemen_button app_del">百度地图</div>
    </div>

    <div class="mainlist_2 left" style="width:82%">
        <iframe src="./spring/redirect.do?page=baidumap" frameborder="no" name="ifrm" id="ifrm" width="100%" height="700px"></iframe>
    </div>
</div>


<script type="text/javascript">
    //点击菜单，改变导航
    $(".managemen_buttonlist .managemen_button").click(function () {
        $(".managemen_button_select").removeClass("managemen_button_select");
        removeSelectClass("app_detail_select", "app_serv_select", "app_env_select", "app_code_select", "app_dom_select", "app_status_select", "app_del_select");
        var id = $(this).attr("id");
        $(this).addClass(id + "_select");
        $(this).addClass("managemen_button_select");
    });

    function removeSelectClass() {
        var argu = arguments;
        for (var i = 0; i < argu.length; i++) {
            if ($("." + argu[i]) != 'undefined') {
                $("." + argu[i]).removeClass(argu[i]);
            }
        }
    }


    //显示服务
    $("#app_detail").click(function(){
        showInfo("./spring/redirect.do?page=baidumap");
    });
    //显示服务
    $("#app_serv").click(function(){
        showInfo("./spring/redirect.do?page=carsmanager");
    });

    $("#app_env").click(function(){
        showInfo("./spring/redirect.do?page=sohistrack");
    });

    $("#app_code").click(function(){
        showInfo("./spring/redirect.do?page=sohistrack");
    });

    $("#app_dom").click(function(){
        showInfo("./spring/redirect.do?page=sohistrack");
    });

    $("#app_del").click(function(){
        showInfo("./spring/redirect.do?page=sohistrack");
    });



    function showInfo(urlpath){
        $.ajax({
            url:urlpath,
            success : function(data) {
/*                alert("成功！");*/
                /*$(".mainlist_2").html(data);*/
/*                $(".mainlist_2").load(urlpath);*/
                document.getElementById("ifrm").src = urlpath;


            } ,
            error: function(data){
                alert("失败！");
            },exception: function(data){
                alert("exception");
            }
        });

    }

    //移至用户邮箱 下拉
    $("#login_info").hover(function () {
        $("#operate_list").show();
        //	$("#userInfo").addClass("user_list_name_selected");
    }, function () {
        //	$("#userInfo").removeClass("user_list_name_selected");
        $("#operate_list").hide();
    });



</script>
</body>
</html>