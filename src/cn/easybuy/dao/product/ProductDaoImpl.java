package cn.easybuy.dao.product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import cn.easybuy.dao.BaseDao;
import cn.easybuy.entity.Product;
import cn.easybuy.params.ProductParams;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.Pager;

public class ProductDaoImpl extends BaseDao implements ProductDao {
	
	public ProductDaoImpl(Connection conn) {
		super(conn);
	}

	public Product tableToClass(ResultSet rs) throws Exception {
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setDescription(rs.getString("description"));
		product.setPrice(rs.getFloat("price"));
		product.setStock(rs.getInt("stock"));
		product.setCategoryLevel1Id(rs.getInt("categoryLevel1Id"));
		product.setCategoryLevel2Id(rs.getInt("categoryLevel2Id"));
		product.setCategoryLevel3Id(rs.getInt("categoryLevel3Id"));
		product.setFileName(rs.getString("fileName"));
		return product;
	}
	@Override
	public List<Product> queryProductList(ProductParams params) throws Exception{
		List<Product> pList  =new ArrayList<Product>();
		StringBuffer sql=new StringBuffer("SELECT * FROM `easybuy_product` WHERE 1=1 ");
		ResultSet rs=null;
		if (EmptyUtils.isNotEmpty(params.getKeyWords())) {
			sql.append(" and name like '%"+params.getKeyWords()+"%'");
		}
		if (EmptyUtils.isNotEmpty(params.getCategoryId())) {
			sql.append(" and categoryLevel1Id="+params.getCategoryId()+" or categoryLevel2Id="+params.getCategoryId()+" or categoryLevel3Id="+params.getCategoryId()+"");
		}
		if (EmptyUtils.isNotEmpty(params.getSort())) {
			sql.append(" order by "+params.getSort());
		}
		if (EmptyUtils.isNotEmpty(params.isPage())) {
			sql.append(" limit "+params.getStartIndex()+","+params.getPageSize());
		}
		rs= executeQuery(sql.toString());
		while(rs.next()){
			Product product =tableToClass(rs);
			pList.add(product);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return pList;
	}

	@Override
	public int queryProductCount(ProductParams params) throws Exception{
		Integer num=0;
		StringBuffer sql=new StringBuffer("SELECT count(*) FROM `easybuy_product` WHERE 1=1 ");
		ResultSet rs=null;
		if (params!=null) {
			if (EmptyUtils.isNotEmpty(params.getKeyWords())) {
				sql.append(" and name like '%"+params.getKeyWords()+"%'");
			}
			if (EmptyUtils.isNotEmpty(params.getCategoryId())) {
				sql.append(" and (categoryLevel1Id)="+params.getCategoryId()+" or categoryLevel2Id="+params.getCategoryId()+" or categoryLevel3Id="+params.getCategoryId()+"");
			}
		}
		
		rs= executeQuery(sql.toString());
		while(rs.next()){
			num=rs.getInt(1);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return num;
	}
	
	@Override
	public List<Product> queryProductListByCategoryId(Integer categoryId) throws Exception {
		List<Product> pList  =new ArrayList<Product>();
		StringBuffer sql=new StringBuffer("SELECT * FROM `easybuy_product` WHERE 1=1 ");
		ResultSet rs=null;
		
		if (EmptyUtils.isNotEmpty(categoryId)) {
			sql.append(" and (categoryLevel1Id)="+categoryId+" or categoryLevel2Id="+categoryId+" or categoryLevel3Id="+categoryId+"");
		}
		
		rs= executeQuery(sql.toString());
		while(rs.next()){
			Product product =tableToClass(rs);
			pList.add(product);
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return pList;
	}
	
	@Override
	public Product getDetailById(Integer id) throws Exception{
		Product product = null;
		String sql = "SELECT * FROM `easybuy_product` WHERE id=?";
		rs=executeQuery(sql, id);
		while(rs.next()){
			product=new Product();
			product = tableToClass(rs);
			
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return product;
	}
	@Override
	public List<Product> getAllProduct(Pager pager) throws Exception {
		List<Product> list  =new ArrayList<Product>();
		String sql = "SELECT * FROM `easybuy_product` ";
		if (pager !=null) {
			sql +=" limit ?,?";
			rs = executeQuery(sql.toString(),(pager.getCurrentPage()-1)*pager.getRowPerPage(),pager.getRowPerPage());
		}else{
			rs=executeQuery(sql);
		}
		while(rs.next()){
			Product product=new Product();
			product = tableToClass(rs);
			list.add(product);
			
		}
		DataSourceUtil.closeAll(null, pstmt, rs);
		return list;
	}

	@Override
	public int deleteById(int id) throws Exception {
		String sql ="DELETE FROM `easybuy_product` WHERE id=?";
		Object[] params = {id};
		int result = executeUpdate(sql, params);
		DataSourceUtil.closeAll(null, pstmt, rs);
		return result;
	}
	
	@Override
	public void updateStock(Integer id, Integer quantity) {
		String sql = " update easybuy_product set stock=? where id=? ";
		Object[] params ={quantity,id};
		executeUpdate(sql, params);
		DataSourceUtil.closeAll(null, pstmt, rs);
	}
	
	@Override
	public int addProduct(Product product) throws Exception {
		int result = 0;
		String sql = "INSERT INTO `easybuy_product` (`name`, `price`,`stock`, `categoryLevel1Id`, `categoryLevel2Id`, `categoryLevel3Id`,`fileName`,description)" +
				" VALUES (?,?,?,?,?,?,?,?);";
		Object[] params ={product.getName(),product.getPrice(),product.getStock(),product.getCategoryLevel1Id(),product.getCategoryLevel2Id(),product.getCategoryLevel3Id(),product.getFileName(),product.getDescription()};
		result = executeInsert(sql, params);
		DataSourceUtil.closeAll(null, pstmt, rs);
		return result;
	}
	
	@Override
	public int updateProduct(Product product) throws Exception {
		int result = 0;
    	String sql = " update easybuy_product set name=?,fileName=?,categoryLevel1Id=?,categoryLevel3Id=?,categoryLevel3Id=? where id=? ";
    	Object[] params = new Object[] {product.getName(),product.getFileName(),product.getCategoryLevel1Id(),product.getCategoryLevel2Id(),product.getCategoryLevel3Id(),product.getId()};
    	result = executeUpdate(sql, params);
    	DataSourceUtil.closeAll(null, pstmt, rs);
		return result;
	}
}
