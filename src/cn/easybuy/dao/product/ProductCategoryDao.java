package cn.easybuy.dao.product;

import java.sql.SQLException;
import java.util.List;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.utils.Pager;

public interface ProductCategoryDao {
	/**
	 * 查询所有商品分类
	 * @param parentId
	 * @return
	 * @throws SQLException
	 */
	List<ProductCategory> queryAllProductCategory(Pager pager) throws Exception;

	/**
	 * 根据id查询出父级的id
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	int getParentIdById(int id) throws Exception;
	
	/**
	 * 根据id查名字
	 * @param id
	 * @return
	 * @throws Exception
	 */
	String  getNameById(int id) throws Exception;
	/**
	 * 查询总商品数
	 * @return
	 */
	int getRowCount() throws Exception;
	/**
	 * 删除商品
	 */
	int delProductCategoryById(int id) throws Exception;
	/**
	 * 查询子集数量
	 * @return
	 */
	int querySubSetsSum(int id) throws Exception;
	
	/**
	 * 查询一级商品分类
	 * @return
	 */
	List<ProductCategory> queryCategory(int type) throws Exception;
	/**
	 * 根据父级id查询子集
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	List<ProductCategory> queryByParenId(int parentId) throws Exception;
	/**
	 * 添加商品分类
	 */
	int addProductCategory (ProductCategory productCategory) throws Exception;
	/**
	 * 根据id查询商品分类
	 * @param id
	 * @return
	 * @throws Exception
	 */
	ProductCategory getById(int id) throws Exception;
	/**
	 * 修改商品分类
	 * @param productCategory
	 * @return
	 * @throws Exception
	 */
	int updateProductCategory(ProductCategory productCategory) throws Exception;
}
