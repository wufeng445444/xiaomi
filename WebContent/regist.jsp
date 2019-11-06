<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html >
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<body>
<div class="sign_background">
    <div class="sign_background_in">
        <div class="sign_background_no1">
            <a href="index.html"><img src="img/logo.jpg" alt=""></a>
        </div>
        <div class="sign_background_no2">注册小米帐号</div>
        <div class="sign_background_no3">
               
            <div class="sign_background_no5">
             	
             	<form action="user?func=registUser" method="post" enctype="multipart/form-data">
             	
             		<table style="width: 500px;" border="0" cellspacing="0">
             			<tr>
             				<td width="25%" class="_left">姓名：</td>
             				<td><input type="text" id="uname" name="uname" /><span id="unameInfo"></span></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">性别：</td>
             				<td>
             					男<input name="gender" type="radio" value="1">
             				 	女<input name="gender" type="radio" value="0">
							</td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">电话号码：</td>
             				<td><input id="phone" type="text" name="phone" /><span id="phoneInfo"></span></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">所在地区：</td>
             				<td><input id="area" type="text" name="area"/><span id="areaInfo"></span></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">账号：</td>
             				<td><input id="username" type="text" name="username"><span id="usernameInfo"></span></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">密码：</td>
             				<td><input id="pwd" type="password" style="width:220px;height:30px" name="password"/><span id="pwdInfo"></span></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">上传头像：</td>
             				<td><input id="photo" type="file" name="photo" /></td>
             			</tr>
             		</table>
             		<div class="sign_background_no6" id="btn" onclick="sub()" >立即注册</div>
             	</form>
             	 
            </div>
        </div>
        <div class="sign_background_no7">注册帐号即表示您同意并愿意遵守小米 <span>用户协议</span>和<span>隐私政策</span> </div>
    </div>
    <div class="sign_background_no8"><img src="img/sign01.jpg" alt=""></div>

</div>
</body>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
/*姓名的非空校验 */
$("#uname").blur(function(){
	//获取姓名
	var uname=$(this).val();
	//判断是否为空
	if(uname==null||uname==""){
		$("#unameInfo").text("姓名不能不能为空").css("color","red");
		$("#uname").focus();
	}else{
		$("#unameInfo").text("姓名正确").css("color","green");
	}
})
/*地区的非空校验 */
$("#area").blur(function(){
	/*获取地区  */
	var area=$(this).val();
	/*判断是否为空  */
	if(area==null||area==""){
		$("#areaInfo").text("地区不能为空").css("color","red");
		$(this).focus();
	}else{
		$("#areaInfo").text("地区正确").css("color","green");
	}
	
})
/*密码的非空校验 */
$("#pwd").blur(function(){
	var pwd=$(this).val();
	if(pwd=null||pwd==""){
		$("#pwdInfo").text("密码不能为空").css("color","red");
		$(this).focus();
	}else{
		$("#pwdInfo").text("密码为空").css("color","green");
		
	}
})

/*完成手机号码的非空、正则、唯一校验  */
$("#phone").blur(function() {
	var phone = $(this).val();
	//判断是否为空
	if(phone==null||phone==""){
		$("#phoneInfo").text("手机号码不能为空").css("color","red");
		//这个return不能忘记，如果这个，条件不满足，将不再往下进行代码
		$(this).focus();
		return;
	}
	
	//正则校验
	if(!(/^1[3456789]\d{9}$/.test(phone))){
		$("#phoneInfo").text("电话号码格式不正确").css("color","red");
		$(this).focus();
	}
	//唯一校验
	else{
		$.ajax({
			//注意这用逗号分割
			url:"user",
			type:"post",
			data: {"phone": phone, "func": "checkPhone"},
			dataType:"json",
			success:function(isRegest){
				if(isRegest){
					$("#phoneInfo").text("手机已经注册").css("color","red");
					$("#phone").focus();
				}else{
					$("#phoneInfo").text("注册成功").css("color","green");
				}
			}
		})
	}
})
//完成用户名的非空、唯一校验

$("#username").blur(function(){
	//获取用户名
	var username=$(this).val();
	//判断非空
	if(username==null||username==""){
		$("#usernameInfo").text("用户名不能为空").css("color","red");
		$("#username").focus();
		return;
		
	}//唯一校验
	else{
		$.post(
			"user",
			{"username":username,"func":"checkUsername"},
			function(isRegist){
				 if(isRegist){
					 $("#usernameInfo").text("用户名已被注册").css("color","red");
				 }else{
					 $("#usernameInfo").text("用户名正确").css("color","green");
				 }
				 
			 },
			"json"
			
		)
		
	}
})
//点击注册按钮，完成登录流程(不考虑上传文件)
function sub(){
	$("form").submit();
}

















</script>


</html>















