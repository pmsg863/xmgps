<%--
  Created by IntelliJ IDEA.
  User: pmsg863
  Date: 14-5-26
  Time: 下午9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>车辆管理</title>

    <script type="text/javascript" src="../jslib/app.js"></script>
    <link rel="stylesheet" type="text/css" href="../jslib/admin.css" media="all">
    <link rel="stylesheet" type="text/css" href="../jslib/master.css" media="all">
    <script type="text/javascript" src="../jslib/jquery-1.js"></script>
    <script type="text/javascript" src="../jslib/jquery.js"></script>
    <script type="text/javascript" src="../jslib/jquery-ui-1.js"></script>

</head>
<body>

<!-- 提示框  -->
<div id="messageBox" style="display:none">
    <p id="messageContent" style="margin-top:5px"></p>
</div>

<div style="margin: 50px; padding-top: 5px;">

    <div class="mainlist_zhuce_left" style="height:auto;width:525px;margin-bottom:70px;">
        <div class="mainlist_zhuce_right_1">新增车辆</div>


        <div class="mainlist_input">
            <input style="line-height:35px;" class="mainlist_input_item_register" id="carno" placeholder="请输入您的车牌号码"
                   type="text">
        </div>

        <div class="mainlist_zhuce_select_block">
            <select name="select" id="cartype" class="mainlist_zhuce_select_1" style="width: 450px;">
                <option value="其他">其他</option>
                <option value="蓝">蓝</option>
                <option value="黄">黄</option>
                <option value="黑">黑</option>
            </select>
        </div>

        <button class="mainlist_login_btn_item" id="registerinfo"
                style="background:url(../images/login_btn_hover.png)no-repeat;clear:left;float:left">立刻注册
        </button>
    </div>

    <div id="instances_list" class="mainlist_zhuce_right" style="margin-left:25px;padding-top: 10px;">

        <table id="appContent" cellpadding="0" cellspacing="0" border="0">
            <tbody>
            <tr id="instances_th" class="managemen_mon_title">
                <th class="managemen_mon_title_box1">车辆编码</th>
                <th class="managemen_mon_title_box2">车牌号码</th>
                <th class="managemen_mon_title_box3">颜色</th>
                <th class="managemen_mon_title_box4">操作</th>
            </tr>
            </tbody>
        </table>
    </div>

</div>

<script type="text/javascript">

    $(document).ready(function(){
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
                    addcars(vehicle);
                });

            }
        });;
    });

    $("#registerinfo").click(function () {
        var vehicle = new Object();
        vehicle.carno = $("#carno").val();
        vehicle.cartype = $("#cartype").val();

        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType:"json",
            url: "../cars/save.do",
            data:  JSON.stringify(vehicle) ,
            error: function(xhr){
                showMessageBox("数据添加失败，请记录并联系开发！",1);
            },
            success: function(data){
                if (data.returnCode == "000") {
                    showMessageBox("数据添加成功",0);
                    vehicle.carid =  data.returnObject;
                    addcars(vehicle);
                } else {
                    showMessageBox(data.returnMessage,1);
                    return false;
                }
            }
        });

    });

    function removetd(obj) {
        var row = obj.parentNode.parentNode; //标签所在行
        var tb = row.parentNode;     //当前表格
        var rowIndex = row.rowIndex;  //A标签所在行下标

        var vehicle = new Object();
        vehicle.carid = row.childNodes[0].innerHTML;
        vehicle.carno = row.childNodes[1].innerHTML;
        vehicle.cartype = row.childNodes[2].innerHTML;

        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType:"json",
            url: "../cars/remove.do",
            data:  JSON.stringify(vehicle) ,
            error: function(xhr){
                showMessageBox("数据删除失败，请记录并联系开发！",1);
            },
            success: function(data){
                if (data.returnCode == "000") {
                    showMessageBox("数据删除成功",0);
                    tb.deleteRow(rowIndex); //删除当前行
                } else {
                    showMessageBox(data.returnMessage,1);
                    return false;
                }
            }
        });

    }

    function addcars(vehicle) {
        //最后一列
        var tr = document.getElementById("appContent").insertRow();
        tr.className = "managemen_mon_info";

        var td = tr.insertCell();
        td.innerHTML = vehicle.carid;
        td.className = "managemen_mon_info_box1";
        td = tr.insertCell();
        td.innerHTML = vehicle.carno;
        td.className = "managemen_mon_info_box2";
        td = tr.insertCell();
        td.innerHTML = vehicle.cartype;
        td.className = "managemen_mon_info_box3";

        td = tr.insertCell();
        td.className = "managemen_mon_info_box3";
        td.innerHTML = "<span class='managemen_mon_info_sp2' style='cursor:pointer;'"+
                        "onclick='revise(this)'>修改</span><span class='managemen_mon_info_sp2' "+
                        "style='cursor:pointer;margin-left:10px;'"+
                        "onclick='removetd(this)'>删除</span>";
    }

</script>


</body>
</html>
