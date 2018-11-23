package cn.easybuy.service.order;

import java.util.List;

import cn.easybuy.entity.OrderDetail;

public interface OrderDetailService {
	/**
	 * 保存订单详情
	 */
	void saveOrderDetail(OrderDetail detail, int orderId);
	/**
	 * 查询商品详单
	 * @return
	 */
	List<OrderDetail> queryOrderDetailList(int orderId);
}
