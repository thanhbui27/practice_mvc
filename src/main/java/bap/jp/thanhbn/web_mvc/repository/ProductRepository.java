package bap.jp.thanhbn.web_mvc.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bap.jp.thanhbn.web_mvc.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query(value = "select p.product_id, p.product_name,p.price, p.category_id from products p\r\n"
			+ "left join (select oi.product_id\r\n"
			+ "from order_items oi \r\n"
			+ "join (select order_id from orders \r\n"
			+ " where month(current_date()) = month(order_date)) o\r\n"
			+ " on o.order_id = oi.order_id \r\n"
			+ " group by oi.product_id\r\n"
			+ " ) oo\r\n"
			+ " on oo.product_id = p.product_id \r\n"
			+ " where oo.product_id is null",nativeQuery = true)
	List<Product> getAllProductNotBuy();
}
