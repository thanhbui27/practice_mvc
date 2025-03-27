package bap.jp.thanhbn.web_mvc.model;

import java.math.BigDecimal;

public class Cart {
	private Product product;
	private int quantity;
	
	public Cart(int quantity, Product p) {
		this.product = p;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return this.quantity * this.product.getPrice().toBigInteger().intValueExact();
	}
}
