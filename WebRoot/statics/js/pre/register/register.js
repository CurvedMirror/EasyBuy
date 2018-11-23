function register() {
	
	//获取相关字段的值
	var loginName = $("input[name='loginName']").val();
	var userName = $("input[name='userName']").val();
	var password = $("input[name='password']").val();
	var confirmPassword = $("input[name='confirmPassword']").val();
	var email = $("input[name='email']").val();
	var mobile = $("input[name='mobile']").val();
	var identityCode = $("input[name='identityCode']").val();
	var sex = $("input[name='sex']:checked").val(); 
	
	
	//判断
	if(loginName==null || loginName==""){
		showMessage("用户名不能为空。");
		return;
	}
	if(loginName.length<2 || loginName.length>10){
		showMessage("登录名不能小于两个字符或者大于十个字符。");
		return;
	}
	if (password =="") {
		showMessage("密码不能为空");
		return;
	}
	if (password!=confirmPassword) {
		showMessage("两次输入的密码不一致");
		return;
	}
	if(userName==null || userName==""){
		showMessage("真实姓名不能为空。");
		return;
	}
	if(userName.length<2 || userName>10){
		showMessage("真实姓名不能小于两个字符或者大于十个字符。");
		return;
	}
	if(identityCode==null|| identityCode=="" || !checkIdentityCode(identityCode)){
		showMessage("身份证格式不正确");
		return;
	}
	
	if(email==null || email=="" || !checkMail(email)){
		showMessage("邮箱格式不正确");
		return;
	}
	if(mobile == null || mobile=="" || !checkMobile(mobile)){
		showMessage("手机格式不正确");
		return;
	}
	
	//使用jQuery的 SerializeArray（）方法实现
	var $inputParams = $("#zhuce :input");
	var paramsArray = $inputParams.serializeArray(); //将表单编码成数组格式
	var queryString = $.param(paramsArray); //将数据序列化成请求字符串
	$.post(contextPath+"/Register?action=doRegister",queryString,function(data){
		if(data.status==1){
			alert("注册成功");
			window.location.href=contextPath+"/login?action=toLogin";
		}else{
			showMessage(data.message);
		}
	},"JSON");
	
	
	//用于验证的正则表达式
	function checkMail(email) {
		var filter = /^\w+([-+.]\\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		if(filter.test(email)){
			return true;
		}else{
			return false;
		}
	}
	
	function checkMobile(phone) {
		var filter = /^1[3|4|5|8][0-9]\d{8}$/;
		if(filter.test(phone)){
			return true;
		}else{
			return false;
		}
	}
	
	function checkIdentityCode(identityCode) {
		var filter = /^\d{15}|\d{18}$/;
		if(filter.test(identityCode)){
			return true;
		}else{
			return false;
		}
	}
	
	
}