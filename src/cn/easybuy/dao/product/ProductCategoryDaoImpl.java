package cn.easybuy.dao.product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;



import cn.easybuy.dao.BaseDao;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.Pager;

public class ProductCategoryDaoImpl extends BaseDao implements
		ProductCategoryDao {
	public ProductCategoryDaoImpl(Connection conn) {
		super(conn);
	}

	public ProductCategory tableToClass(ResultSet rs) throws Exception{
		ProductCategory productCategory = new ProductCategory();
		productCategory.setId(rs.getInt("id"));
		productCategory.setName(rs.getString("name"));
		productCategory.setParentId(rs.getInt("parentId"));
		productCategory.setType(rs.getInt("type"));
		productCategory.setIconClass(rs.getString("iconClass"));
		return productCategory;
	}
	@Override
	public List<ProductCategory> queryAllProductCategory(Pager pager)
			throws Exception {
		List<ProductCategory> list = new ArrayList<ProductCategory>();
		StringBuffer sql = new StringBuffer(
				"SELECT id,NAME,parentId,TYPE,iconClass FROM easybuy_product_category WHERE 1=1 ");
		if (pager!=null) {
			sql.append(" limit ?,?");
			rs = executeQuery(sql.toString(),(pager.getCurrentPage()-1)*pager.getRowPerPage(),pager.getRowPerPage());
		}else{
			rs = executeQuery(sql.toString());
		}
		while (rs.next()) {
			ProductCategory productCategory = new ProductCategory();
			productCategory = tableToClass(rs);
			list.add(productCategory);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return list;
	}
	
	@Override
	public int getParentIdById(int id) throws Exception {
		int parentId = 0;
		String sql = "SELECT parentId FROM `easybuy_product_category` WHERE id=? ";
		rs = executeQuery(sql, id);
		while(rs.next()){
			parentId = rs.getInt(1);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return parentId;
	}
	
	@Override
	public String getNameById(int id) throws Exception {
		String name = "";
		String sql = "SELECT NAME FROM `easybuy_product_category` WHERE id=?";
		rs = executeQuery(sql, id);
		while(rs.next()){
			name = rs.getString(1);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return name;
	}
	@Override
	public int getRowCount() throws Exception {
		int rowCount = 0 ;
		String sql = "SELECT COUNT(*) FROM `easybuy_product_category`";
		rs =executeQuery(sql);
		while(rs.next()){
			rowCount= rs.getInt(1);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return rowCount;
	}
	
	@Override
	public int delProductCategoryById(int id) throws Exception{
		String sql = "DELETE FROM `easybuy_product_category` WHERE id= ?";
		Object[] params ={id};
		int result = executeUpdate(sql, params);
		DataSourceUtil.closeAll(null, pstmt, rs);
		return result;
	}
	
	@Override
	public int querySubSetsSum(int id) throws Exception {
		int subSetsSum =0;
		String sql = "SELECT COUNT(*) FROM `easybuy_product_category` WHERE parentId=?";
		rs =executeQuery(sql, id);
		while(rs.next()){
			subSetsSum = rs.getInt(1);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return subSetsSum;
	}
	
	@Override
	public List<ProductCategory> queryCategory(int type) throws Exception{
		List<ProductCategory> list = new ArrayList<ProductCategory>();
		String sql = "SELECT * FROM `easybuy_product_category` WHERE TYPE="+type;
		rs = executeQuery(sql);
		while(rs.next()){
			ProductCategory productCategory = new ProductCategory();
			productCategory = tableToClass(rs);
			list.add(productCategory);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return list;
	}
	
	@Override
	public List<ProductCategory> queryByParenId(int parentId) throws Exception {
		List<ProductCategory> list = new ArrayList<ProductCategory>();
		String sql ="SELECT * FROM `easybuy_product_category` WHERE parentId = ?";
		Object[] params ={parentId};
		rs = executeQuery(sql, params);
		while(rs.next()){
			ProductCategory productCategory = new ProductCategory();
			productCategory = tableToClass(rs);
			list.add(productCategory);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return list;
	}
	@Override
	public int addProductCategory(ProductCategory productCategory)
			throws Exception {
		int result  = 0 ; 
		String sql = "INSERT INTO `easybuy_product_category`(`name`,parentId,`type`,iconClass)" +
				" values (?,?,?,?)";
		Object[] params ={productCategory.getName(),productCategory.getParentId(),productCategory.getType(),productCategory.getIconClass()};
		result = executeUpdate(sql, params);
		DataSourceUtil.closeAll(null, pstmt, rs);
		return result;
	}
	@Override
	public ProductCategory getById(int id) throws Exception {
		ProductCategory productCategory = null;
		String sql="SELECT id,name,parentId,type,iconClass  FROM easybuy_product_category where id = ? ";
		rs = executeQuery(sql, id);
		while(rs.next()){
			productCategory = tableToClass(rs);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return productCategory;
	}
	@Override
	public int updateProductCategory(ProductCategory productCategory)
			throws Exception {
		int result = 0;
    	String sql = " UPDATE easybuy_product_category SET name=?,parentId=?,type=?,iconClass=? WHERE id =?  ";
    	Object[] params = new Object[] {productCategory.getName(),productCategory.getParentId(),productCategory.getType(),productCategory.getIconClass(),productCategory.getId()};
    	result  =executeUpdate(sql, params);
    	DataSourceUtil.closeAll(null, pstmt, rs);
		return result;
	}
	
}
