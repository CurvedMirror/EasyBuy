package cn.easybuy.service.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.easybuy.dao.user.UserDao;
import cn.easybuy.dao.user.UserDaoImpl;
import cn.easybuy.entity.User;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.Pager;

public class UserServiceImpl implements UserService {

	private Connection connection;
	private UserDao userDao;
	
	@Override
	public User getUserByLoginName(String loginName) {
		User user = null;
		try {
			connection=DataSourceUtil.getConnection();
			userDao = new UserDaoImpl(connection);
			user = userDao.getUserByLoginName(loginName);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(connection, null, null);
		}
		return user;
	}
	@Override
	public boolean save(User user) {
		boolean flag =true;
		try {
			connection=DataSourceUtil.getConnection();
			userDao = new UserDaoImpl(connection);
			int count=userDao.save(user);
			flag=count>0;
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(connection,null,null);
		}
		return flag;
	}
	
	@Override
	public List<User> getAllUserByPage(Pager pager) {
		List<User> list =null;
		try {
			connection=DataSourceUtil.getConnection();
			userDao = new UserDaoImpl(connection);
			list = userDao.getAllUserByPage(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(connection, null, null);
		}
		return list;
	}
	
	@Override
	public int rowCount() {
		int rowPerPage = 0;
		try {
			connection=DataSourceUtil.getConnection();
			userDao = new UserDaoImpl(connection);
			rowPerPage = userDao.rowCount();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(connection, null, null);
		}
		return rowPerPage;
	}
	
	@Override
	public int deleteUserById(int userId) {
		int result = 0;
		try {
			connection=DataSourceUtil.getConnection();
			userDao = new UserDaoImpl(connection);
			result =userDao.deleteUserById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(connection, null, null);
		}
		return result;
	}
	
	@Override
	public int updateUser(User user) {
		int result = 0;
		try {
			connection=DataSourceUtil.getConnection();
			userDao = new UserDaoImpl(connection);
			result =userDao.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(connection, null, null);
		}
		return result;
	}
	
	@Override
	public User getById(int id) {
		User user = null;
		try {
			connection=DataSourceUtil.getConnection();
			userDao = new UserDaoImpl(connection);
			user = userDao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(connection, null, null);
		}
		return user;
	}
	@Override
	public int checkByName(String name) {
		int count = 0;
		try {
			connection=DataSourceUtil.getConnection();
			userDao = new UserDaoImpl(connection);
			count = userDao.checkByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(connection, null, null);
		}
		return count;
	}
	
	@Override
	public int addUser(User user) {
		int result =0;
		try {
			connection=DataSourceUtil.getConnection();
			userDao = new UserDaoImpl(connection);
			result =userDao.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(connection, null, null);
		}
		return result;
	}
}
