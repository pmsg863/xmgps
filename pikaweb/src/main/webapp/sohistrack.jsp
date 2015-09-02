<%--
  Created by IntelliJ IDEA.
  User: Herrfe
  Date: 14-5-30
  Time: 下午3:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        body, html, #allmap {
/*            width: 80%;*/
            height: 550px;
            margin: 0px;
        }
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=RNtnnDoVmQDbc4NVFq8s6038"></script>
    <title>轨迹回放</title>
    <link rel="stylesheet" type="text/css" href="../jslib/admin.css" media="all">
    <link rel="stylesheet" type="text/css" href="../jslib/master.css" media="all">
    <script type="text/javascript" src="../jslib/app.js"></script>
    <script type="text/javascript" src="../jslib/LuShu_min.js"></script>
    <script type="text/javascript" src="../jslib/jquery.js"></script>
    <script type="text/javascript" src="../jslib/jquery-1.js"></script>
    <script type="text/javascript" src="../jslib/jquery-ui-1.js"></script>
    <link rel="stylesheet" type="text/css" href="../jslib/jquery-ui-1.css" media="all">
    <script type="text/javascript" src="../etc/GPSHelper.js"></script>
    <script type="text/javascript" src="../etc/My97DatePicker/WdatePicker.js"></script>

    <%--时间控件--%>
    <link rel="stylesheet" href="../jslib/kalendae.css" type="text/css" charset="utf-8">
    <script src="../jslib/kalendae.standalone.min.js" type="text/javascript" charset="utf-8"></script>
    <style type="text/css" media="screen">
        .kalendae span.closed {
            background: red;
        }
    </style>

</head>
<body>
<div id="messageBox" style="display:none">
    <p id="messageContent" style="margin-top:5px"></p>
</div>

<div style="position: relative; top: 50px; left: 50px;/*padding-top: 5px;*//* margin: 50px;*/">

    <div class="mainlist_zhuce_left" style="height:auto;width:230px;margin-bottom:70px;">
        <div class="mainlist_zhuce_right_1">轨迹查询</div>

        <div class="mainlist_zhuce_select_block">
            <select name="select" id="carno" class="mainlist_zhuce_select_1" style="line-height:35px;width: 190px;">
            </select>
        </div>

        <div class="mainlist_input">
            <input style="line-height:35px;width: 180px;" class="mainlist_input_item_register"  id="starttime"
                   placeholder="请输入要查询的开始时间"
                   type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'endtime\',{d:-7});}',maxDate:'#F{$dp.$D(\'endtime\',{d:0});}'})"/>

        </div>

        <div class="mainlist_input">
            <input style="line-height:35px;width: 180px;" class="mainlist_input_item_register"  id="endtime"
                   placeholder="请输入要查询的结束时间"
                   type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starttime\',{d:0});}',maxDate:'#F{$dp.$D(\'starttime\',{d:7});}'})"/>

        </div>

        <button class="mainlist_login_btn_item" id="registerinfo"
                style="background:url(../images/login_btn_hover.png)no-repeat;clear:left;float:left">查询
        </button>

        <div style="margin-top: 100px;">

            <div class="managemen_mon_pic_bar" style="float:left;">
                <div class="managemen_mon_pic_bar_title" style="padding-right:10px">回放速度</div>
                <div style="width :35px; background-color: rgb(62, 160, 209); color: rgb(255, 255, 255);" id="slow"
                     class="managemen_mon_pic_bar_button">慢
                </div>
                <div style="width :35px;background-color: rgb(255, 255, 255); color: rgb(102, 102, 102);" id="normal"
                     class="managemen_mon_pic_bar_button">正常
                </div>
                <div style="width :35px;background-color: rgb(255, 255, 255); color: rgb(102, 102, 102);" id="fast"
                     class="managemen_mon_pic_bar_button">快
                </div>
                <div style="width :35px;background-color: rgb(255, 255, 255); color: rgb(102, 102, 102);" id="sofast"
                     class="managemen_mon_pic_bar_button">恐怖
                </div>
            </div>

            <div class="managemen_mon_pic_bar" style="float:left;width:210px;">
                <div class="managemen_mon_pic_bar_title" style="padding-right:10px">回放进度</div>
                <div id="pos1"
                     style="float:left;width :35px;background-color: rgb(255, 255, 255); color: rgb(102, 102, 102);height:23px; background:#FFFFFF;  margin-top:1px;text-align:center;border-color: #CCCCCC; border-style: solid solid solid none; border-width: 1px;">
                    0
                </div>
                <div id="slider"
                     style="margin-top:10px;margin-left:120px;background-color: rgb(62, 160, 209); color: rgb(255, 255, 255);"></div>
            </div>

            <button class="mainlist_login_btn_item" id="replay"
                    style="background:url(../images/login_btn_hover.png)no-repeat;clear:left;float:left">回放
            </button>

        </div>


    </div>
