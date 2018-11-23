package cn.easybuy.dao.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.BaseDao;
import cn.easybuy.entity.User;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.Pager;

public class UserDaoImpl extends BaseDao implements  UserDao {

	public UserDaoImpl(Connection conn) {
		super(conn);
	}
	
	/**
	 * 给user赋值的方法
	 * @param rs
	 * @return user对象
	 * @throws Exception
	 */
	public User tableToClass(ResultSet rs) throws Exception {
		User user = new User();
		user.setLoginName(rs.getString("loginName"));
		user.setUserName(rs.getString("userName"));
		user.setPassword(rs.getString("password"));
		user.setSex(rs.getInt("sex"));
		user.setIdentityCode(rs.getString("identityCode"));
		user.setEmail(rs.getString("email"));
		user.setMobile(rs.getString("mobile"));
		user.setType(rs.getInt("type"));
		user.setId(rs.getInt("id"));
		return user;
	}
	
	@Override
	public User getUserByLoginName(String loginName) throws Exception{
		User user = null;
		StringBuffer sql= new StringBuffer("SELECT * FROM `easybuy_user` WHERE 1=1");
	
		if (!EmptyUtils.isEmpty(loginName)) {
			sql.append(" and loginName = ?");
		}
		ResultSet rs=executeQuery(sql.toString(),loginName);
		while(rs.next()){
			user = tableToClass(rs);
		}
		return user;
	}
	@Override
	public int save(User user) {
		Integer id=0;
		StringBuffer sql=new StringBuffer("INSERT INTO `easybuy_user` (loginName,userName,`password`,sex,identityCode,email,mobile) VALUES(?,?,?,?,?,?,?)");
		Object[] params =new Object[]{user.getLoginName(),user.getUserName(),user.getPassword(),user.getSex(),user.getIdentityCode(),user.getEmail(),user.getMobile()};
		id=super.executeInsert(sql.toString(),params);
		return id;
	}
	
	@Override
	public List<User> getAllUserByPage(Pager pager) throws Exception {
		List<User> list =new ArrayList<User>();
		String sql = "SELECT * FROM `easybuy_user`  LIMIT ?,? ";
		ResultSet rs=executeQuery(sql, (pager.getCurrentPage()-1)*pager.getRowPerPage(),pager.getRowPerPage());
		while(rs.next()){
			User user = new User();
			user = tableToClass(rs);
			list.add(user);
		}
		return list;
	}
	@Override
	public int rowCount() throws Exception {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM `easybuy_user` ";
		ResultSet rs = executeQuery(sql);
		while(rs.next()){
			result = rs.getInt(1);
		}
		return result;
	}
	
	@Override
	public int deleteUserById(int userId) throws Exception {
		String sql = "DELETE FROM `easybuy_user` WHERE id = ?";
		Object[] params = {userId};
		int result = executeUpdate(sql, params);
		return result;
	}
	
	@Override
	public int updateUser(User user) throws Exception {
		String sql = "UPDATE  `easybuy_user` SET loginName=?,userName=?,identityCode=?,email=?,mobile=?,type=? WHERE id =?";
		Object[] params ={user.getLoginName(),user.getUserName(),user.getIdentityCode(),user.getEmail(),user.getMobile(),user.getType(),user.getId()};
		int result = executeUpdate(sql, params);
		return result;
	}
	
	@Override
	public User getById(int id) throws Exception {
		User user = null;
		String sql ="SELECT * FROM `easybuy_user` WHERE id=?";
		ResultSet rs=executeQuery(sql,id);
		while(rs.next()){
			user = new User();
			user= tableToClass(rs);
		}
		return user;
	}
	
	@Override
	public int checkByName(String name) throws Exception {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM `easybuy_user` WHERE loginName=?";
		ResultSet rs = executeQuery(sql, name);
		while(rs.next()){
			count =rs.getInt(1);
		}
		return count;
	}
	
	@Override
	public int addUser(User user) {
		String sql ="INSERT INTO `easybuy`.`easybuy_user` (`loginName`, `userName`, `password`, `identityCode`, `email`, `mobile`,`type`)" +
				" VALUES (?,?,?,?,?,?,?); ";
		Object[] params = {user.getLoginName(),user.getLoginName(),user.getPassword(),user.getIdentityCode(),user.getEmail(),user.getMobile(),user.getType()}; 
		int result = executeInsert(sql, params);
		return result;
	}
}
