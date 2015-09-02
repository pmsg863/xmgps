<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
    <meta property="wb:webmaster" content="72818d890429100a">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>首页</title>

    <link rel="stylesheet" type="text/css" href="jslib/index.css" media="all">

    <script type="text/javascript" src="jslib/jquery-1.js"></script>
    <script type="text/javascript" src="jslib/app.js"></script>
    <script type="text/javascript" src="jslib/jquery.js"></script>
    <script type="text/javascript" src="jslib/jquery-ui-1.js"></script>
    <script type="text/javascript" src="jslib/form.js"></script>
    <link rel="stylesheet" type="text/css" href="jslib/jquery-ui-1.css" media="all">
    <style>
        .slidebox-01{
            width: 100%;
            height: 660px;
            overflow: hidden;
            position: relative;
        }
        .slidepic-01{position:absolute;width:9999em;}/* 必要元素 */
        .slidepic-01 .photo{height:500px;overflow:hidden;float:left;  margin:0px;width: auto;}
        .slidebtn-01{
            position: absolute;
            bottom: 10px;
            right: 760px;
            float: right;
        }
        .slidebtn-01 li{background:#fff;border:1px solid #999999;cursor:pointer;float:left;height:10px;line-height:10px;width:10px;margin:4px;text-align:center;color:#333333; border-radius: 28.5px;}
        .slidebtn-01 li.current{background:#ccc;border:1px solid #ccc;height:10px;line-height:24px;width:10px;color:#fff;font-weight:800;}
        *{
            list-style-type: none;
            padding-bottom: 0;
            padding-left: 0px;
            padding-right: 0px;
            margin-bottom: 0;
            margin-left: 0;
            margin-right: 0px;
            margin-top: 0;
        }
        .img1{
            background-image: url("images/huadong_zh-CN.jpg");
            background-position: center center;
            background-repeat: no-repeat;
            background-size: cover;

        }
        .img2{
            background-image: url("images/huadong1_zh-CN.jpg");
            background-position: center center;
            background-repeat: no-repeat;
            background-size: cover;
        }
        .img3{
            background-image: url("images/huadong2_zh-CN.jpg");
            background-position: center center;
            background-repeat: no-repeat;
            background-size: cover;
        }
    </style>
    <script>
        $(document).ready(function(){
            $("button").click(function(){
                $("#contest_pic").fadeToggle("slow");
            });
        });
    </script></head>

<body>
<!-- 提示框  -->
<div id="messageBox" style="display:none">
    <p id="messageContent" style="margin-top:5px"></p>
</div>

<div class="home_header" id = "home_header" >
    <div class="home_header_2">
        <div class="home_logo"><img src="images/home_logo_zh-CN.png" onclick="location.href='./login.jsp';"></div>
        <div class="home_header_title">
            <div class="mainlist_input" style="float:left;">

                <input class="mainlist_input_item" id="email" placeholder="请输入您的邮箱" type="text"></div>

            <div class="mainlist_input" style="float:left;padding-left=10px;">
                <input class="mainlist_input_item" id="password" placeholder="请输入您的密码" type="password"></div>

            <div class="mainlist_zhuce_right_error" id="errormail" style="float:left;margin-top:-10px;"></div>
            <div class="mainlist_zhuce_right_error" id="errorpwd"  style="float:left;padding-left=10"></div>
            <div class="forget_pwd" style="margin-top: 10px;">
                <a style="text-decoration:none;" href="./forget.jsp">忘记密码 </a>
            </div>

            <div class="home_header_title_action" style="line-height: 20px;">
                <div style="" id="register" class="home_header_title_4 register_color" >注册</div>
                <div id="is_login">
                    <div style="margin-left:10px;" id="login" class="home_header_title_4 login_color">登录</div>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="slidebox-01" id="intr_mopaas">
    <div class="slidepic-01" style="margin-top: 160px; left: -3166px;">
        <div style="width: 1583px;" class="img1 photo">

        </div>
        <div style="width: 1583px;" class="img2 photo">
        </div>
        <div style="width: 1583px;" class="img3 photo">				                    </div>
    </div>
    <div class="slidebtn-01" >
        <ul>
            <li class=""></li>
            <li class=""></li>
            <li class="current"></li>
        </ul>
    </div>
</div>

<div class="mainlist_zhuce" id = "mainlist_zhuce" style="display:none">

    <div class="mainlist_zhuce_left" style="height:auto;width:525px;margin-bottom:70px;">
        <div class="mainlist_zhuce_right_1">注册</div>


        <div class="mainlist_input">
            <!-- <div class="mainlist_zhuce_right_2_1">邮箱地址：</div> -->
            <input style="line-height:35px;" class="mainlist_input_item_register" id="rgsemail" placeholder="请输入您的邮箱" type="text">
            <!-- <div class="color_red">*</div> -->
        </div>
        <div class="mainlist_zhuce_right_error" id="erroremail"></div>

        <div class="mainlist_input">
            <!-- <div class="mainlist_zhuce_right_2_1">密码：</div> -->
            <div><input style="line-height:35px;" class="mainlist_input_item_register" id="rgspassword" placeholder="请输入您的密码" type="password"></div>
            <!--    <div class="color_red">*</div> -->
        </div>
        <div class="mainlist_zhuce_right_error" id="errorpassword"></div>

        <div class="mainlist_input">
            <!-- <div class="mainlist_zhuce_right_2_1">确认密码：</div> -->
            <div><input style="line-height:35px;" class="mainlist_input_item_register" id="confirm_password" placeholder="请再次输入您的密码" type="password"></div>
            <!-- <div class="color_red">*</div> -->
        </div>
        <div class="mainlist_zhuce_right_error" id="confirmerror"></div>

        <div class="mainlist_input">
            <!-- <div  class="mainlist_zhuce_right_2_1">手机：</div> -->
            <div><input style="line-height:35px;" class="mainlist_input_item_register" id="mobile" placeholder="您的手机号码"></div>
        </div>
        <div class="mainlist_zhuce_right_error" id="mobileerror" style="width:450px;"></div>

        <div class="mainlist_zhuce_select_block">
            <select name="select" id="scale" class="mainlist_zhuce_select_1" style="width: 450px;">
                <option value="9">内部测试</option>
                <option value="1">厦门卫星定位应用有限公司</option>
                <option value="2">厦门大学</option>
            </select>
            <div class="mainlist_zhuce_right_error_select" id="erroescale"></div>
        </div>

        <div class="mainlist_input">
            <div><input style="line-height:35px;" class="mainlist_input_item_register" id="username" placeholder="用户名称" type="text"></div>
        </div>
        <div class="mainlist_zhuce_right_error" id="usernameerror"></div>

        <div class="mainlist_input">
            <div><input style="line-height:35px;" class="mainlist_input_item_register" id="useraddress" placeholder="地址" type="text"></div>
        </div>
        <div class="mainlist_zhuce_right_error" id="useraddresserror"></div>

        <div class="mainlist_register_btn">

            <div style="clear:both;"></div>
            <div style="float:left;">
                <div style="float:left">
                    <input id="identifying_code" class="mainlist_input_item_register" style="line-height: 35px;width:120px;" placeholder="请输入验证码" type="text">
                </div>
                <div style="float:right;margin: 8px;">
                    <img src="validateCodeServlet" alt="false" title="点击刷新" id="resrc" style="width:100px;height:35px;cursor:pointer;">
                </div>
            </div>
            <div style="clear:both;"></div>
            <div class="mainlist_zhuce_right_error" id="errorValidateCode"></div>
            <button class="mainlist_login_btn_item" id="registerinfo" style="background:url(images/login_btn_hover.png)no-repeat;clear:left;float:left">立刻注册</button>
            <div class="go_register_link" onclick="location.href ='./login.jsp'">立即登录</div>
            <div class="no_account">已经有账号</div>
        </div>
    </div>
    <div class="mainlist_zhuce_right" style="margin-left:25px;">
        <div class="mainlist_login_right_desc">
            <div class="mainlist_login_right_desc_block">
                <div class="mainlist_login_right_desc_title">开放平台</div>
                <div class="mainlist_login_right_desc_content">支持多种语言、框架、服务和IaaS技术；避免应用锁定，支持应用迁移。</div>
            </div>
            <div class="mainlist_login_right_desc_block">
                <div class="mainlist_login_right_desc_title">安全可靠</div>
                <div class="mainlist_login_right_desc_content">高可用的计算和存储资源；新一代备份、隔离和加密技术保证您的应用和数据安全。</div>
            </div>
            <div class="mainlist_login_right_desc_block">
                <div class="mainlist_login_right_desc_title">化繁为简</div>
                <div class="mainlist_login_right_desc_content">忽视服务器配置和底层构造，充分享受云给您带来的便利，使您专注于应用的开发和创新</div>
            </div>
            <div class="mainlist_login_right_desc_block">
                <div class="mainlist_login_right_desc_title">创新科技</div>
                <div class="mainlist_login_right_desc_content">基于新一代云平台和创新的信息链管理技术</div>
            </div>
        </div>
    </div>
</div>



<script type="text/javascript">
    //选中bar
    $(".bar_title").click(function(){
        $(".bar_title").removeClass("select_bar");
        $(this).addClass("select_bar");
    });
</script>
<script type="text/javascript">
    $(function(){
        var pw=document.body.scrollWidth;
        $('.slidepic-01 .photo').css("width",pw);
        $('.reg_core').attr("style","width:1024px;height:50px;margin:281px auto 0 auto");
        var ah='<a href="../register.jsp" target="_blank" style="width:200px;height:50px;display:block;float:left;margin-top:30px;margin-left:-27px"></a>'
                +'<a href="https://core.cloudfoundry.org/listings" target="_blank" style="width:50px;height:50px;display:block;margin-left:145px;float:left"></a>';
        $('.reg_core').html(ah);
        // 图片左右翻滚
        var size = $('.slidebtn-01>ul>li').length;
        var frist = 0;
        var datetime;
        $('.slidebtn-01 li').mouseover(function(){
            frist = $('.slidebtn-01 li').index(this);
            showpic(frist);
        }).eq(0).mouseover();

        $('.slidebox-01').hover(function(){
            clearInterval(datetime);
        },function(){
            datetime = setInterval(function(){
                showpic(frist)
                frist++;
                if(frist==size){
                    frist=0;
                }
            },10000);
        }).trigger('mouseleave');

        function showpic(frist){
            var imgheight = document.body.scrollWidth;//$('.banner_pic_center').width();

            $('.slidepic-01').stop(true,false).animate({left:-imgheight*frist},1000)
            $('.slidebtn-01 li').removeClass('current').eq(frist).addClass('current');
        };



    });

    $("#login").click(function(){
        var email =  $("#email").val();
        var password =  $("#password").val();

        var flag = checkuserbymail(email,"#errormail")
        if ( !flag ) {
            return false;
        }

        if (password != '') {
            if (password.length > 32 || password.length < 6) {
                $("#errorpwd").html('密码不为空且长度6～32位，字母区分大小写');
                return false;
            } else {
                $("#errorpwd").html('');
            }
        }

        $.ajax({
            type: "get",
            url: "./users/getUser.do?email="+email+"&password="+password,
            error: function(xhr){
                showMessageBox("页面有错误，请记录并联系开发！",1);
            },
            success: function(data){
                var gpsuser = eval("("+data+")");
                if (gpsuser.email==email) {
                    showMessageBox("登录成功，页面跳转中！",0);
                    window.location.href = "./userindex.jsp";
                }
                else
                    showMessageBox("登录失败，账号或密码错误！",1);
            }
        });
    })
    //显示服务
    $("#register").click(function(){
        document.getElementById("mainlist_zhuce").style.display='block';
        document.getElementById("mainlist_zhuce").style.marginTop ='150px';
        document.getElementById("intr_mopaas").style.display='none';
    })

    //验证邮箱格式
    function checkuserbymail(mail,mailmessage) {
        var emailRegExp =/\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
        var flag = false;
        if (emailRegExp.test(mail)) {
            flag = true;
            $(mailmessage).html('');
        } else {
            $(mailmessage).html('请填写正确的邮箱');
            return false;
        }
        return flag;
    }
    //去除空格
    String.prototype.trim = function() {
        return this.replace(/(^\s*)|(\s*$)/g, "");
    }

    $(document).ready(function(){

        $("#resrc").click(function(){
            changeValidateCode($(this));
        })
        //验证邮箱是否为空
        $("#rgsemail").blur(function() {
            var mail = this.value.trim();
            var flag = checkuserbymail(mail,"#erroremail");
            if (flag) {
                return false;
            }
        });

        //输入密码的判断
        $("#rgspassword").blur(function() {
            var password = $("#rgspassword").val();
            if (password != '') {
                if (password.length > 32 || password.length < 6) {
                    $("#errorpassword").html('密码不为空且长度6～32位，字母区分大小写');
                    return false;
                } else {
                    $("#errorpassword").html('');
                }
            }
        });

        //确认密码判断
        $("#confirm_password").blur(function(){
            var confirm_password = $("#confirm_password").val();
            var password = $("#rgspassword").val();
            if (confirm_password != password) {
                $("#confirmerror").html("两次输入的密码不一致");
                return false;
            }else {
                $("#confirmerror").html('');
            }
        });
        //判断手机
        $("#mobile").blur(function(){
            var mobile=$("#mobile").val();
            var mobileRegExp=/^\d{11}$/;
            if(!mobileRegExp.test(mobile)){
                $("#mobileerror").html("手机号码格式错误");
                return false;
            }else{
                $("#mobileerror").html('');
            }
        });


        $("#scale").change(function() {
            var scale = $("#scale").val();
            if (scale == '') {
                $("#erroescale").html('公司不能为空');
                return false;
            } else {
                $("#erroescale").html('');
            }
        });
        $("#username").change(function(){
            var username = $("#username").val();
            if (username == '') {
                $("#usernameerror").html('名称不能为空');
                return false;
            } else {
                $("#usernameerror").html('');
            }
        });

        $("#useraddress").change(function(){
            var useraddress = $("#useraddress").val();
            if (useraddress == '') {
                $("#useraddresserror").html('地址不能为空');
                return false;
            } else {
                $("#useraddresserror").html('');
            }
        });
        $("#identifying_code").blur(function(){
            var identifying_code = $(this).val();
            if(identifying_code == ""){
                $("#errorValidateCode").html('验证码不能为空');
                return false;
            }else{
                $("#errorValidateCode").html('');
            }
        });
        //将注册信息提交给服务器
        $("#registerinfo").live('click',function(){
            register();
        });

        bindEnterEvent(register,"email","password","confirm_password","mobile","scale","username","useraddress","identifying_code");
    });

    function register(){
        var status = true;

        var email = $("#rgsemail").val();
        if (email == '') {
            $("#erroremail").html('请填写正确的邮箱');
            status = false;
        } else {
            var emailRegExp =/\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
            if (emailRegExp.test(email)) {
                $("#erroremail").html('');
            } else {
                $("#erroremail").html('请填写正确的邮箱');
                status = false;
            }

        }


        var password = $("#rgspassword").val().trim();
        if (password == '') {
            $("#errorpassword").html('密码不能为空');
            status = false;
        } else {
            $("#errorpassword").html('');
        }
        var confirm_password = $("#confirm_password").val().trim();
        if (confirm_password == '') {
            $("#confirmerror").html('确认密码不能为空');
            status = false;
        } else {
            $("#confirmerror").html('');
        }

        if (confirm_password != password) {
            $("#confirmerror").html("两次输入的密码不一致");
            status = false;
        }else {
            $("#confirmerror").html('');
        }

        var mobile = $("#mobile").val();
        if(mobile == ''){
            $("#mobileerror").html("手机号不能为空");
            status = false;
        }else{
            var mobileRegExp=/^\d{11}$/;
            if(!mobileRegExp.test(mobile)){
                $("#mobileerror").html("手机号码格式错误");
                status = false;
            }else{
                $("#mobileerror").html('');
            }
        }

        var scale = $("#scale").val();
        if (scale == '') {
            $("#erroescale").html('公司不能为空');
            status = false;
        } else {
            $("#erroescale").html('');
        }


            var username = $("#username").val();
            if (username == '') {
                $("#usernameerror").html('名称不能为空');
                return false;
            } else {
                $("#usernameerror").html('');
            }

            var useraddress = $("#useraddress").val();
            if (useraddress == '') {
                $("#useraddresserror").html('地址不能为空');
                return false;
            } else {
                $("#useraddresserror").html('');
            }

        var identifying_code = $("#identifying_code").val();
        if(identifying_code == ""){
            $("#errorValidateCode").html('验证码不能为空');
            status = false;
        }else{
            $("#errorValidateCode").html('');
        }

        if(status == false){
            return false;
        }

        if (email && password && mobile && scale && username && useraddress){
            var jsondata = {email:email,password:password,mobile:mobile,username:username,useraddress:useraddress, usergroup:scale};
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                dataType:"json",
                url: "./users/save.do",
                data:  JSON.stringify(jsondata) ,
                beforeSend: function(xhr){
                    $("#registerinfo").html("注册中...");
                },
                success: function(data){
                    $("#registerinfo").html("注册成功");
                    if (data.returnCode == "000") {
/*                        if(cfrole == 0){
                            showMessageBox("注册成功，请去邮箱激活",2);
                            window.location.href = "/regSuccess.jsp";
                        }else{*/
                            showMessageBox("注册成功",0);
                            /*_userlogin(email,password);*/
                            window.location.href = "./login.jsp";
/*                        }*/

                    } else {
                        showMessageBox(data.returnMessage,1);
/*                        changeValidateCode($("#resrc"));*/
                        return false;
                    }
                }
            });
        }

    }
</script>

</body></html>
