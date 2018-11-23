package cn.easybuy.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.PrintUtil;
import cn.easybuy.utils.ReturnResult;

public abstract class AbstractServlet extends HttpServlet {
	public abstract Class getServletClass();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String action = req.getParameter("action");
		Method method = null;
		Object result = null;
		
		try {
			if (EmptyUtils.isEmpty(action)) {
				result=execute();
			}else {
				//我只知道把action放到这里   会返回 跳转路径 然后 toView()
				method=getServletClass().getDeclaredMethod(action, HttpServletRequest.class,HttpServletResponse.class);
				result=method.invoke(this,req,resp);
			}
			toView(req, resp, result);
		} catch (NoSuchMethodException e) {
			String viewName="404.jsp";
			req.getRequestDispatcher(viewName).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
			if (!EmptyUtils.isEmpty(result)) {
				if (result instanceof String) {
					String viewName="500.jsp";
					req.getRequestDispatcher(viewName).forward(req, resp);
				}else {
					ReturnResult returnResult=new ReturnResult();
					returnResult.returnFail("系统错误");
					PrintUtil.write(returnResult, resp);
				}
			}else {
				String viewName="500.jsp";
				req.getRequestDispatcher(viewName).forward(req, resp);
			}
		}

	}
	
	protected void toView(HttpServletRequest req,HttpServletResponse resp,Object result)
			throws ServletException, IOException {
		if (!EmptyUtils.isEmpty(result)) {
			if (result instanceof String) {
				String viewName=result.toString()+".jsp";
				req.getRequestDispatcher(viewName).forward(req,resp);
			}else {
				PrintUtil.write(result, resp);
			}
		}
	}
	
	private Object execute() {
		
		return "/pre/index";
	}
}
