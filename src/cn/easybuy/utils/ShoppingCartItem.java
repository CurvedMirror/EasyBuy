package cn.easybuy.utils;

import java.io.Serializable;

import cn.easybuy.entity.Product;

public class ShoppingCartItem implements Serializable {
	private Product product;// 商品
	private Integer quantity;// 商品数量
	private double cost;// 价格

	public ShoppingCartItem(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
		this.cost = product.getPrice() * quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double d) {
		this.cost = d;
	}

}
