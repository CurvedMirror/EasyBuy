package cn.easybuy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;


public class BaseDao {
	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	protected Connection conn;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;

	public BaseDao(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 执行增、删,改语句
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数列表
	 * @return
	 */
//	public int executeUpdate(String sql, Object... params) {
//
//		int result = 0;
//		try {
//			pstmt = conn.prepareStatement(sql);
//
//			// 赋值参数
//			if (params != null && params.length != 0) {
//				for (int i = 0; i < params.length; i++) {
//					pstmt.setObject(i + 1, params[i]);
//				}
//			}
//			result = pstmt.executeUpdate();
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
//		return result;
//	}

	// 4.查询通用方法
	public ResultSet executeQuery(String sql, Object... params) {


		try {
			pstmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
	
	/**
	 * 新增记录 返回id值
	 * @param sql
	 * @param params
	 * @return
	 */
	public int executeInsert(String sql, Object[] params) {

		Long id=0L;
		try {
			pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
			pstmt.executeUpdate();
			ResultSet rs=pstmt.getGeneratedKeys();
			if (rs.next()) {
				id=rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			id=null;
		}
		return id.intValue();
	}

	public int executeUpdate(String sql,Object[] params){
		int updateRows =0;
		try {
			pstmt=conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i+1, params[i]);
			}
			updateRows=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			updateRows=-1;
		}
		return updateRows;
		
	}
}
