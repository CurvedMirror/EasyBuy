package cn.easybuy.service.user;

import java.util.List;

import cn.easybuy.entity.User;
import cn.easybuy.utils.Pager;

public interface UserService {
	/**
	 * 根据用户账号获取用户记录
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	User getUserByLoginName(String loginName);
	/**
	 * 用户注册
	 * @return
	 */
	public boolean save(User user);
	/**
	 * 分页查询所有信息
	 * @return
	 */
	List<User> getAllUserByPage(Pager pager);
	/**
	 * 查询总记录数
	 * @return
	 * @throws Exception
	 */
	int rowCount();
	/**
	 * 根据userId删除用户
	 * @param userId
	 * @return
	 */
	int deleteUserById(int userId);
	/**
	 * 更新用户信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	int updateUser(User user);
	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	User getById(int id);
	/**
	 * 判断用户是否重名
	 * @param name
	 * @return
	 * @throws Exception
	 */
	int checkByName(String name);
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	int addUser(User user);
}
