package cn.easybuy.dao.order;
import java.util.List;

import cn.easybuy.entity.Order;
import cn.easybuy.utils.Pager;

/***
 * 订单处理的dao层
 * getRowCount
 * getRowList(Params params)
 * getById(Integer id)
 * addObject(Params params)
 */
public interface OrderDao  {

	/**
	 * 保存订单
	 * @param order
	 */
	public void saveOrder(Order order) throws Exception;
	/**
	 * 查询用户订单
	 * @param userId
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	List<Order> queryOrderList(int userId,Pager pager) throws Exception;

	/**
	 * 查询订单记录数
	 */
	int getOrderCount(int userId) throws Exception;
}