</div>

<%--<div style="width:96%;height:600px;position: relative; left: 50px;">--%>
    <div id="allmap" style="width:78%;height:600px;position: relative; left: 50px;"></div>

<div id="instances_list"  style="width:100%;">

    <div class="managemen_mon_record_searchbar" style="text-align:left;margin-top: 2px;">&nbsp;&nbsp;轨迹信息
        <span id="now_page" style="display:none">1</span>
        <span id="end_log" style="display:none">0</span>

        <div id="pagination" style="float:right">
            <%--            <button id="page_up">上一页</button>
                        <button id="page_down">下一页</button>--%>
        </div>
    </div>

    <table id="appContent" cellpadding="0" cellspacing="0" border="0">
        <tbody>
        <tr id="instances_th" class="managemen_mon_title">
            <th class="managemen_mon_title_box1">车辆编码</th>
            <th class="managemen_mon_title_box2">车牌号</th>
            <th class="managemen_mon_title_box3">定位标志</th>
            <th class="managemen_mon_title_box4">地址</th>
            <th class="managemen_mon_title_box5">时间</th>
            <th class="managemen_mon_title_box6">高度</th>
            <th class="managemen_mon_title_box7">速度</th>
            <th class="managemen_mon_title_box8">操作</th>
        </tr>
        </tbody>
    </table>


</div>

</body>
</html>
<script type="text/javascript">
//数据库轨迹
var cartrack;
//百度地图建议轨迹
var routetrack = new Array();
// 百度地图API功能
var map = initBaiduMap("allmap");
//车辆图标
var carMk;
//播放速度
var speed = 25;
//路书
var lushu;

function initBaiduMap(divname) {
    /*    百度地图API功能*/
    var map = new BMap.Map(divname);
    /*创建Map实例*/
    map.centerAndZoom(new BMap.Point(118.148, 24.734), 11);
    /* 初始化地图,设置中心点坐标和地图级别*/
    map.addControl(new BMap.NavigationControl());
    /* 添加平移缩放控件*/
    map.addControl(new BMap.ScaleControl());
    /*添加比例尺控件*/
    map.addControl(new BMap.OverviewMapControl());
    /*添加缩略地图控件*/
    map.enableScrollWheelZoom();
    /*启用滚轮放大缩小*/
    map.addControl(new BMap.MapTypeControl());
    /*添加地图类型控件*/
    map.setCurrentCity("厦门");
    /*设置地图显示的城市 此项是必须设置的*/
    /*    添加默认缩放平移控件*/
    map.enableScrollWheelZoom();
    map.addControl(new BMap.NavigationControl());
    return map;
}

var myIcon = new BMap.Icon("../images/car.png", new BMap.Size(32, 70), {    //小车图片
    imageOffset: new BMap.Size(0, -5)    //图片的偏移量。为了是图片底部中心对准坐标点。
});

function addcars(carpos) {
    //最后一列
    var tr = document.getElementById("appContent").insertRow();
    tr.className = "managemen_mon_info";

    var td = tr.insertCell();
    td.innerHTML = carpos.id;
    td.className = "managemen_mon_info_box1";
    td = tr.insertCell();
    td.innerHTML = carpos.carnumber;
    td.className = "managemen_mon_info_box2";
    td = tr.insertCell();
    td.innerHTML = carpos.location == 0 ? "定位" : "非定位";
    td.className = "managemen_mon_info_box3";
    td = tr.insertCell();
    td.innerHTML = "厦门软件园二期";
    td.className = "managemen_mon_info_box4";
    td = tr.insertCell();
    td.innerHTML = carpos.gpstime;
    td.className = "managemen_mon_info_box5";
    td = tr.insertCell();
    td.innerHTML = carpos.high + "m";
    td.className = "managemen_mon_info_box6";
    td = tr.insertCell();
    td.innerHTML = carpos.speed + "km/h";
    td.className = "managemen_mon_info_box7";
    td = tr.insertCell();
    td.className = "managemen_mon_info_box3";
    td.innerHTML = "<span class='managemen_mon_info_sp2' style='cursor:pointer;'" +
            "onclick='getPosition(this)'>信息查询</span><span class='managemen_mon_info_sp2' " +
            "style='cursor:pointer;margin-left:10px;'" +
            "onclick='getPosition(this)'>轨迹回放</span>";

}

