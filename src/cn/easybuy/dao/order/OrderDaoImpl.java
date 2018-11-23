package cn.easybuy.dao.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.easybuy.dao.BaseDao;
import cn.easybuy.entity.Order;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.Pager;

public class OrderDaoImpl extends BaseDao implements OrderDao {

	
	public OrderDaoImpl(Connection conn) {
		super(conn);
	}
	public Order tableToClass(ResultSet rs) throws Exception {
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("userId"));
		order.setCreateTime(rs.getString("createTime"));
		order.setCost(rs.getFloat("cost"));
		order.setUserAddress(rs.getString("userAddress"));
		order.setSerialNumber(rs.getString("serialNumber"));
		order.setLoginName(rs.getString("loginName"));
		return order;
	}
	@Override
	public void saveOrder(Order order) throws Exception {
		int id = 0;
		String sql="insert into easybuy_order(userId,loginName,userAddress,createTime,cost,serialNumber) values(?,?,?,?,?,?) ";
		Object[] param=new Object[]{order.getUserId(),order.getLoginName(),order.getUserAddress(),new Date(),order.getCost(),order.getSerialNumber()};
		id=this.executeInsert(sql, param);
		order.setId(new Integer(id).intValue());
		DataSourceUtil.closeAll(null, pstmt, rs);
	}

	@Override
	public List<Order> queryOrderList(int userId,Pager pager)
			throws Exception {
		List<Order> list = new ArrayList<Order>();
		List<Object> paramsList=new ArrayList<Object>();   
		
		String sql = "SELECT id,userId,loginName,userAddress,createTime,cost,serialNumber FROM easybuy_order  WHERE 1=1 ";
		if(userId !=0){
			sql +=" and userId = ? ";
			paramsList.add(userId);
		}
		sql+=" ORDER BY createTime DESC  LIMIT ?,?";
		paramsList.add((pager.getCurrentPage()-1)*pager.getRowPerPage());
		paramsList.add(pager.getRowPerPage());
		
		rs = executeQuery(sql, paramsList.toArray());
		while(rs.next()){
			Order order = new Order();
			order = tableToClass(rs);
			list.add(order);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return list;
	}
	
	@Override
	public int getOrderCount(int userId) throws Exception {
		int result = 0;
		List<Object> params = new ArrayList<Object>();
		String sql = "SELECT COUNT(id) FROM easybuy_order WHERE 1=1 ";
		if(userId != 0 ){
			sql+=" and userid=?";
			params.add(userId);
		}
		rs = executeQuery(sql, params.toArray());
		while(rs.next()){
			result = rs.getInt(1);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return result;
	}
}
