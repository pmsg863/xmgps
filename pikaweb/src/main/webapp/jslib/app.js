function checkCreateAppForm() {
	var flag = true;
	var errorInfo=$("#errorInfo");
	var framework = $("#framework").val();
	var appName = $("#appName").val();
	var url = $("#url").val();
	var memory = $("#memory").val();
    if(appName.replace(/^\s*/,"").replace(/\s*$/,"")===""){
    	errorInfo.html("请输入应用名字");
    	return  false;
    }
	var reg = /^[A-Za-z0-9]*$/;
	if(reg.exec(appName)==null){
		errorInfo.html("应用名字只能包含数字或字符");
		return false;
	}
    if(url.replace(/^\s*/,"").replace(/\s*$/,"")===""){
    	errorInfo.html("请输入URL地址");
    	return  false;
    }
	if(reg.exec(url)==null){
		errorInfo.html("URL只能包含数字或字符");
		return false;
	}
	if(framework.replace(/^\s*/,"").replace(/\s*$/,"")===""){
		errorInfo.html("请选择应用类型");
		return false;
	}
	if(memory.replace(/^\s*/,"").replace(/\s*$/,"")===""){
		errorInfo.html("请选择内存");
		return false;
	}
	$("#save").attr("disabled","disabled");
	return true;
}
function updateUrl() {
	$("#url").val($("#appName").val());

}
/** 动态选择内存* */
function selectMem() {
	var framework = $("#framework").val();
	if (framework != "") {
		jQuery
				.ajax({
					async : false,
					type : "POST",
					url : "/selectMem.html",
					data : {
						"mem" : framework
					},
					dataType : "json",
					error : function(data) {
						flag = false;
					},
					success : function(data) {
						var obj = eval(data);
						if (obj.returnCode == '000') {
							flag = true;
							document.getElementById("memory").value = obj.returnMessage;
						} else {
							flag = false;
						}
					}
				});
	}
	checkFramework();
}

function checkUpdateAppForm() {
	var errorInfo=$("#errorInfo");
	var instances = $("#instances").val();
	var re = /^[0-9]*$/;
	if((instances.replace(/^\s*/,"").replace(/\s*$/,"")==="")||(re.exec(instances)==null)){
		errorInfo.html("实例数只能是数字");
		return false;
	}
	if(instances==='0'){
		errorInfo.html("实例数必须大于0");
		return false;
	}
	var urls=document.getElementsByName("url");
	for(var i=0;i<urls.length;i++){
		if(urls[i].value.replace(/^\s*/,"").replace(/\s*$/,"")===""){
			errorInfo.html("URL地址不能为空");
			return false;
		}
		var reg = /^[A-Za-z0-9]*$/;
		if(reg.exec(urls[i].value)==null){
			errorInfo.html("URL只能包含数字或字符");
			return false;
		}
		
	}
	var choose_options = document.getElementById("choose_sel");
	for ( var i = 0; i < choose_options.length; i++) {
		choose_options[i].selected = "selected";
	}
	$("#register").attr("disabled","disabled");
	return true;
}

function rightMove() {

	var brand_sel = document.getElementById("brand_sel");
	var choose_sel = document.getElementById("choose_sel");

	var brand_options = brand_sel.options;
	var s = choose_sel.options.length;
	for ( var i = 0; i < brand_options.length; i++) {
		var is_selected = brand_options[i].selected;
		if (is_selected) {
			var option = new Option(brand_options[i].text,
					brand_options[i].value);
			if (!contains(choose_sel, option)) {
				choose_sel.options[s++] = new Option(brand_options[i].text,
						brand_options[i].value);
			}
		}
	}
	rightReMove();
}
function rightReMove() {
	$("#brand_sel>option").each(function() {
		var option = $(this);
		if (option.attr("selected")) {
			option.remove();
		}
	});
}
function leftReMove() {
	$("#choose_sel>option").each(function() {
		var option = $(this);
		if (option.attr("selected")) {
			option.remove();
		}
	});
}

function leftMove() {
	var brand_sel = document.getElementById("brand_sel");
	var choose_sel = document.getElementById("choose_sel");

	var choose_options = choose_sel.options;
	var s = brand_sel.options.length;
	for ( var i = 0; i < choose_options.length; i++) {
		var is_selected = choose_options[i].selected;
		if (is_selected) {
			var option = new Option(choose_options[i].text,
					choose_options[i].value);
			if (!contains(brand_sel, option)) {
				brand_sel.options[s++] = new Option(choose_options[i].text,
						choose_options[i].value);
			}
		}
	}
	leftReMove();
}

function clean() {

	$("#choose_sel>option").each(function() {
		$(this).remove();
	});

}

function selectChoose() {
	$("#choose_sel>option").attr("selected", "true");
	return true;
}

