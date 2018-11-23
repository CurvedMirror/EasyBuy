package cn.easybuy.service.order;

import cn.easybuy.utils.ShoppingCart;

public interface CartService {
	/**
	 * 修改和删除
	 * @param productId
	 * @param quantityStr
	 * @param cart
	 * @return
	 * @throws Exception
	 */
    public ShoppingCart modifyShoppingCart(String productId,String quantityStr,ShoppingCart cart) throws Exception;

}
