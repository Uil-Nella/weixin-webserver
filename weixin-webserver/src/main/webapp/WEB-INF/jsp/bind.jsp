<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>关联用友域账号</title>
<meta name="keywords" content="绑定" />
<meta name="description" content="身份绑定" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->
<link rel="stylesheet" href="static/css/reset.css">
<link rel="stylesheet" href="static/css/supersized.css">
<link rel="stylesheet" href="static/css/style.css">
<!-- Javascript -->
<script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="static/js/supersized.3.2.7.min.js"></script>
<!-- <script type="text/javascript" src="static/js/supersized-init.js"></script> -->
<script type="text/javascript" src="static/js/scripts.js"></script>
<script type="text/javascript" src="static/js/WeixinApi.js"></script>
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
<!-- 在链接里引入无法生效  不知道为什么 -->
<script type="text/javascript">
	jQuery(function($) {
		$.supersized({
			// Functionality
			slide_interval : 4000, // Length between transitions
			transition : 1, // 0-None, 1-Fade, 2-Slide Top, 3-Slide Right, 4-Slide Bottom, 5-Slide Left, 6-Carousel Right, 7-Carousel Left
			transition_speed : 1000, // Speed of transition
			performance : 1, // 0-Normal, 1-Hybrid speed/quality, 2-Optimizes image quality, 3-Optimizes transition speed // (Only works for Firefox/IE, not Webkit)
			// Size & Position
			min_width : 0, // Min width allowed (in pixels)
			min_height : 0, // Min height allowed (in pixels)
			vertical_center : 1, // Vertically center background
			horizontal_center : 1, // Horizontally center background
			fit_always : 0, // Image will never exceed browser width or height (Ignores min. dimensions)
			fit_portrait : 1, // Portrait images will not exceed browser height
			fit_landscape : 0, // Landscape images will not exceed browser width
			// Components
			slide_links : 'blank', // Individual links for each slide (Options: false, 'num', 'name', 'blank')
			slides : [ // Slideshow Images
			{
				image : 'static/img/backgrounds/1.jpg'
			}, {
				image : 'static/img/backgrounds/2.jpg'
			}, {
				image : 'static/img/backgrounds/3.jpg'
			} ]

		});

	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		//传递字符串格式json对象到后台（一个json对象）  
		$("#submit").click(function() {
			var userid = encodeURI($("#userid").attr("value"));
			var username = encodeURI($("#username").attr("value"));
			var password = encodeURI($("#password").attr("value"));

			var user = {
				"userid" : userid,
				"username" : username,
				"password" : password
			};
			var res_user = encodeURI(JSON.stringify(user));

			$.ajax({
				url : "http://121.42.24.106/weixin-web/bindUser.do",
				type : "POST",
				data : "jsondata=" + res_user,
				dataType : "json",
				success : function(data) {
					var arr = eval(data);
					alert(arr.msg);
					if (arr.status == "success") {
						WeixinApi.ready(function(Api) {
							// 关闭窗口 
							WeixinApi.closeWindow({
								success : function(resp) {
								}
							});
						});

					}

				}
			});
		});

	});
</script>
</head>
<body>
	<div class="page-container" style="margin-top: 15px">
		<h1>身份绑定</h1>
		<form action="" method="post">
			<input type="text" name="userid" id="userid" class="username"
				value="${UserId}" placeholder="微信ID" readonly="readonly"> <input
				type="text" name="username" class="username" id="username"
				placeholder="域账号"> <input type="password" name="password"
				id="password" class="password" placeholder="密码">
			<button type="button" id="submit">绑定</button>
			<div class="error">
				<span>+</span>
			</div>
		</form>
		<div class="connect">
			<p>关 联 账 号：</p>
			<p>
				<a class="facebook" href=""></a> <a class="twitter" href=""></a>
			</p>
		</div>
	</div>
</body>

</html>