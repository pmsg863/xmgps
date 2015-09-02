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
            width: 100%;
            height: 500px;
            /*overflow: hidden;*/
            margin: 0px;
        }
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=RNtnnDoVmQDbc4NVFq8s6038"></script>
    <title>轨迹回放</title>
    <link rel="stylesheet" type="text/css" href="./jslib/admin.css" media="all">
    <link rel="stylesheet" type="text/css" href="./jslib/master.css" media="all">
    <script type="text/javascript" src="./jslib/app.js"></script>
    <script type="text/javascript" src="./jslib/jquery-1.js"></script>
    <script type="text/javascript" src="./jslib/jquery.js"></script>
    <script type="text/javascript" src="./jslib/jquery-ui-1.js"></script>
    <%--时间控件--%>
    <link rel="stylesheet" href="./jslib/kalendae.css" type="text/css" charset="utf-8">
    <script src="./jslib/kalendae.standalone.min.js" type="text/javascript" charset="utf-8"></script>
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

<div id="allmap"></div>

<div style="padding-top: 5px; margin: 50px;">

<div class="mainlist_zhuce_left" style="height:auto;width:220px;margin-bottom:70px;">
    <div class="mainlist_zhuce_right_1">轨迹查询</div>

    <div class="mainlist_input">
        <input style="line-height:35px;width: 180px;" class="mainlist_input_item_register" id="carno" placeholder="请输入您的车牌号码"
               type="text">
    </div>

    <div class="mainlist_input">
        <input style="line-height:35px;width: 180px;" class="mainlist_input_item_register" id="starttime" placeholder="请输入要查询的开始时间"
               type="text">
        <script type="text/javascript" charset="utf-8">
            var k4 = new Kalendae.Input('starttime', {
                months: 1
            });
        </script>
    </div>

    <div class="mainlist_input">
        <input style="line-height:35px;width: 180px;" class="mainlist_input_item_register" id="endtime" placeholder="请输入要查询的结束时间"
               type="text">
        <script type="text/javascript" charset="utf-8">
            var k4 = new Kalendae.Input('endtime', {
                months: 1
            });
        </script>

    </div>

    <button class="mainlist_login_btn_item" id="registerinfo"
            style="background:url(./images/login_btn_hover.png)no-repeat;clear:left;float:left">查询
    </button>
</div>

<div id="instances_list" class="mainlist_zhuce_right" style="width:80%;">

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
</div>

</body>
</html>
<script type="text/javascript">

    // 百度地图API功能
    var map = initBaiduMap("allmap");

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

    var polyline = new BMap.Polyline([
        new BMap.Point(109.867482, 39.717835),
        new BMap.Point(109.967482, 39.717835),
        new BMap.Point(109.967482, 39.727835),
        new BMap.Point(109.867482, 39.717835)
    ], {
        strokeColor: "blue",
        strokeWeight: 6,
        strokeOpacity: 0.5
    });
    map.addOverlay(polyline);

    var myP1 = new BMap.Point(118.148, 24.734);    //起点
    var myP2 = new BMap.Point(118.848, 24.934);    //终点
    var myP3 = new BMap.Point(118.948, 24.994);    //终点
    var myIcon = new BMap.Icon("./images/car.png", new BMap.Size(32, 70), {    //小车图片
        //offset: new BMap.Size(0, -5),    //相当于CSS精灵
        imageOffset: new BMap.Size(0, 0)    //图片的偏移量。为了是图片底部中心对准坐标点。
    });

    //绘制路线   var driving2 = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});    //驾车实例
    var driving1 = new BMap.DrivingRoute(map);    //驾车实例
    driving1.search(myP1, myP2);    //显示一条公交线路
    driving1.setSearchCompleteCallback(function () {
        var track1 = driving1.getResults();
        var steps = track1.getPlan(0).getRoute(0).getNumSteps();
        for (var i = 0; i < steps; i++) {
            var step = track1.getPlan(0).getRoute(0).getStep(i);
            var carpos = new Object;
            carpos.id = i;
            carpos.carnumber = "MD111"
            carpos.location = 0;
            carpos.gpstime = step.getDescription(true);
            carpos.high = step.getPosition().lat + " " + step.getPosition().lng;
            carpos.speed = step.getDistance(true);
            addcars(carpos);
        }

        var polyline = new BMap.Polyline(track1.getPlan(0).getRoute(0).getPath(), {
            strokeColor: "red",
            strokeWeight: 6,
            strokeOpacity: 0.5
        });
        map.addOverlay(polyline);
    });

    var driving2 = new BMap.DrivingRoute(map);    //驾车实例
    driving2.search(myP2, myP3);
    driving2.setSearchCompleteCallback(function () {
        var track2 = driving2.getResults();
        var steps = track2.getPlan(0).getRoute(0).getNumSteps();
        for (var i = 0; i < steps; i++) {
            var step = track2.getPlan(0).getRoute(0).getStep(i);
            var carpos = new Object;
            carpos.id = i;
            carpos.carnumber = "MD222"
            carpos.location = 0;
            carpos.gpstime = step.getDescription(true);
            carpos.high = step.getPosition().lat + " " + step.getPosition().lng;
            carpos.speed = step.getDistance(true);
            addcars(carpos);
        }
        var polyline = new BMap.Polyline(track2.getPlan(0).getRoute(0).getPath(), {
            strokeColor: "red",
            strokeWeight: 6,
            strokeOpacity: 0.5
        });
        map.addOverlay(polyline);
    });

    //绘制车辆移动
    window.run = function () {
        var driving = new BMap.DrivingRoute(map);    //驾车实例
        driving.search(myP1, myP2);
        driving.setSearchCompleteCallback(function () {
            var pts = driving.getResults().getPlan(0).getRoute(0).getPath();    //通过驾车实例，获得一系列点的数组
            var paths = pts.length;    //获得有几个点

            var carMk = new BMap.Marker(pts[0], {icon: myIcon});
            map.addOverlay(carMk);
            i = 0;
            function resetMkPoint(i) {
                carMk.setPosition(pts[i]);
                if (i < paths) {
                    setTimeout(function () {
                        i++;
                        resetMkPoint(i);
                    }, 100);
                }
            }

            setTimeout(function () {
                resetMkPoint(5);
            }, 100)

        });
    }

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

    setTimeout(function () {
        run();
    }, 1500);
</script>