function contains(obj_sel, option) {
	var options = obj_sel.options;
	for ( var i = 0; i < options.length; i++) {
		if (options[i].value == option.value) {
			return true;
		}
	}

	return false;
}
function checkUploadFileForm(){
	if($("#uploadstate").attr("submited") == undefined||$("#uploadstate").attr("submited")== "false"){
		var errorInfo=document.getElementById("errorInfo");
		var flag=true;
		flag=chkfile();
		if(!flag){
			errorInfo.innerHTML="请选择war,zip类型的文件";
			return flag;
		}
		$("#uploadstate").css('display', '').html("文件正在上传,请稍候...");
		$("#uploadstate").attr("submited","true");
		return flag;
	}else{
		$("#uploadstate").css("color","red");
		$("#uploadstate").html("文件正在上传,请不要重复提交...");
		return false;
	}
}
var file_type=new Array(".zip",".war");
//檢查文件上傳圖片的類型
function chkfile()
{
	var flag=true;
   var filevalue=$("#fileName").val();
    if(filevalue.length>0)
 {
    	flag=checkFileType(filevalue);
  return flag;
 }else{
	 
	 return false;
 }

}
/**
判断上传文件格式是否正确
*/
function checkFileType(fileURL)
{
//本程序用来验证后缀，如果还有其它格式，可以添加在right_type;
var right_typeLen=file_type.length;
var imgUrl=fileURL.toLowerCase();
var postfixLen=imgUrl.length;
var len4=imgUrl.substring(postfixLen-4,postfixLen);
var len5=imgUrl.substring(postfixLen-5,postfixLen);
for (i=0;i<right_typeLen;i++)
{
if((len4==file_type[i])||(len5==file_type[i]))
{
return true;
}
}
return false;
}
function checkSelect() {
	var appchecked=0;
	var flag = true;
	var appvalue="";
	var appNames = document.getElementsByName("appName");
	for ( var i = 0; i < appNames.length; i++) {
		if (appNames[i].checked) {
			appvalue=appNames[i].value;
			appchecked++;
		}
	}
	if (appchecked<=0){
		openDialog("请选择应用!");
		flag=false;
	}
	else if(appchecked>1){
		openDialog("只能选择一个应用!");
		flag=false;
	}else{
		document.getElementById("app").value=appvalue;
	}
	return flag;
}
function checkAppSelect() {
	var flag = false;
	var appNames = document.getElementsByName("appName");
	for ( var i = 0; i < appNames.length; i++) {
		if (appNames[i].checked) {
			flag = true;
		}
	}
	if (!flag)
		openDialog("请选择应用!");
	return flag;
}
function startApp() {
	var appNames = document.getElementsByName("appName");
	var url = '/mopaas/app/startApp.html?';
	for ( var i = 0; i < appNames.length; i++) {
		if (appNames[i].checked) {
			url += "appName=" + appNames[i].value + "&";
		}
	}
	document.location = url;
}
function stopApp() {
	var appNames = document.getElementsByName("appName");
	var url = '/mopaas/app/stopApp.html?';
	for ( var i = 0; i < appNames.length; i++) {
		if (appNames[i].checked) {
			url += "appName=" + appNames[i].value + "&";
		}
	}
	document.location = url;
}
function restartApp() {
	var appNames = document.getElementsByName("appName");
	var url = '/mopaas/app/restartApp.html?';
	for ( var i = 0; i < appNames.length; i++) {
		if (appNames[i].checked) {
			url += "appName=" + appNames[i].value + "&";
		}
	}
	document.location = url;
}
function deleteApp() {
	var appNames = document.getElementsByName("appName");
	var url = '/mopaas/app/deleteApp.html?';
	for ( var i = 0; i < appNames.length; i++) {
		if (appNames[i].checked) {
			url += "appName=" + appNames[i].value + "&";
		}
	}
	$("#del_ok").attr("disabled","disabled");
	document.location = url;
}

function adminStartApp() {
	var appNames = document.getElementsByName("appName");
	var url = '/admin/statistics/adminStartApp.html?';
	for ( var i = 0; i < appNames.length; i++) {
		if (appNames[i].checked) {
			url += "id=" + appNames[i].value + "&";
		}
	}
	document.location = url;
}
function adminStopApp() {
	var appNames = document.getElementsByName("appName");
	var url = '/admin/statistics/adminStopApp.html?';
	for ( var i = 0; i < appNames.length; i++) {
		if (appNames[i].checked) {
			url += "id=" + appNames[i].value + "&";
		}
	}
	document.location = url;
}
function adminRestartApp() {
	var appNames = document.getElementsByName("appName");
	var url = '/admin/statistics/adminRestartApp.html?';
	for ( var i = 0; i < appNames.length; i++) {
		if (appNames[i].checked) {
			url += "id=" + appNames[i].value + "&";
		}
	}
	document.location = url;
}
function adminDeleteApp() {
	var appNames = document.getElementsByName("appName");
	var url = '/admin/statistics/adminDeleteApp.html?';
	for ( var i = 0; i < appNames.length; i++) {
		if (appNames[i].checked) {
			url += "id=" + appNames[i].value + "&";
		}
	}
	document.location = url;
}


