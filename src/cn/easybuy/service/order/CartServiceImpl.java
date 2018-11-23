package cn.easybuy.service.order;

import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.ShoppingCart;
import cn.easybuy.utils.ShoppingCartItem;

public class CartServiceImpl implements CartService {

	@Override
	public ShoppingCart modifyShoppingCart(String productId,
			String quantityStr, ShoppingCart cart) throws Exception {
		int quantity = EmptyUtils.isNotEmpty(quantityStr)?Integer.parseInt(quantityStr):0;
		
		for (ShoppingCartItem item : cart.getItems()) {
			if (item.getProduct().getId()==Integer.parseInt(productId)) {
				if (quantity==0) {
					cart.getItems().remove(item);
					break;
				}else {
					item.setQuantity(quantity);
				}
			}
		}
		 //重新计算金额
        calculate(cart);
		return cart;
	}

	public ShoppingCart calculate(ShoppingCart cart) throws Exception{
		double sum =0.0;
		for (ShoppingCartItem item : cart.getItems()) {
			sum += item.getQuantity() * item.getProduct().getPrice();
			item.setCost(item.getQuantity() * item.getProduct().getPrice());
		}
		cart.setSum(sum);
		return cart;
	}
	

}
