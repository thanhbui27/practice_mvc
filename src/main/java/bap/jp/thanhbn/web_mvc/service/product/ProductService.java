package bap.jp.thanhbn.web_mvc.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.batch.item.Chunk;

import bap.jp.thanhbn.web_mvc.model.Order;
import bap.jp.thanhbn.web_mvc.model.Product;


public interface ProductService {
	List<Product> getAllProduct();
	List<Product> getAllProductNotBuy();
	Optional<Product> findById(int id);
}	