function checkAll(name)
{
	var flag=true;
	var checkAll = document.getElementById("checkAll");
	if(!checkAll.checked){
		flag=false;
	}
    var el = document.getElementsByTagName('input');
    var len = el.length;
    for(var i=0; i<len; i++)
    {
        if((el[i].type=="checkbox") && (el[i].id==name))
        {
        	if(flag){
        		el[i].checked = true;
        	}else{
        		el[i].checked = false;
        	}
        }
    }
}
function showButton() {
	var appNames = document.getElementsByName("appName");
	var run = 0;
	var appchecked=0;
	for ( var i = 0; i < appNames.length; i++) {
		if (appNames[i].checked) {
			appchecked++;
			var state = document.getElementById(i).value;

			if (state == "STARTED") {
				run++;
			}
		}
	}
	if(appchecked==1){
		document.getElementById("uploadbutton").style.display = '';
	}else{
		document.getElementById("uploadbutton").style.display = 'none';
	}
	if (run != 0) {
		document.getElementById("start").style.display = 'none';
	} else {
		document.getElementById("start").style.display = '';
	}

}
function updateApp(appname,type) {
	var url = '/mopaas/app/detailsApp.html?';
	url += "appName=" + appname+"&action=update&type="+type;
	document.location = url;
}
function detailsApp(appname, state) {
	var url = '/mopaas/app/infoApp.html?';
	url += "appName=" + appname;
	document.location = url;
}
function checkUrlSelect() {
	var errorInfo=document.getElementById("errorInfo");
	var flag = false;
	var urls = document.getElementsByName("isChecked");
	for ( var i = 0; i < urls.length; i++) {
		if (urls[i].checked) {
			flag = true;
		}
	}
	if (!flag)
		openDialog("请选择URL!");
	return flag;
}
//删除当前行
function delMyRow() {
	var val = new Array();
	var myrow;
	var mytable = document.getElementById("mytable");
	var urls = document.getElementsByName("isChecked");
	for ( var i = 0; i < urls.length; i++) {
		if (urls[i].checked) {
			val[i] = urls[i].id;
		}
	}

	for ( var i = 0; i < val.length; i++) {
		if (val[i] != undefined) {
			myrow = document.getElementById("tr" + val[i]);
			mytable.deleteRow(myrow.rowIndex);
		}
	}

}
// 删除所有行
function delAllMyRow() {
	var mytable = document.getElementById("mytable");
	var rowlen = mytable.rows.length;
	for ( var i = rowlen - 1; i >= 0; i--) {
		mytable.deleteRow(i);
	}
}

