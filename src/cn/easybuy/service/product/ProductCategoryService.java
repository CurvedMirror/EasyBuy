package cn.easybuy.service.product;

import java.util.List;

import cn.easybuy.entity.ProductCategory;
import cn.easybuy.params.ProductCategoryParam;
import cn.easybuy.utils.Pager;

public interface ProductCategoryService {
	/**
	 * 根据ID查询所有商品分类
	 * @param parentId
	 * @return
	 */
	List<ProductCategory> queryAllProductCategory(Pager pager);
	/**
	 * 根据id查询出父级的id
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	int getParentIdById(int id);
	/**
	 * 根据id查名字
	 * @param id
	 * @return
	 * @throws Exception
	 */
	String  getNameById(int id);
	/**
	 * 查询总商品数
	 * @return
	 */
	int getRowCount() ;
	/**
	 * 删除商品
	 */
	int delProductCategoryById(int id);
	/**
	 * 查询子集数量
	 * @return
	 */
	int querySubSetsSum(int id);
	/**
	 * 根据类型查询商品分类
	 * @return
	 */
	List<ProductCategory> queryCategory(int type);
	/**
	 * 根据父级id查询子集
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	List<ProductCategory> queryByParenId(int parentId);
	
	/**
	 * 添加商品分类
	 */
	int addProductCategory (ProductCategory productCategory);
	/**
	 * 根据id查询商品分类
	 * @param id
	 * @return
	 * @throws Exception
	 */
	ProductCategory getById(int id);
	/**
	 * 修改商品分类
	 * @param productCategory
	 * @return
	 * @throws Exception
	 */
	int updateProductCategory(ProductCategory productCategory);
}
