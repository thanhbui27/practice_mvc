package bap.jp.thanhbn.web_mvc.service.product;

import java.util.List;
import java.util.Optional;

import bap.jp.thanhbn.web_mvc.model.Order;
import bap.jp.thanhbn.web_mvc.model.Product;


public interface ProductService {
	List<Product> getAllProduct();
	Optional<Product> findById(int id);
}