function gettrack() {
    var paths = new Array();
    $.each(cartrack, function (index, track) {
        paths.push(WGS2BD(new BMap.Point(track.longtitude / 1000000, track.latitude / 1000000), new BMap.Point(0, 0)));
    });

/*    var polyline = new BMap.Polyline(paths, {
        strokeColor: "blue",
        strokeWeight: 3,
        strokeOpacity: 0.5 ,
        enableClicking:false
    });
    map.addOverlay(polyline);*/

    lushu = new BMapLib.LuShu(map,paths,{
        defaultContent:"轨迹回放",
        speed:speed*20,
        icon:myIcon,
        landmarkPois: [
/*            {lng:paths[0].lng,lat:paths[0].lat,html:'加油站',pauseTime:2},*/
            {lng:paths[0].lng,lat:paths[0].lat,html:'<div><img src="../images/trans_icons_start.png"/></div>',pauseTime:2},
            {lng:paths[paths.length-1].lng,lat:paths[paths.length-1].lat,html:'<div><img src="../images/trans_icons_end.png"/></div>',pauseTime:2}
        ],
        mycallback:function (percent){
            /* 设置进度*/
            $("#pos1")[0].innerHTML = Math.round( percent );
            $("#slider").slider( "option", "value", Math.round( percent ) );
        }
    });

    lushu.addPaths(paths);


}

function cleanlastpos() {
    map.clearOverlays();
    cartrack = null;
    //百度地图建议轨迹
    routetrack = new Array();
    //清除除标题的所有数据
    var table = document.getElementById("appContent");
    trs = table.getElementsByTagName("tr");
    for (var i = trs.length - 1; i > 0; i--) {
        table.deleteRow(i);
    }
    if( lushu!=null ) {
        lushu.pause();
        lushu.stop;
    }
    lushu=null;
    //进度条归零
    $("#pos1")[0].innerHTML = Math.round( 0 );
    $("#slider").slider( "option", "value", Math.round( 0 ) );
}

$("#registerinfo").click(function () {
    cleanlastpos();

    var trackparam = new Object();
    trackparam.carno = $("#carno").val();
    trackparam.starttime = $("#starttime").val();
    trackparam.endtime = $("#endtime").val();

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: encodeURI("../cars/hisposition.do?carno=" + trackparam.carno + "&starttime=" + trackparam.starttime + "&endtime=" + trackparam.endtime),
        error: function (xhr) {
            showMessageBox("轨迹获取失败，请记录并联系开发！", 1);
        },
        success: function (data) {
            cartrack = eval(data);
            showMessageBox("轨迹获取成功，总记录数"+cartrack.length, 0);

            var lasttrack;
            $.each(cartrack, function (index, track) {
                if (typeof(lasttrack) == "undefined") {
                    var firstpoint = WGS2BD(new BMap.Point(track.longtitude / 1000000, track.latitude / 1000000), new BMap.Point(0, 0));
                    map.setZoom(17);
                    map.panTo(firstpoint);

                    lasttrack = track;
                    return true;
                }
            })
            gettrack();
        }

    });

});

$("#replay").click(function () {
    lushu.start();
});


$(document).ready(function () {

    //默认选中第“一小时”
    $(".managemen_mon_pic_bar #slow").css("background-color", "#3EA0D1");
    $(".managemen_mon_pic_bar #slow").css("color", "#FFFFFF");

    //选择时间段，显示访问量
    $(".managemen_mon_pic_bar_button").click(function () {
        $(".managemen_mon_pic_bar_button").css("background-color", "#FFFFFF");
        $(".managemen_mon_pic_bar_button").css("color", "#666666");
        $(this).css("background-color", "#3EA0D1");
        $(this).css("color", "#FFFFFF");
        var id = $(this).attr("id");
        if (id == "slow")
            speed = 25;
        else if ( id == "normal")
            speed = 50;
        else if ( id = "fast")
            speed = 80;
        else if  ( id = "sofast")
            speed = 100;
        lushu.setSpeed(speed*20);

    });


    $("#slider").slider({ max: 100 });
    $("#slider").slider({
        slide: function (event, ui) {
            var step = $("#slider").slider("option", "value");
            $("#pos1")[0].innerHTML = Math.round(step);
        },
        stop: function (event, ui) {
            var step = $("#pos1")[0].innerHTML;
        } });
    $.ajax({
        type: "get",
        url: "../cars/getCars.do",
        error: function(xhr){
            showMessageBox("获取车辆信息错误，请记录并联系开发！",1);
        },
        success: function(data){
            var cars = eval(data);
            $.each( cars, function(index, vehicle)
            {
                $("#carno").append("<option value='"+vehicle.carno+"'>"+vehicle.carno+"</option>");  //为Select追加一个Option(下拉项)
            });

        }
    });


});

</script>


