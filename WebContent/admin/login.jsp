<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html  >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录小米后台管理系统</title>
<link href="<%= basePath%>admin/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%= basePath%>admin/js/jquery.js"></script>
<script src="<%= basePath%>admin/js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    
    <form action="<%= basePath%>user" method="post">
    	<input name="func" type="hidden" value="adminLogin" />
	    <ul>
	    <li>
	    <font style="color: red">${msg }</font><br>
	    <input name="username" type="text" class="loginuser" /></li>
	    <li><input name="password" type="password" class="loginpwd" /></li>
	    <li><input name="" type="submit" class="loginbtn" value="登录"    /><label><input name="" type="checkbox" value="" checked="checked" />记住密码</label><label><a href="#">忘记密码？</a></label></li>
	    </ul>
    </form>
    
    </div>
    
    </div>
    
    
    
    <div class="loginbm">版权所有  2013  .com 仅供学习交流，勿用于任何商业用途</div>
</body>
</html>
