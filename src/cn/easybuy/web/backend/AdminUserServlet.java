package cn.easybuy.web.backend;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.entity.User;
import cn.easybuy.service.user.UserService;
import cn.easybuy.service.user.UserServiceImpl;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.ReturnResult;
import cn.easybuy.utils.SecurityUtils;
import cn.easybuy.web.AbstractServlet;

@WebServlet(urlPatterns = { "/admin/user" }, name = "adminUser")
public class AdminUserServlet extends AbstractServlet {

	private UserService userService;

	public void init() throws ServletException {
		userService = new UserServiceImpl();
	}

	@Override
	public Class getServletClass() {

		return AdminUserServlet.class;
	}

	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("menu", 2);
		return "/backend/user/userInfo";
	}

	/**
	 * 分页查询用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryUserList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String currentPageStr = request.getParameter("currentPage");
		int rowPerPage = userService.rowCount();
		int currentPage = EmptyUtils.isNotEmpty(currentPageStr) ? Integer
				.parseInt(currentPageStr) : 1;
		Pager pager = new Pager(rowPerPage, 6, currentPage);
		pager.setUrl("admin/user?action=queryUserList");
		List<User> userList = userService.getAllUserByPage(pager);
		request.setAttribute("userList", userList);
		request.setAttribute("menu", 8);
		request.setAttribute("pager", pager);
		return "/backend/user/userList";
	}

	/**
	 * 删除用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ReturnResult deleteUserById(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnResult result = new ReturnResult();
		String id = request.getParameter("id");
		int ok = userService.deleteUserById(Integer.parseInt(id));
		if (ok > 0) {
			result.returnSuccess();
		} else {
			result.returnFail("删除失败");
		}
		return result.returnSuccess();
	}

	/**
	 * 根据id查询用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String toUpdateUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		User user = userService.getById(id);
		request.setAttribute("user", user);
		return "/backend/user/toUpdateUser";
	}
	
	/**
	 * 修改用户信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ReturnResult updateUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnResult result = new ReturnResult();
		
		//判断是否是新增
		String isAdd = request.getParameter("isAdd");
		int id=Integer.parseInt(request.getParameter("id"));
		String loginName = request.getParameter("loginName");
		String userName =request.getParameter("userName");
		String identityCode =request.getParameter("identityCode");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String password =request.getParameter("password");
		int type =Integer.parseInt(request.getParameter("type"));
		
		User user = new User();
		user.setLoginName(loginName);
		user.setId(id);
		user.setUserName(userName);
		user.setIdentityCode(identityCode);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setType(type);
		user.setPassword(EmptyUtils.isEmpty(password)?user.getPassword():SecurityUtils.md5Hex(password));
		
		int ok = 0;
		if (isAdd.equals("修改信息")) {
			 ok = userService.updateUser(user);
		}else{
		     ok = userService.addUser(user);
		}
		
		if (ok>0) {
			result.returnSuccess("操作成功");
		}else {
			result.returnFail("操作失败");
		}
		return result;
	}
	/**
	 * 添加用户
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String toAddUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user = new User();
		request.setAttribute("user", user);
		return "/backend/user/toUpdateUser";
	}
	
	/**
	 * 判断用户名是否重名
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ReturnResult checkName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnResult result = new ReturnResult();
		String name =request.getParameter("name");
		int ok = userService.checkByName(name);
		if (ok!=0) {
			result.returnFail("用户名已存在");
		}
		return result;
	}

}
