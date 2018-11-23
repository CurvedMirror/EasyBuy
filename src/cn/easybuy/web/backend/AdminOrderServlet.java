package cn.easybuy.web.backend;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.easybuy.entity.Order;
import cn.easybuy.entity.OrderDetail;
import cn.easybuy.entity.User;
import cn.easybuy.service.order.OrderDetailService;
import cn.easybuy.service.order.OrderDetailServiceImpl;
import cn.easybuy.service.order.OrderService;
import cn.easybuy.service.order.OrderServiceImpl;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.Pager;
import cn.easybuy.web.AbstractServlet;

@WebServlet(urlPatterns = { "/admin/order" }, name = "order")
public class AdminOrderServlet extends AbstractServlet {

	private OrderService orderService;
	private OrderDetailService orderDetailService;
	@Override
	public void init() throws ServletException {
		orderService = new OrderServiceImpl();
		orderDetailService = new OrderDetailServiceImpl();
	}
	 /**
     * 查询订单明细
     * @param request
     * @param response
     * @return
     */
    public String queryOrderDeatil(HttpServletRequest request,HttpServletResponse response)throws Exception{
        String orderId=request.getParameter("orderId");
        List<OrderDetail> orderDetailList=orderDetailService.queryOrderDetailList(Integer.parseInt(orderId));
        request.setAttribute("orderDetailList", orderDetailList);
        request.setAttribute("menu", 1);
        return "/backend/order/orderDetailList";
    }
    
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("menu", 1);
		User user = (User)request.getSession().getAttribute("loginUser");
		if(user==null){
			return "/backend/order/orderList";
		}
		String currentPageStr = request.getParameter("currentPage");
		int currentPage = EmptyUtils.isEmpty(currentPageStr)?1:Integer.parseInt(currentPageStr);
		int orderCount = orderService.getOrderCount(user.getId());
		Pager pager  = new Pager(orderCount,2,currentPage);
		pager.setUrl("admin/order?action=index");
		
		List<Order> orderList = orderService.queryAllOrderList(user.getId(),pager);
		request.setAttribute("orderList", orderList);
		
		request.setAttribute("pager", pager);
		return "/backend/order/orderList";
	}
	/**
	 * 全部订单
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryAllOrder(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String currentPageStr = request.getParameter("currentPage");
		int currentPage = EmptyUtils.isEmpty(currentPageStr)?1:Integer.parseInt(currentPageStr);
		int orderCount = orderService.getOrderCount(0);
		Pager pager  = new Pager(orderCount,2,currentPage);
		pager.setUrl("admin/order?action=queryAllOrder");
		
		List<Order> orderList = orderService.queryAllOrderList(0,pager);
		request.setAttribute("orderList", orderList);
		request.setAttribute("menu", 9);
		request.setAttribute("pager", pager);
		return "/backend/order/orderList";
	}
	@Override
	public Class getServletClass() {
		return AdminOrderServlet.class;
	}
}