var pageIndex=0;
var pageSize=0;
function listApps(select_id) {
//	filePath = document.getElementById("file_path").value;
//	listtype="0";
	jQuery
			.ajax({
				async : true,
				type : "POST",
				url : "/mopaas/app/listApps.html",
				data : {
					"index" : select_id
				},
				dataType : "json",
				error : function(data) {

				},
				success : function(data) {
					
					var thisapp=parseInt(data.thisapp);
					var appCount=parseInt(data.appCount);
					var thisser=parseInt(data.thisser);
					var serviceCount=parseInt(data.serviceCount);
					var thismem=parseInt(data.thismem);
					var memCount=parseInt(data.memCount);
					$("#appRed").remove();
					$("#appGree").remove();
					$("#serRed").remove();
					$("#serGree").remove();
					$("#memRed").remove();
					$("#memGree").remove();
					$("#appBarSpan").html('');
					$("#serviceBarSpan").html('');
					$("#memBarSpan").html('');
					if((thisapp/appCount)*100>=80){
						$("#appBar").append("<div class='progress_bar_red' id='appRed' style=\"width: "+(thisapp/appCount)*100+"%\"></div>");
					}else{
						$("#appBar").append("<div class='progress_degree' id='appGree' style=\"width: "+(thisapp/appCount)*100+"%\"></div>");
					}
					$("#appBarSpan").html(thisapp+"/"+appCount);
					
					if((thisser/serviceCount)*100>=80){
						$("#serviceBar").append("<div class='progress_bar_red' id='serRed' style=\"width: "+(thisser/serviceCount)*100+"%\"></div>");
					}else{
						$("#serviceBar").append("<div class='progress_degree' id='serGree' style=\"width: "+(thisser/serviceCount)*100+"%\"></div>");						
					}
					$("#serviceBarSpan").append(thisser+"/"+serviceCount);
					
					if((thismem/memCount)*100>=80){
						$("#memBar").append("<div class='progress_bar_red' id='memRed' style=\"width: "+(thismem/memCount)*100+"%\"></div>");
					}else{
						$("#memBar").append("<div class='progress_degree' id='memGree' style=\"width: "+(thismem/memCount)*100+"%\"></div>");						
					}
					$("#memBarSpan").append(thismem+"/"+memCount);
					
//					$("#create-user").hide();
//					$("#showTable").find("thead").remove();
//					
					var appNames = document.getElementsByName("appName");
					var checkbox= new Array();
					for ( var i = 0; i < appNames.length; i++) {
						if (appNames[i].checked) {
							checkbox[i]=appNames[i].value;
						}
					}
					var appAllCheck=document.getElementById("checkAll");
					var isAllCheck=false;
					if(appAllCheck!=null){
						isAllCheck=appAllCheck.checked;
					}
					$("#showTable").find("tbody").remove();
					var obj=eval(data);
					var fileList = data.list;
					
					pageIndex = parseInt(data.thisPage);
					
//					nextIndex = pageIndex + 1;
//					
//					befIndex = pageIndex - 1;
					
					pageSize = data.pageSize;
//					
//					dispalyFile();
//					
//					
					
					if(isAllCheck){
						$("#showTable").append("<tr><th><input type='checkbox' checked='checked' name='checkAll' id='checkAll'onclick=\"checkAll('checkbox');showButton();\"></th><th>应用名称</th><th>实例数</th><th>类型</th><th>应用状态</th><th>地址</th><th>时间</th><th>操作</th></tr>");
					}else{
						$("#showTable").append("<tr><th><input type='checkbox' id='checkAll'  onclick=\"checkAll('checkbox');showButton();\"></th><th>应用名称</th><th>实例数</th><th>类型</th><th>应用状态</th><th>地址</th><th>时间</th><th>操作</th></tr>");	
					}
					
					$("#showTable").append("<tbody>");
					var tableHTML = "";
					for ( var i = 0, l = fileList.length; i < l; i++) {
						var count=0;
						var app=fileList[i];
						tableHTML += "<tr>";
						for(var c=0;c<checkbox.length;c++){
							if(checkbox[c]==app.appName){
								count=count+1;
								tableHTML+="<td align='center'><input type='checkbox' checked='checked' name='appName'id='checkbox' value='"+app.appName+"' onclick=\"showButton();\"></td>";
							}
						}
						if(count==0){
							tableHTML+="<td align='center'><input type='checkbox' name='appName'id='checkbox' value='"+app.appName+"' onclick=\"showButton();\"></td>";
						}
						tableHTML+="<td style='text-align: left;' width='180px'><div style='text-align: left;padding-left: 30px;'>";

						if((app.packageHash==null)&&(app.uploadState=="0")){
							tableHTML+="<img style='position: relative;top: 3px;' src='../../images/spinner.gif'/> "+app.appName;
						}else if((app.packageHash==null)&&(app.uploadState=="1")){
							tableHTML+="<img style='position: relative;top: 3px;' src='../../images/new.png'/> "+app.appName;
						}else if((app.packageHash==null)&&(app.uploadState=="2")){
							tableHTML+="<img style='position: relative;top: 3px;' src='../../images/failure.png'/> "+app.appName;
						}else if((app.packageHash==null)&&(app.uploadState==null)){
							tableHTML+="<img style='position: relative;top: 3px;' src='../../images/new.png'/> "+app.appName;
						}else if((app.packageHash!=null)&&(app.uploadState==null)){
							tableHTML+="<img style='position: relative;top: 3px;' src='../../images/success.png'/> "+app.appName;
						}else if((app.packageHash!=null)&&(app.uploadState=="0")){
							tableHTML+="<img style='position: relative;top: 3px;' src='../../images/spinner.gif'/> "+app.appName;
						}else if((app.packageHash!=null)&&(app.uploadState=="1")){
							tableHTML+="<img style='position: relative;top: 3px;' src='../../images/success.png'/> "+app.appName;
						}else if((app.packageHash!=null)&&(app.uploadState=="2")){
							tableHTML+="<img style='position: relative;top: 3px;' src='../../images/failure.png'/> "+app.appName;
						}else{
							tableHTML+="" +
									app.appName;
						}
						
						tableHTML+="</div></td>"
						tableHTML+="<td align='center'>"+app.instances+"</td>";
						tableHTML+="<td align='center'>"+app.framework+"</td>";
						tableHTML+="<td align='center'>";
						if(app.state=='STARTED'){
							tableHTML+="启动";
						}
						if(app.state=='STOPPED'){
							tableHTML+="停止";
						}
						tableHTML+="<input type='hidden' id='"+i+"' value='"+app.state+"' /></td>";
						tableHTML+="<td align='center' width='180px'><div style='text-align: left;padding-left: 30px;'>";
						var strs= new Array();
						if(app.uris.length>0){
							strs=app.uris.split(",");
							for(v=0;v<strs.length ;v++)
								tableHTML+="<a href='#' onclick=\"javascript:window.open('http://"+strs[v]+"');\">"+strs[v]+"</a><br />";
						}else{
							tableHTML+="<a href='#' onclick=\"javascript:window.open('http://"+app.uris+"');\">"+app.uris+"</a><br />";
						}
						tableHTML+="</div></td>";
							
							tableHTML+="<td align='center'>"+app.createTime+"</td>";
							tableHTML+="<td align='center'><a class='edit_btn' name='"+app.appName+"'id='"+app.appName+"'onclick=\"updateApp('"+app.appName+"');\" style='cursor: pointer;' title='编辑'><img src='../../images/app/edit.png'></img></a>&nbsp;&nbsp;" +
									" <a href='#' onclick=\"detailsApp('"+app.appName+"','"+app.state+"');\"><img src='../../images/app/detail.png' title='详情'></img></a></td>";
							tableHTML += "</tr>";
							//alert(tableHTML);
					}
					$("#showTable").append(tableHTML);
					$("#showTable").append("</tbody>");
				}

				 
			});

}

