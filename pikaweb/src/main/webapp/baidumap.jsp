<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
    <!--加载鼠标绘制工具-->
    <script type="text/javascript"
            src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>

    <link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css"/>
    <link rel="stylesheet" type="text/css" href="../jslib/admin.css" media="all">
    <link rel="stylesheet" type="text/css" href="../jslib/master.css" media="all">
    <script type="text/javascript" src="../jslib/app.js"></script>
    <script type="text/javascript" src="../jslib/jquery-1.js"></script>
    <script type="text/javascript" src="../jslib/jquery.js"></script>
    <script type="text/javascript" src="../jslib/jquery-ui-1.js"></script>
    <script type="text/javascript" src="../etc/GPSHelper.js"></script>

    <title>地图监控</title>
</head>
<body>
<!-- 提示框  -->
<div id="messageBox" style="display:none">
    <p id="messageContent" style="margin-top:5px"></p>
</div>

<div id="allmap"></div>
<div id="instances_list">

    <div class="managemen_mon_record_searchbar" style="text-align:left;margin-top: 2px;">&nbsp;&nbsp;车辆详情
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

<script type="text/javascript">

    var carsposition ;

    var map = initBaiduMap("allmap");

    function addMarker(point) {  // 创建图标对象
        var myIcon = new BMap.Icon("images/car.png", new BMap.Size(50, 50), {
            // 指定定位位置。
            // 当标注显示在地图上时，其所指向的地理位置距离图标左上
            // 角各偏移10像素和25像素。您可以看到在本例中该位置即是
            // 图标中央下端的尖角位置。
            offset: new BMap.Size(50, 50)
            // 设置图片偏移。
            // 当您需要从一幅较大的图片中截取某部分作为标注图标时，您
            // 需要指定大图的偏移位置，此做法与css sprites技术类似。
            /*imageOffset: new BMap.Size(0, 0 - index * 25)   // 设置图片偏移*/
        });
        // 创建标注对象并添加到地图
        var marker = new BMap.Marker(point, {icon: myIcon});

        map.addOverlay(marker);

        return marker;

    }

    function addInfoWindow(marker, title, htmlmsg) {
        var opts = {
            width: 250,     // 信息窗口宽度
            height: 100,     // 信息窗口高度
            title: title    // 信息窗口标题
        }
        var infoWindow = new BMap.InfoWindow(htmlmsg, opts);  // 创建信息窗口对象

        marker.addEventListener("mouseover", function () {
            map.openInfoWindow(infoWindow, marker.getPosition());      // 鼠标移入 打开信息窗口
        });
        marker.addEventListener("mouseout", function () {
            map.closeInfoWindow();       // 鼠标移出 关闭信息窗口
        });
        marker.addEventListener("click", function () {
            alert("可以在某个地区显示详细信息");      // 鼠标点击 查看信息窗口
        });

    }

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

    $(document).ready(function () {
        freshlastpos();
        //10秒刷新一次数据
        setInterval("freshlastpos()", 30000);
    });

    function freshlastpos() {
        cleanlastpos();

        $.ajax({
            type: "get",
            url: "../cars/position.do",
            error: function (xhr) {
                showMessageBox("获取车辆末次位置错误，请记录并联系开发！", 1);
            },
            success: function (data) {
                showMessageBox("获取车辆末次位置成功！", 0);
                carsposition = eval(data);
                $.each(carsposition, function (index, carpos) {

                    var script = document.createElement('script');
                    script.type = 'text/javascript';
                    script.src = 'http://api.map.baidu.com/geocoder/v2/?ak=RNtnnDoVmQDbc4NVFq8s6038&callback=renderReverse&location=39.983424,116.322987&output=json&pois=0';
                    document.body.appendChild(script);

                    addcars(carpos);
                    pincars(carpos);

                });

            }
        });
        ;
    }

    function getPosition(obj){
        var row = obj.parentNode.parentNode; //标签所在行
        var tb = row.parentNode;     //当前表格
        var rowIndex = row.rowIndex;  //A标签所在行下标

        var vehicle = new Object();
        vehicle.carid = row.childNodes[0].innerHTML;
        vehicle.carno = row.childNodes[1].innerHTML;
        vehicle.cartype = row.childNodes[2].innerHTML;

        $.each(carsposition, function (index, carpos) {
            if(carpos.carnumber==vehicle.carno){
                map.panTo( WGS2BD(new BMap.Point(carpos.longtitude / 1000000, carpos.latitude / 1000000),new BMap.Point(0, 0) ) )
                return
            }
        })

        return;
    }


    function renderReverse(response) {
        var html = '';
        if (response.status ) {
            return;
        }
        var location = response.result.location;
        location.lng;
        location.lat;
        response.result.formatted_address;

        return;
    }

    function cleanlastpos() {
        map.clearOverlays();
        //清除除标题的所有数据
        var table = document.getElementById("appContent");
        trs = table.getElementsByTagName("tr");
        for (var i = trs.length - 1; i > 0; i--) {
            table.deleteRow(i);
        }
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

    function pincars(carpos) {  // 创建图标对象
        var myIcon = new BMap.Icon("../images/car.png", new BMap.Size(50, 50), {
            offset: new BMap.Size(50, 50)
        });
        // 创建标注对象并添加到地图
        var marker = new BMap.Marker(WGS2BD(new BMap.Point(carpos.longtitude / 1000000, carpos.latitude / 1000000),new BMap.Point(0, 0)),
                {icon: myIcon});

        map.addOverlay(marker);

        addInfoWindow(marker, "车辆信息", "<html>\n" +
                "<body>\n" +
                "\n" +
                "<b>车牌号：</b>" + carpos.carnumber + "<br/>\n" +
                "<b>位置：</b>厦门思明区观日路44号<br/>\n" +
                "</body>\n" +
                "</html>");

        return marker;

    }

</script>

</body>
</html>


