package cn.easybuy.dao.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.BaseDao;
import cn.easybuy.dao.product.ProductDao;
import cn.easybuy.dao.product.ProductDaoImpl;
import cn.easybuy.entity.OrderDetail;
import cn.easybuy.entity.Product;
import cn.easybuy.utils.DataSourceUtil;

public class OrderDetailDaoImpl extends BaseDao implements OrderDetailDao{
	
	private ProductDao productDao;
	
	public OrderDetailDaoImpl(Connection conn) {
		super(conn);
		productDao=new ProductDaoImpl(conn);
	}
	
	
	 public OrderDetail tableToClass(ResultSet rs) throws Exception {
	        OrderDetail orderDetail = new OrderDetail();
	        orderDetail.setId(rs.getInt("id"));
	        orderDetail.setOrderId(rs.getInt("orderId"));
	        orderDetail.setProduct(productDao.getDetailById(rs.getInt("productId")));
	        orderDetail.setProductId(rs.getInt("productId"));
	        orderDetail.setQuantity(rs.getInt("quantity"));
	        orderDetail.setCost(rs.getFloat("cost"));
	        return orderDetail;
	  }
	 
	@Override
	public void saveOrderDetail(OrderDetail detail) throws Exception {
		int id = 0;
		String sql=" insert into easybuy_order_detail(orderId,productId,quantity,cost) values(?,?,?,?) ";
        Object param[]=new Object[]{detail.getOrderId(),detail.getProduct().getId(),detail.getQuantity(),detail.getCost()};
        id=this.executeInsert(sql,param);
        detail.setId(id);
        DataSourceUtil.closeAll(null, pstmt, rs);
	}
	
	@Override
	public List<OrderDetail> queryOrderDetailList(int orderId) throws Exception {
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		String sql = "SELECT id,orderId,productId,quantity,cost FROM easybuy_order_detail WHERE orderId=? ";
		Object[] params = {orderId};
		rs = executeQuery(sql, params);
		while(rs.next()){
			OrderDetail orderDetail = new OrderDetail();
			orderDetail = tableToClass(rs);
			list.add(orderDetail);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return list;
	}
}
