package cn.easybuy.service.product;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.product.ProductCategoryDao;
import cn.easybuy.dao.product.ProductDao;
import cn.easybuy.dao.product.ProductDaoImpl;
import cn.easybuy.entity.Product;
import cn.easybuy.params.ProductParams;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.Pager;

public class ProductServiceImpl implements ProductService {

//	private Connection conn;
//	private ProductDao productDao;

	@Override
	public List<Product> queryProductList(ProductParams params) {
		Connection conn =null;
		ProductDao productDao;
		List<Product> pList = new ArrayList<Product>();
		try {
			conn = DataSourceUtil.getConnection();
			productDao = new ProductDaoImpl(conn);
			pList = productDao.queryProductList(params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeAll(conn, null, null);
		}
		return pList;
	}

	@Override
	public int queryProductCount(ProductParams params) {
		Connection conn =null;
		ProductDao productDao;
		Integer num = 0;
		try {
			conn = DataSourceUtil.getConnection();
			productDao = new ProductDaoImpl(conn);
			num = productDao.queryProductCount(params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeAll(conn, null, null);
		}
		return num;
	}

	@Override
	public List<Product> queryProductListByCategoryId(Integer categoryId) {
		Connection conn =null;
		ProductDao productDao;
		List<Product> pList = new ArrayList<Product>();
		try {
			conn = DataSourceUtil.getConnection();
			productDao = new ProductDaoImpl(conn);
			pList = productDao.queryProductListByCategoryId(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeAll(conn, null, null);
		}
		return pList;
	}
	
	@Override
	public Product getDetailById(Integer id) {
		Connection conn =null;
		ProductDao productDao;
		Product product = new Product();
		try {
			conn = DataSourceUtil.getConnection();
			productDao = new ProductDaoImpl(conn);
			product = productDao.getDetailById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return product;
	}
	@Override
	public List<Product> getAllProduct(Pager pager) {
		Connection conn =null;
		ProductDao productDao;
		List<Product> pList = new ArrayList<Product>();
		try {
			conn = DataSourceUtil.getConnection();
			productDao = new ProductDaoImpl(conn);
			pList = productDao.getAllProduct(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return pList;
	}
	
	@Override
	public int deleteById(int id) {
		Connection conn =null;
		ProductDao productDao;
		int result = 0;
		try {
			conn = DataSourceUtil.getConnection();
			productDao = new ProductDaoImpl(conn);
			result = productDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return result;
	}
	
	@Override
	public int addProduct(Product product) {
		Connection conn =null;
		ProductDao productDao;
		int result = 0;
		try {
			conn = DataSourceUtil.getConnection();
			productDao = new ProductDaoImpl(conn);
			result = productDao.addProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return result;
	}

	@Override
	public int updateProduct(Product product) {
		Connection conn =null;
		ProductDao productDao;
		int result = 0;
		try {
			conn = DataSourceUtil.getConnection();
			productDao = new ProductDaoImpl(conn);
			result = productDao.updateProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
		return result;
	}

	@Override
	public void updateStock(Integer id, Integer quantity) {
		Connection conn =null;
		ProductDao productDao;
		try {
			conn = DataSourceUtil.getConnection();
			productDao = new ProductDaoImpl(conn);
			productDao.updateStock(id, quantity);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtil.closeAll(conn, null, null);
		}
	}
	
}