//将用户信息的cookie值保存进去
function SetCookie(name,value) {
    var Days = 1;   //cookie 将被保存1天
    var exp  = new Date();  //获得当前时间
    exp.setTime(exp.getTime() + Days*24*60*60*1000);  //换成毫秒
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}


//获取客户端写入的cookie
function getCookie(name) {	//name为cookie名称
	var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
    if(arr != null) {
    	return decodeURIComponent(arr[2]);
    } else {
    	return null;
    }
}

//获取链接后面的参数
function getRequestParam(paras)
{
    var url = location.href;
    var paraString = url.substring(url.indexOf("?")+1,url.length).split("&");
    var paraObj = {}
    for (i=0; j=paraString[i]; i++){
    paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length);
    }
    var returnValue = paraObj[paras.toLowerCase()];
    if(typeof(returnValue)=="undefined"){
    return "";
    }else{
    return returnValue;
    }
}

/*
 * 显示提示框
 * message 要显示的信息的内容
 * type 0适用于正确信息如创建成功等，显示蓝色的背景;1适用于错误信息如失败信息等;2适用于注册成功发送邮件
 */
function showMessageBox(message,type)
{
	if(type == 0)
	{
		$('#messageBox').css("background","none repeat scroll 0 0 #33d366");
		// filter:alpha(Opacity=80);-moz-opacity:0.5;opacity: 0.5;
		$('#messageBox').css("filter","alpha(Opacity=80)");
		$('#messageBox').css("-moz-opacity","0.9");
		$('#messageBox').css("opacity","0.9");
		$("#messageContent").css("color","white");
		$("#messageContent" ).html(message);
		$( '#messageBox').fadeIn(1000).delay(2000).fadeOut(1000);
	}	
	else if(type == 1)
	{
		$('#messageBox').css("background","none repeat scroll 0 0 #DC143C");
		$("#messageContent").css("color","white");
		$("#messageContent" ).html(message);
		$( '#messageBox').fadeIn(1000).delay(2000).fadeOut(1000);
		//$( '#messageBox').css('display','block');
	}
	else if(type == 2)
	{
		$('#messageBox').css("background","none repeat scroll 0 0 #F0FFF0");
		$("#messageContent").css("color","black");
		$("#messageContent" ).html(message);
		$( '#messageBox').fadeIn(1000).delay(2000).fadeOut(1000);
		//$( '#messageBox').css('display','block');
	}
	
}

//转换套餐中的数值显示
function computeValue(value){
	if(value >= 1024)
		value = value/1024 + "G";
	else
		value = value + "MB";
	return value;
}

//是否能整除
function ISDivisible(value)
{
	/*if(value % 1024 == 0)
		value = value/1024 + "G";
	else
		value = value + "MB";
	return value;*/
	
	var index;
	if(value < 1)
	{
		value = value * 1024;
		index = value.toString().indexOf(".");
		if(index > -1)
		{
			//存在小数点，取两位小数
			value = Number(value).toFixed(2);
		}
		value += "KB";
	}
	else if(1 <= value && value < 1024)
	{
		index = value.toString().indexOf(".");
		if(index > -1)
		{
			//存在小数点，取两位小数
			value = Number(value).toFixed(2);
		}
		value += "MB";
	}
	else
	{
		value = value/1024;
		index = value.toString().indexOf(".");
		if(index > -1)
		{
			//存在小数点，取两位小数
			value = Number(value).toFixed(2);
		}
		value += "G";
	}
	return value;
}

