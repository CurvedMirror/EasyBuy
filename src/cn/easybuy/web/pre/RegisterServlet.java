package cn.easybuy.web.pre;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.User;
import cn.easybuy.service.user.UserService;
import cn.easybuy.service.user.UserServiceImpl;
import cn.easybuy.utils.Constants;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.RegexUtils;
import cn.easybuy.utils.ReturnResult;
import cn.easybuy.utils.SecurityUtils;
import cn.easybuy.web.AbstractServlet;

@WebServlet(urlPatterns = {"/Register"}, name="Register")
public class RegisterServlet extends AbstractServlet {

	// 注入用户业务类
	private UserService userService;

	@Override
	public void init() throws ServletException {
		userService = new UserServiceImpl();
	}

	/**
	 * 重写相关方法
	 */
	@Override
	public Class getServletClass() {
		return RegisterServlet.class;
	}

	public String toRegister(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		return "/pre/register";
	}
	public ReturnResult doRegister(HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException, IOException {
		ReturnResult result=new ReturnResult();
		User user=new User();
		String loginName=req.getParameter("loginName");
		User oldUser =userService.getUserByLoginName(loginName);
//		判断oloUser是否不为空
		if (EmptyUtils.isNotEmpty(oldUser)) {
			result.returnFail("用户已经存在");
			return result;
		}
		String sex=req.getParameter("sex");
		String password=req.getParameter("password");
		String userName=req.getParameter("userName");
		String identityCode=req.getParameter("identityCode");
		String email=req.getParameter("email");
		String mobile=req.getParameter("mobile");
		
		user.setLoginName(loginName);
		user.setPassword(SecurityUtils.md5Hex(password));
		user.setSex(EmptyUtils.isEmpty(sex)?0:1);//1男0女
		user.setIdentityCode(identityCode);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setUserName(userName);
		user.setType(Constants.UserType.PRE);
		result=checkUser(user);
		
		//判断是否校验通过
		if (result.getStatus()==Constants.ReturnResult.SUCCESS) {
			boolean flag = userService.save(user);
			if (!flag) {
				return result.returnFail("注册失败");
			}else {
				return result.returnSuccess("注册成功");
			}
		}else {
			return result;
		}
		
	}
	
	/**
	 * 对手机号  邮箱  身份证验证
	 * @param user
	 * @return
	 */
	public ReturnResult checkUser(User user) {
		ReturnResult result = new ReturnResult();
		if (EmptyUtils.isNotEmpty(user.getMobile())) {
			if (!RegexUtils.isPhoneNumber(user.getMobile())) {
				return result.returnFail("手机号格式不正确");
			}
		}
		if (EmptyUtils.isNotEmpty(user.getIdentityCode())) {
			if (!RegexUtils.isIdentityCode(user.getIdentityCode())) {
				return result.returnFail("身份证格式不正确");
			}
		}
		if (EmptyUtils.isNotEmpty(user.getEmail())) {
			if (!RegexUtils.isEmail(user.getEmail())) {
				return result.returnFail("邮箱格式不正确");
			}
		}
		return result.returnSuccess();
	}

}
