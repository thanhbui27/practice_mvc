package bap.jp.thanhbn.web_mvc.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import bap.jp.thanhbn.web_mvc.model.Order;

@Component
public class ExportOrderItemProcessor implements ItemProcessor<Order, Order> {

	@Override
	public Order process(Order item) throws Exception {
		// TODO Auto-generated method stub
		
		return item;
	}

}
