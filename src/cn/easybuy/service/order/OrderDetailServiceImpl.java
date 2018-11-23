package cn.easybuy.service.order;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.order.OrderDetailDao;
import cn.easybuy.dao.order.OrderDetailDaoImpl;
import cn.easybuy.entity.OrderDetail;
import cn.easybuy.utils.DataSourceUtil;

public class OrderDetailServiceImpl implements OrderDetailService {
	private OrderDetailDao orderDetailDao;
	private Connection conn;

	@Override
	public void saveOrderDetail(OrderDetail detail, int orderId) {
		conn = DataSourceUtil.getConnection();
		orderDetailDao  = new OrderDetailDaoImpl(conn);
		try {
			orderDetailDao.saveOrderDetail(detail);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
	}
	@Override
	public List<OrderDetail> queryOrderDetailList(int orderId) {
		List<OrderDetail> list = null;
		try {
			conn = DataSourceUtil.getConnection();
			orderDetailDao  = new OrderDetailDaoImpl(conn);
			list = orderDetailDao.queryOrderDetailList(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return list;
	}
}
