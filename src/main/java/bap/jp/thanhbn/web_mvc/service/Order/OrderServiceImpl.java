package bap.jp.thanhbn.web_mvc.service.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bap.jp.thanhbn.web_mvc.model.Order;
import bap.jp.thanhbn.web_mvc.model.User;
import bap.jp.thanhbn.web_mvc.repository.OrderRepository;
import jakarta.transaction.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrdersByUser(int id) {
        return orderRepository.findByUser_userID(id);
    }

    public Optional<Order> getOrderById(int id) {
        return orderRepository.findById(id);
    }
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    
    @Transactional
    public void saveOrder(Order order) {
    	try {
    		order.setOrderDate(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC));
            orderRepository.save(order);
    	}catch(Exception e) {
    		throw new RuntimeException("Fail to create order, order will rollback");
    	}
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
