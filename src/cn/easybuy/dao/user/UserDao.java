package cn.easybuy.dao.user;

import java.util.List;

import cn.easybuy.entity.User;
import cn.easybuy.utils.Pager;

public interface UserDao {
	/**
	 * 根据用户账号获取用户记录
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	User getUserByLoginName(String loginName) throws Exception ;
	/**
	 * 用户注册
	 * @return
	 */
	public int  save(User user);
	/**
	 * 分页查询所有信息
	 * @return
	 */
	List<User> getAllUserByPage(Pager pager)  throws Exception;
	/**
	 * 查询总记录数
	 * @return
	 * @throws Exception
	 */
	int rowCount() throws Exception;
	/**
	 * 根据userId删除用户
	 * @param userId
	 * @return
	 */
	int deleteUserById(int userId) throws Exception;
	/**
	 * 更新用户信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	int updateUser(User user) throws Exception;
	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	User getById(int id) throws Exception;
	/**
	 * 判断用户是否重名
	 * @param name
	 * @return
	 * @throws Exception
	 */
	int checkByName(String name) throws Exception;
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	int addUser(User user) throws Exception;
}
