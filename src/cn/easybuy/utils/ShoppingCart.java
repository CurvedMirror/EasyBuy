package cn.easybuy.utils;

import java.util.ArrayList;
import java.util.List;

import cn.easybuy.entity.Product;

public class ShoppingCart {
	private List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
	private double sum;

	public List<ShoppingCartItem> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCartItem> items) {
		this.items = items;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	//添加购物车
	public ReturnResult addItem(Product product, Integer quantity) {
		ReturnResult result = new ReturnResult();
		boolean flag = false;
		// 判断购物车是否已经有此商品，如果有则累计数量
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getProduct().getId() == product.getId()) {
				if (items.get(i).getQuantity() + quantity > product.getStock()) {
					return result.returnFail("商品数量不足");
				} else {
					items.get(i).setQuantity(items.get(i).getQuantity() + quantity);
					flag = true;
				}
			}
		}
		if (!flag) {
			items.add(new ShoppingCartItem(product, quantity));
		}
		return result.returnSuccess();
	}

	// 移除一项
	public void removeItem(int index) {
		items.remove(index);
	}

	// 修改数量
	public void modifQuantity(int index, Integer quantity) {
		items.get(index).setQuantity(quantity);
	}

}