//获取系统当前的时间
function getNowTime()
{
	var myDate = new Date();
	var nowTime = myDate.getFullYear()+'-'+(myDate.getMonth()+1)+'-'+myDate.getDate()+' '
	+myDate.getHours()+":"+myDate.getMinutes()+":"+myDate.getSeconds();
	return nowTime;
}

//比较时间的大小,传入参数格式为"2013-9-10 10:00:00"型的
//如果endStr大于startStr则返回true
function dateCompare(startStr,endStr)
{
	 var d1, d2, s, arr, arr1, arr2;
	 if(startStr.length > 10)
	 {
	    arr = startStr.split(" ");
	    arr1 = arr[0].split("-");
	    arr2 = arr[1].split(":");
	    d1 = new Date(arr1[0], arr1[1] - 1, arr1[2], arr2[0], arr2[1], arr2[2]);
	 }
	 else
	 {
	    arr = startStr.split("-");
	    d1 = new Date(arr[0], arr[1], arr[2]);
	 }
	 if(endStr.length > 10)
	 {
	    arr = endStr.split(" ");
	    arr1 = arr[0].split("-");
	    arr2 = arr[1].split(":");
	    d2 = new Date(arr1[0], arr1[1] - 1, arr1[2], arr2[0], arr2[1], arr2[2]);
	 }
	 else
	 {
	    arr = endStr.split("-");
	    d2 = new Date(arr[0], arr[1], arr[2]);
	 }
	 
	 s = d2 - d1 ;
	 if(s < 0)
	 {
	    return false;
	 }
	 return true;
}

//获取上个月的这一天
function getLastMonthYestdy(date) {  
    var daysInMonth = new Array([0], [31], [28], [31], [30], [31], [30], [31], [31], [30], [31], [30], [31]);  
    var strYear = date.getFullYear();  
    var strDay = date.getDate();  
    var strMonth = date.getMonth() + 1;  
    if (strYear % 4 == 0 && strYear % 100 != 0) {  
        daysInMonth[2] = 29;  
    }  
    if (strMonth - 1 == 0) {  
        strYear -= 1;  
        strMonth = 12;  
    }  
    else {  
        strMonth -= 1;  
    }  
    strDay = daysInMonth[strMonth] >= strDay ? strDay : daysInMonth[strMonth];  
    if (strMonth < 10) {  
        strMonth = "0" + strMonth;  
    }  
    if (strDay < 10) {  
        strDay = "0" + strDay;  
    }  
    return strYear + "-" + strMonth + "-" + strDay;  
}  


//截取字符串(包括中文）
function getCutString(str,len){
	var strlen = 0; 
	var s = "";
	for(j = 0;j < str.length;j++){
  		if(str.charCodeAt(j) > 128){
			strlen += 2;
		}else{ 
			strlen++;
		}
		s += str.charAt(j);
		if(strlen >= len){ 
			return s+"..." ;
		}
	}
	return s;
}


//模拟placeholder
(function(){
//判断是否支持placeholder
function isPlaceholer(){
var input = document.createElement('input');
return "placeholder" in input;
}
//不支持的代码
if(!isPlaceholer()){
//创建一个类
function Placeholder(obj){
this.input = obj;
this.label = document.createElement('label');
this.label.innerHTML = obj.getAttribute('placeholder');
this.label.style.cssText = 'position:absolute;margin-top:10px; text-indent:4px;color:#999999; font-size:12px;';
if(obj.value != ''){
this.label.style.display = 'none';
}
this.init();
}
Placeholder.prototype = {
//取位置
getxy : function(obj){
var left, top;
if(document.documentElement.getBoundingClientRect){
var html = document.documentElement,
body = document.body,
pos = obj.getBoundingClientRect(),
st = html.scrollTop || body.scrollTop,
sl = html.scrollLeft || body.scrollLeft,
ct = html.clientTop || body.clientTop,
cl = html.clientLeft || body.clientLeft;
left = pos.left + sl - cl;
top = pos.top + st - ct;
}
else{
while(obj){
left += obj.offsetLeft;
top += obj.offsetTop;
obj = obj.offsetParent;
}
}
return{
left: left+10,
top : top
}
},
//取宽高
getwh : function(obj){
return {
w : obj.offsetWidth,
h : obj.offsetHeight
}
},
//添加宽高值方法
setStyles : function(obj,styles){
for(var p in styles){
obj.style[p] = styles[p]+'px';
}
},
init : function(){
var label = this.label,
input = this.input,
xy = this.getxy(input),
wh = this.getwh(input);
this.setStyles(label, {'width':wh.w, 'height':wh.h, 'lineHeight':20, 'left':xy.left, 'top':xy.top});
document.body.appendChild(label);
label.onclick = function(){
this.style.display = "none";
input.focus();
}
input.onfocus = function(){
label.style.display = "none";
};
input.onblur = function(){
if(this.value == ""){
label.style.display = "block";
}
};
}
}
var inpColl = document.getElementsByTagName('input'),
textColl = document.getElementsByTagName('textarea');
//html集合转化为数组
function toArray(coll){
for(var i = 0, a = [], len = coll.length; i < len; i++){
a[i] = coll[i];
}
return a;
}
var inpArr = toArray(inpColl),
textArr = toArray(textColl),
placeholderArr = inpArr.concat(textArr);
for (var i = 0; i < placeholderArr.length; i++){
if (placeholderArr[i].getAttribute('placeholder')){
new Placeholder(placeholderArr[i]);
}
}
}
})() 



