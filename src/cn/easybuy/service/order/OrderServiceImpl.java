package cn.easybuy.service.order;


import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.easybuy.dao.order.OrderDao;
import cn.easybuy.dao.order.OrderDaoImpl;
import cn.easybuy.dao.order.OrderDetailDao;
import cn.easybuy.dao.order.OrderDetailDaoImpl;
import cn.easybuy.dao.order.UserAddressDaoImpl;
import cn.easybuy.dao.product.ProductDao;
import cn.easybuy.dao.product.ProductDaoImpl;
import cn.easybuy.entity.Order;
import cn.easybuy.entity.OrderDetail;
import cn.easybuy.entity.User;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.ShoppingCart;
import cn.easybuy.utils.ShoppingCartItem;
import cn.easybuy.utils.StringUtils;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao;
	private Connection conn;
	private OrderDetailDao orderDetailDao;
	private ProductDao productDao;
	private ProductService productService ;
	@Override
	public void saveOrder(Order order) {
		try {
			conn=DataSourceUtil.getConnection();
			orderDao = new OrderDaoImpl(conn);
			orderDao.saveOrder(order);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		
	}
	
	@Override
	public Order payShoppingCart(ShoppingCart cart, User user, String address) {
		Order order = new Order();
		try{
			conn = DataSourceUtil.getConnection();
			orderDao = new OrderDaoImpl(conn);
			orderDetailDao = new OrderDetailDaoImpl(conn);
			productService = new  ProductServiceImpl(); 
			conn.setAutoCommit(false);
			//保存订单
			order.setUserId(user.getId());
			order.setLoginName(user.getLoginName());
			order.setUserAddress(address);
			order.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			order.setCost(cart.getSum());
			order.setSerialNumber(StringUtils.randomUUID());
			orderDao.saveOrder(order);
			//循环迭代商品
			for (ShoppingCartItem item : cart.getItems()) {
				//保存详细订单
				OrderDetail detail = new OrderDetail();
				detail.setOrderId(order.getId());
				detail.setCost(item.getCost());
				detail.setProduct(item.getProduct());
				detail.setQuantity(item.getQuantity());
				orderDetailDao.saveOrderDetail(detail);
				//让商品库存减少
				productService.updateStock(item.getProduct().getId(), item.getProduct().getStock());
			}
			
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return order;
	}
	@Override
	public List<Order> queryAllOrderList(int userId, Pager pager) {
		List<Order> orderList = null;
		try {
			conn=DataSourceUtil.getConnection();
			orderDao = new OrderDaoImpl(conn);
			orderDetailDao = new OrderDetailDaoImpl(conn);
			orderList = orderDao.queryOrderList(userId,pager);
			for (Order order : orderList) {
				List<OrderDetail> list =orderDetailDao.queryOrderDetailList(order.getId());
				order.setOrderDetailList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return orderList;
	}
	
	@Override
	public List<Order> queryOrderList(int userId, Pager pager) {
		List<Order> orderList = null;
		try{
			conn=DataSourceUtil.getConnection();
			orderDao = new OrderDaoImpl(conn);
			orderList = orderDao.queryOrderList(userId, pager);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return orderList;
	}
	
	@Override
	public int getOrderCount(int userId) {
		int result = 0;
		try {
			conn=DataSourceUtil.getConnection();
			orderDao = new OrderDaoImpl(conn);
			result = orderDao.getOrderCount(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return result;
	}
}
	