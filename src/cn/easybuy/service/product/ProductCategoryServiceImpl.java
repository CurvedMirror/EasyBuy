package cn.easybuy.service.product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.product.ProductCategoryDao;
import cn.easybuy.dao.product.ProductCategoryDaoImpl;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.params.ProductCategoryParam;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.Pager;

public class ProductCategoryServiceImpl implements ProductCategoryService {
//	private Connection conn ;
//	private ProductCategoryDao productCategoryDao ;
	@Override
	public List<ProductCategory> queryAllProductCategory(Pager pager) {
		 Connection conn =null;
		 ProductCategoryDao productCategoryDao ;
		
		conn = DataSourceUtil.getConnection();// 获取连接
		productCategoryDao = new ProductCategoryDaoImpl(conn);
		
		List <ProductCategory> list=null;
		try {
			list=productCategoryDao.queryAllProductCategory(pager);//查询所有商品分类
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return list;
	}
	
	@Override
	public int getParentIdById(int id) {
		Connection conn =null;
		 ProductCategoryDao productCategoryDao ;
		int parentId = 0;
		try{
			conn = DataSourceUtil.getConnection();// 获取连接
			productCategoryDao = new ProductCategoryDaoImpl(conn);
			parentId = productCategoryDao.getParentIdById(id);
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return parentId;
	}
	
	@Override
	public String getNameById(int id) {
		Connection conn =null;
		 ProductCategoryDao productCategoryDao ;
		String name = "";
		try{
			conn = DataSourceUtil.getConnection();// 获取连接
			productCategoryDao = new ProductCategoryDaoImpl(conn);
			name = productCategoryDao.getNameById(id);
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return name;
	}
	
	@Override
	public int getRowCount() {
		Connection conn =null;
		 ProductCategoryDao productCategoryDao ;
		int rowCount = 0;
		try{
			conn = DataSourceUtil.getConnection();// 获取连接
			productCategoryDao = new ProductCategoryDaoImpl(conn);
			rowCount = productCategoryDao.getRowCount();
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return rowCount;
	}
	
	@Override
	public int delProductCategoryById(int id) {
		Connection conn =null;
		 ProductCategoryDao productCategoryDao ;
		int result = 0;
		try{
			conn = DataSourceUtil.getConnection();// 获取连接
			productCategoryDao = new ProductCategoryDaoImpl(conn);
			result = productCategoryDao.delProductCategoryById(id);
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return result;
	}
	
	@Override
	public int querySubSetsSum(int id) {
		Connection conn =null;
		 ProductCategoryDao productCategoryDao ;
		int result = 0;
		try{
			conn = DataSourceUtil.getConnection();// 获取连接
			productCategoryDao = new ProductCategoryDaoImpl(conn);
			result = productCategoryDao.querySubSetsSum(id);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return result;
	}
	
	@Override
	public List<ProductCategory> queryCategory(int type) {
		Connection conn =null;
		 ProductCategoryDao productCategoryDao ;
		List<ProductCategory> list = null;
		try{
			conn = DataSourceUtil.getConnection();// 获取连接
			productCategoryDao = new ProductCategoryDaoImpl(conn);
			list = productCategoryDao.queryCategory(type);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return list;
	}
	
	@Override
	public List<ProductCategory> queryByParenId(int parentId) {
		Connection conn =null;
		 ProductCategoryDao productCategoryDao ;
		List<ProductCategory> list = null;
		try{
			conn = DataSourceUtil.getConnection();// 获取连接
			productCategoryDao = new ProductCategoryDaoImpl(conn);
			list = productCategoryDao.queryByParenId(parentId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return list;
	}

	@Override
	public int addProductCategory(ProductCategory productCategory) {
		Connection conn =null;
		 ProductCategoryDao productCategoryDao ;
		int  result = 0;
		try{
			conn = DataSourceUtil.getConnection();// 获取连接
			productCategoryDao = new ProductCategoryDaoImpl(conn);
			result = productCategoryDao.addProductCategory(productCategory);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return result;
	}

	@Override
	public ProductCategory getById(int id) {
		Connection conn =null;
		 ProductCategoryDao productCategoryDao ;
		ProductCategory productCategory = null;
		try{
//			synchronized (this) {
				conn = DataSourceUtil.getConnection();// 获取连接
				productCategoryDao = new ProductCategoryDaoImpl(conn);
				productCategory = productCategoryDao.getById(id);
//			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return productCategory;
	}
	
	@Override
	public int updateProductCategory(ProductCategory productCategory) {
		Connection conn =null;
		 ProductCategoryDao productCategoryDao ;
		int  result = 0;
		try{
			conn = DataSourceUtil.getConnection();// 获取连接
			productCategoryDao = new ProductCategoryDaoImpl(conn);
			result = productCategoryDao.updateProductCategory(productCategory);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return result;
	}
}
