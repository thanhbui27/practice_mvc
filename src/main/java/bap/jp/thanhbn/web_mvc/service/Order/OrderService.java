package bap.jp.thanhbn.web_mvc.service.Order;

import java.util.List;
import java.util.Optional;

import bap.jp.thanhbn.web_mvc.model.Order;

public interface OrderService {
	List<Order> findByUserId(int id);
	Optional<Order> getOrderById(int id);
	void deleteOrder(int id);
	void saveOrder(Order order);

}
