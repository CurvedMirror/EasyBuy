package cn.easybuy.web.pre;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.easybuy.entity.User;
import cn.easybuy.service.user.UserService;
import cn.easybuy.service.user.UserServiceImpl;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.ReturnResult;
import cn.easybuy.utils.SecurityUtils;
import cn.easybuy.utils.ShoppingCart;
import cn.easybuy.utils.ShoppingCartItem;
import cn.easybuy.web.AbstractServlet;

@WebServlet(urlPatterns={"/login"},name="login")
public class LoginServlet extends AbstractServlet{
	
	//注入用户业务类
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		userService=new UserServiceImpl();
	}
	
	/**
	 * 重写相关方法
	 */
	@Override
	public Class getServletClass() {
		return LoginServlet.class;
	}
	/**
	 * 跳转到登录页面
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String toLogin(HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException, IOException {
		return "/pre/login";
	}
	
	 

	public ReturnResult login(HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException, IOException {
		ReturnResult result=new ReturnResult();
		//参数获取
		String loginName=req.getParameter("loginName");
		String password=req.getParameter("password");
		User user=userService.getUserByLoginName(loginName);
		if (EmptyUtils.isEmpty(user)) {
			result.returnFail("用户不存在");
		}else {
			//如果得到的密码和    用户输入的密码加密后相同
			if (user.getPassword().equals(SecurityUtils.md5Hex(password))) {
				req.getSession().setAttribute("loginUser", user);
				result.returnSuccess("登录成功!");
			}else {
				result.returnFail("密码错误!");
			}
		}
		
		return result;
	}
	
	public String  loginOut(HttpServletRequest request,HttpServletResponse response) 
		throws ServletException, IOException {
		ReturnResult result=new ReturnResult();
		request.getSession().removeAttribute("loginUser");
	    ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
	    if (cart!=null) {
			if (cart.getItems().size() != 0) {
				cart.getItems().clear();
			}
		}
		result.returnSuccess("注销成功");
		return "/pre/login";
	}
	
}
