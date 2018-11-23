package cn.easybuy.dao.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.easybuy.dao.BaseDao;
import cn.easybuy.entity.UserAddress;
import cn.easybuy.utils.DataSourceUtil;

public class UserAddressDaoImpl  extends BaseDao implements UserAddressDao{

	public UserAddressDaoImpl(Connection conn) {
		super(conn);
	}

	public UserAddress tableToClass(ResultSet rs) throws Exception {
		UserAddress userAddress = new UserAddress();
		userAddress.setId(rs.getInt("id"));
		userAddress.setAddress(rs.getString("address"));
		userAddress.setUserId(rs.getInt("userId"));
		userAddress.setCreateTime(rs.getString("createTime"));
		userAddress.setRemark(rs.getString("remark"));
		return userAddress;
	}
	@Override
	public List<UserAddress> queryUserAddressList(int userId) throws Exception{
		List<UserAddress> list =  new ArrayList<UserAddress>();
		String sql = "SELECT id,userId,address,createTime,isDefault,remark FROM easybuy_user_address  WHERE userId =?";
		rs = executeQuery(sql,userId);
		while(rs.next()){
			UserAddress userAddress = new UserAddress();
			userAddress = tableToClass(rs);
			list.add(userAddress);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return list;
	}

	@Override
	public int addUserAddress(UserAddress userAddress) throws Exception {
		int id = 0;
		String sql = " INSERT into easybuy_user_address(userId,address,createTime,isDefault,remark) VALUES(?,?,?,?,?) ";
		Object param[]=new Object[]{userAddress.getUserId(),userAddress.getAddress(),new Date(),userAddress.getIsDefault(),userAddress.getRemark()};
        id=this.executeInsert(sql,param);
        userAddress.setId(id);
        DataSourceUtil.closeAll(null,null, rs);
		return id;
	}
	
	@Override
	public UserAddress getAddressById(int id) throws Exception{
		UserAddress userAddress  = new UserAddress();
		String sql = "select id,userId,address,createTime,isDefault,remark from easybuy_user_address  where id=? ";
		Object[] params = {id};
		rs = executeQuery(sql, params);
		while(rs.next()){
			userAddress = tableToClass(rs);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return userAddress;
	}
}
