package cn.easybuy.service.order;

import java.sql.Connection;
import java.util.List;

import cn.easybuy.dao.order.UserAddressDao;
import cn.easybuy.dao.order.UserAddressDaoImpl;
import cn.easybuy.dao.user.UserDaoImpl;
import cn.easybuy.entity.UserAddress;
import cn.easybuy.utils.DataSourceUtil;

public class UserAddressServiceImpl implements UserAddressService {

	private Connection connection;
	private UserAddressDao userAddressDao ;
	@Override
	public List<UserAddress> queryUserAddressList(int userId) {
		List<UserAddress> list = null;
		try {
			connection=DataSourceUtil.getConnection();
			userAddressDao = new UserAddressDaoImpl(connection);
			list = userAddressDao.queryUserAddressList(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(connection, null, null);
		}
		return list;
	}
	
	@Override
	public int addUserAddress(UserAddress userAddress) {
		int id= 0;
		try {
			connection=DataSourceUtil.getConnection();
			userAddressDao = new UserAddressDaoImpl(connection);
			id = userAddressDao.addUserAddress(userAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(connection, null, null);
		}
		return id;
	}
	
	@Override
	public UserAddress getAddressById(int id) {
		UserAddress userAddress = null;
		try {
			connection=DataSourceUtil.getConnection();
			userAddressDao = new UserAddressDaoImpl(connection);
			userAddress = userAddressDao.getAddressById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(connection, null, null);
		}
		return userAddress;
	}

}
