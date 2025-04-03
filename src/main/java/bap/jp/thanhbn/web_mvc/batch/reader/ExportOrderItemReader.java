package bap.jp.thanhbn.web_mvc.batch.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bap.jp.thanhbn.web_mvc.model.Order;
import bap.jp.thanhbn.web_mvc.repository.OrderRepository;


@Component
public class ExportOrderItemReader implements ItemReader<Order> {

	@Autowired
	private OrderRepository orderRepository;
	
	private int currentIndex=0;
	
	private List<Order> orders = new ArrayList<Order>();
	
	@Override
	public Order read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
	
		
		if(orders.size() < orderRepository.findAll().size()) {
			orders = orderRepository.findAll();
		}
		
		if(currentIndex < orders.size()) {
			return orders.get(currentIndex++);
		}
		
		return null;
	}

	

}