Date.prototype.format = function(style) {
	  var o = {   
	    "M+" : this.getMonth() + 1, //month   
	    "d+" : this.getDate(),      //day   
	    "h+" : this.getHours(),     //hour   
	    "m+" : this.getMinutes(),   //minute   
	    "s+" : this.getSeconds(),   //second   
	    "w+" : "��һ����������".charAt(this.getDay()),   //week   
	    "q+" : Math.floor((this.getMonth() + 3) / 3),  //quarter   
	    "S"  : this.getMilliseconds() //millisecond   
	  }   
	  if(/(y+)/.test(style)) {   
		style = style.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));   
	  }
	  for(var k in o){
	    if(new RegExp("("+ k +")").test(style)){   
	      style = style.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));   
	    }
	  } 
	  return style;
	}

function placeholerSupport(){
	//判断是否支持placeholder
	function isPlaceholer(){
		var input = document.createElement('input');
		return "placeholder" in input;
	}
	//不支持的代码
	if(!isPlaceholer()){
		//创建一个类
		function Placeholder(obj){
			this.input = obj;
			this.label = document.createElement('label');
			this.label.innerHTML = obj.getAttribute('placeholder');
			this.label.style.cssText = 'position:absolute;margin-top:5px; text-indent:4px;color:#999999; font-size:12px;';
			if(obj.value != ''){
				this.label.style.display = 'none';
			}
			this.init();
		}
		Placeholder.prototype = {
			//取位置
			getxy : function(obj){
				var left, top;
				if(document.documentElement.getBoundingClientRect){
					var html = document.documentElement,
					body = document.body,
					pos = obj.getBoundingClientRect(),
					st = html.scrollTop || body.scrollTop,
					sl = html.scrollLeft || body.scrollLeft,
					ct = html.clientTop || body.clientTop,
					cl = html.clientLeft || body.clientLeft;
					left = pos.left + sl - cl;
					top = pos.top + st - ct;
				}else{
					while(obj){
						left += obj.offsetLeft;
						top += obj.offsetTop;
						obj = obj.offsetParent;
					}
				}
				return{
					left: left,
					top : top
				}
			},
			//取宽高
			getwh : function(obj){
				return {
					w : obj.offsetWidth,
					h : obj.offsetHeight
				}
			},
			//添加宽高值方法
			setStyles : function(obj,styles){
				for(var p in styles){
					obj.style[p] = styles[p]+'px';
				}
			},
			init : function(){
				var label = this.label,
				input = this.input,
				xy = this.getxy(input),
				wh = this.getwh(input);
				this.setStyles(label, {'width':wh.w, 'height':wh.h, 'lineHeight':20, 'left':xy.left, 'top':xy.top});
				document.body.appendChild(label);
				label.onclick = function(){
					this.style.display = "none";
					input.focus();
				}
				input.onfocus = function(){
					label.style.display = "none";
				};	
				input.onblur = function(){
					if(this.value == ""){
						label.style.display = "block";
					}
				};
			}
		}
		var inpColl = document.getElementsByTagName('input'),
		textColl = document.getElementsByTagName('textarea');
		//html集合转化为数组
		function toArray(coll){
			for(var i = 0, a = [], len = coll.length; i < len; i++){
				a[i] = coll[i];
			}
			return a;
		}
		var inpArr = toArray(inpColl),
		textArr = toArray(textColl),
		placeholderArr = inpArr.concat(textArr);
		for (var i = 0; i < placeholderArr.length; i++){
			if (placeholderArr[i].getAttribute('placeholder')){
				new Placeholder(placeholderArr[i]);
			}
		}
	}
}
/**
*
*  Base64 encode / decode
*
*  @author haitao.tu
*  @date   2010-04-26
*  @email  tuhaitao@foxmail.com
*
*/
 
