package bap.jp.thanhbn.web_mvc.service.cart;

import java.util.Collection;

import bap.jp.thanhbn.web_mvc.model.Cart;
import bap.jp.thanhbn.web_mvc.model.Product;

public interface CartService {
	void addToCart(Product product, int quantity);
	void removeProductToCart(int id);
	void clearCart();
	double getTotalAmount();
	Collection<Cart> getCartItems();
}
