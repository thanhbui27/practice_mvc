package bap.jp.thanhbn.web_mvc.service.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import bap.jp.thanhbn.web_mvc.model.Cart;
import bap.jp.thanhbn.web_mvc.model.Product;

@Service
public class CartServiceImpl implements CartService {
	
	private Map<Integer, Cart> cartItems  = new HashMap();

	@Override
	public void addToCart(Product product, int quantity) {
		 if (cartItems.containsKey(product.getProductID())) {
	            Cart item = cartItems.get(product.getProductID());
	            item.setQuantity(item.getQuantity() + quantity);
	        } else {
	            cartItems.put(product.getProductID(), new Cart(quantity,product));
	        }
		
	}

	@Override
	public void removeProductToCart(int id) {
		// TODO Auto-generated method stub
		cartItems.remove(id);
	}

	@Override
	public void clearCart() {
		 cartItems.clear();
		
	}
	 public double getTotalAmount() {
	        return cartItems.values().stream().mapToDouble(item -> item.getTotalPrice()).sum();
	 }

	@Override
	public Collection<Cart> getCartItems() {
		
		return cartItems.values();
	}

}
