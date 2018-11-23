function deleteUserId(id) {
	if(confirm("是否要删除该用户？")){
		$.post(contextPath+"/admin/user", "action=deleteUserById&id=" + id, function(data) {
			if (data.status == 1) {
				window.location.reload();
			} else {
				showMessage(data.message);
			}
		}, "json");
	}
}
/**
 * 修改用户
 */
function updateUser(isAdd) {
	 if(checkUser()){
		 //使用jQuery的 SerializeArray（）方法实现
		var $inputParams = $("#userAdd");
		var paramsArray = $inputParams.serializeArray(); //将表单编码成数组格式
		var queryString = $.param(paramsArray); //将数据序列化成请求字符串
		$.post(contextPath+"/admin/user?action=updateUser&isAdd="+isAdd,queryString,function(data){
			if(data.status==1){
				showMessage(data.message);
				toUserList();
			}else{
				showMessage(data.message);
			}
		
		},"JSON");
	 }
}
/**
 * 跳转用户列表
 */
function toUserList() {
	$("#aaa").load(contextPath+"/admin/user?action=queryUserList");
}
/**
 * 判断用户是否重复
 */
function checkName(name) {
	$.post(contextPath+"/admin/user","action=checkName&name="+name,function(data){
		if(data.status==-1){
			showMessage(data.message);
			return false;
		}
		
	},"json");
}
/**
 * 检查用户
 */
function checkUser() {
    var loginName = $("input[name='loginName']").val();
    var userName = $("input[name='userName']").val();
    var identityCode = $("input[name='identityCode']").val();
    var email = $("input[name='email']").val();
    var mobile = $("input[name='mobile']").val();
    var type = $("select[name='type']").val();
    var password = $("input[name='password']").val();
    var repPassword = $("input[name='repPassword']").val();
    var id = $("input[name='id']").val();

    if (loginName == null || loginName == "") {
        showMessage("请填写登录用户名");
        return false;
    }
    
    if(loginName.length<2 || loginName>10){
        showMessage("登录名不能小于两个字符或者大于十个字符.");
        return false;
    }

    if (userName == null || userName == "") {
        showMessage("请填写真实姓名");
        return false;
    }
    if(id==null || id=="" || id==0){
    	if (password == null || password == "") {
            showMessage("请填写密码");
            return false;
        }
        
        if (password !=repPassword) {
            showMessage("两次输入密码不一致");
            return false;
        }
    }

    if(email!=null && email!="" && !checkMail(email)){
    	showMessage("邮箱格式不正确");
        return false;
    }
    //验证邮箱格式
    if(mobile==null || mobile==""|| !checkMobile(mobile)){
    	showMessage("手机格式不正确");
        return false;
    }
     //验证邮箱格式
    if(identityCode!=null && identityCode!="" && !checkIdentityCode(identityCode)){
    	showMessage("身份证号格式不正确");
        return false;
    }
    return true;
}
function checkMail(mail) {
	  var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  if (filter.test(mail)) 
		  return true;
	  else 
		 return false;
	}

	function checkMobile(phone) {
	  var filter = /^1[3|4|5|8][0-9]\d{8}$/;
	  if (filter.test(phone)) 
		  return true;
	  else {
		 return false;
	  }
	}

	function checkIdentityCode(identityCode) {
	  var filter  = /^\w{18}$/;
	  if (filter.test(identityCode)) 
		  return true;
	  else {
		 return false;
	  }
	}
