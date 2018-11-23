package cn.easybuy.service.order;

import java.util.List;

import cn.easybuy.entity.Order;
import cn.easybuy.entity.User;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.ShoppingCart;

public interface OrderService {
	/**
	 * 保存订单
	 * @param order
	 */
	public void saveOrder(Order order);
	/**
	 * 结算购物车物品 1.生成订单 2.生成订单明细 3.减库存 注意加入事物的控制
	 * @param cart
	 * @param user
	 * @param address
	 * @return
	 */
	Order payShoppingCart(ShoppingCart cart, User user, String address);
	/**
	 * 查询用户订单
	 * @param userId
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	List<Order> queryOrderList(int userId,Pager pager);
	/**
	 * 查询我的订单
	 * @param userId
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	List<Order> queryAllOrderList(int userId,Pager pager);
	/**
	 * 查询订单记录数
	 */
	int getOrderCount(int userId);
}
