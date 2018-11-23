package cn.easybuy.service.order;

import java.util.List;

import cn.easybuy.entity.UserAddress;

public interface UserAddressService {
	/**
	 * 根据用户id查询用户地址
	 * @param userId
	 * @return
	 */
	public List<UserAddress> queryUserAddressList(int userId);
	/**
	 * 新增收货地址
	 * @param userAddress
	 * @return
	 * @throws Exception
	 */
	int addUserAddress(UserAddress userAddress);
	/**
	 * 根据地址id查询地址
	 */
	UserAddress getAddressById(int id);
}
