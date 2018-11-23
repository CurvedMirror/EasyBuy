function login() {
	
	var loginName=$("#loginName").val();
	var password = $("#password").val();
	if(loginName==""){
		showMessage("请输入用户名");
		return;
	}
	if(password==""){
		showMessage("请输入密码");
		return false;
	}
	
	$.ajax({
		url:contextPath+"/login",
		type:"post",
		data:{loginName:loginName,password:password,action:"login"},
		dataType:"json",
		
		success:function(jsonStr){
	
			
			if(jsonStr.status==1){
				window.location.href = contextPath + "/Home?action=index";
			}else{
				showMessage(jsonStr.message);
			}
		}
	});
}