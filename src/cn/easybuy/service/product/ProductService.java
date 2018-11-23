package cn.easybuy.service.product;

import java.util.List;

import cn.easybuy.entity.Product;
import cn.easybuy.params.ProductParams;
import cn.easybuy.utils.Pager;

public interface ProductService {
	/**
	 * 根据条件查询商品列表
	 * @param params
	 * @return
	 */
	List<Product> queryProductList(ProductParams params);
	/**
	 * 根据条件查询商品数量
	 * @param params
	 * @return
	 */
	int queryProductCount(ProductParams params) ;
	/**
	 * 根据分类id查询
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	List<Product> queryProductListByCategoryId(Integer categoryId) ;
	/**
	 * 根据id查询商品详情
	 * @param id
	 * @return
	 */
	Product getDetailById(Integer id) ;
	/**
	 * 查询所有商品
	 * @return
	 * @throws Exception
	 */
	List<Product> getAllProduct(Pager pager);
	/**
	 * 根据id删除商品
	 * @param id
	 * @return
	 */
	int deleteById (int id);
	/**
	 * 新增商品
	 * @param product
	 * @return
	 * @throws Exception
	 */
	int addProduct(Product product);
	/**
	 * 修改商品
	 * @param product
	 * @return
	 * @throws Exception
	 */
	int updateProduct(Product product);
	/**
	 * 更新商品库存
	 * @param id
	 * @param quantity
	 */
	void updateStock(Integer id, Integer quantity);
}