function Base64() {
 
	// private property
	_keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
 
	// public method for encoding
	this.encode = function (input) {
		var output = "";
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;
		input = _utf8_encode(input);
		while (i < input.length) {
			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}
			output = output +
			_keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
			_keyStr.charAt(enc3) + _keyStr.charAt(enc4);
		}
		return output;
	}
 
	// public method for decoding
	this.decode = function (input) {
		var output = "";
		var chr1, chr2, chr3;
		var enc1, enc2, enc3, enc4;
		var i = 0;
		input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
		while (i < input.length) {
			enc1 = _keyStr.indexOf(input.charAt(i++));
			enc2 = _keyStr.indexOf(input.charAt(i++));
			enc3 = _keyStr.indexOf(input.charAt(i++));
			enc4 = _keyStr.indexOf(input.charAt(i++));
			chr1 = (enc1 << 2) | (enc2 >> 4);
			chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
			chr3 = ((enc3 & 3) << 6) | enc4;
			output = output + String.fromCharCode(chr1);
			if (enc3 != 64) {
				output = output + String.fromCharCode(chr2);
			}
			if (enc4 != 64) {
				output = output + String.fromCharCode(chr3);
			}
		}
		output = _utf8_decode(output);
		return output;
	}
 
	// private method for UTF-8 encoding
	_utf8_encode = function (string) {
		string = string.replace(/\r\n/g,"\n");
		var utftext = "";
		for (var n = 0; n < string.length; n++) {
			var c = string.charCodeAt(n);
			if (c < 128) {
				utftext += String.fromCharCode(c);
			} else if((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			} else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}
 
		}
		return utftext;
	}
 
	// private method for UTF-8 decoding
	_utf8_decode = function (utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;
		while ( i < utftext.length ) {
			c = utftext.charCodeAt(i);
			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			} else if((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i+1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			} else {
				c2 = utftext.charCodeAt(i+1);
				c3 = utftext.charCodeAt(i+2);
				string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}
		}
		return string;
	}
}
/**
 * 输入需要绑定enter键的DOM id
 */
function bindEnterEvent(callback){
	for(var i=1; i<arguments.length; i++){
		$("#"+arguments[i]).keydown(function(event){
	        if (event.keyCode == 13) {  
	        	callback();
	        }  
		});
	}
}

function checkLanguageEnv(locale){
	if(locale == "en"){
		$("#zh_div").show();
		$("#en_div").hide();
	}else{
		$("#zh_div").hide();
		$("#en_div").show();
	}
}

function YesOrNoDialog(obj){
	this.dom_id = obj.dom_id;
	this.dom_class = obj.dom_class;
	this.btnFunc = obj.submitFunc;
	this.title = obj.dialog_title;
	this.btn_title1 = (obj.submit_title == undefined)?"OK":obj.submit_title;
	this.btn_title2 = (obj.cancel_title == undefined)?"Cancel":obj.cancel_title;
}
YesOrNoDialog.prototype = {
	createDialog : function(){
		var self = this;
		var _dialogTitle = self.title;
		var _dom_selector = (self.dom_id == undefined)?("."+self.dom_class):("#"+self.dom_id);
		$(_dom_selector).after("<div id='createYesOrNoDialog'  style='padding-top:40px;'>"
				+"<button id='YesOrNoDialogButton1' class='YesOrNoDialog_button'>"+self.btn_title1+"</button>"
				+"<button id='YesOrNoDialogButton2' class='YesOrNoDialog_button'>"+self.btn_title2+"</button>"
				+"</div>");
		$("#createYesOrNoDialog").dialog({
			autoOpen:false,
		    resizable: false,
		    width:470,
		    modal: true,
		    title:_dialogTitle,
		    overlay: {
		        backgroundColor: '#000',
		        opacity: 0.5
		    }
		});
		$("#YesOrNoDialogButton2").click(function(){
			$("#createYesOrNoDialog").dialog("close");
		});
		self.setBtnFuncClick();
	},
	openDialog : function(operate){
		if(operate != undefined){
			$("#createYesOrNoDialog").dialog(operate);
		}
		$("#createYesOrNoDialog").dialog("open");
	},
	setBtnFuncClick : function(func,param){
		$("#YesOrNoDialogButton1").unbind("click");
		if(func == undefined){
			$("#YesOrNoDialogButton1").bind("click",this._btnFunc);
		}else{
			if(typeof(param) == "object"){
				var param_object = new Object();
				for(var key in param){
					param_object[key] = param[key];
				}
				$("#YesOrNoDialogButton1").bind("click",param_object,func);
			}else{
				$("#YesOrNoDialogButton1").bind("click",{param1:param},func);
			}
		}
	},
	closeDialog : function(){
		$("#createYesOrNoDialog").dialog("close");
	}
}
