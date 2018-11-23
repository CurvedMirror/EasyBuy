package cn.easybuy.dao.order;

import java.util.List;

import cn.easybuy.entity.OrderDetail;

public interface OrderDetailDao {

	/**
	 * 保存订单详情
	 */
	void saveOrderDetail(OrderDetail detail) throws Exception;
	/**
	 * 查询商品详单
	 * @return
	 */
	List<OrderDetail> queryOrderDetailList(int orderId) throws Exception;
	
}
