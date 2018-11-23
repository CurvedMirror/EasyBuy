package cn.easybuy.dao.order;

import java.util.List;

import cn.easybuy.entity.UserAddress;


public interface UserAddressDao {
	/**
	 * 根据用户id查询用户地址
	 * @param userId
	 * @return
	 */
	public List<UserAddress> queryUserAddressList(int userId) throws Exception;
	/**
	 * 新增收货地址
	 * @param userAddress
	 * @return
	 * @throws Exception
	 */
	int addUserAddress(UserAddress userAddress) throws Exception;
	/**
	 * 根据地址id查询地址
	 */
	UserAddress getAddressById(int id) throws Exception;
}
