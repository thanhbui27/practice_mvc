package bap.jp.thanhbn.web_mvc.batch.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bap.jp.thanhbn.web_mvc.model.Product;
import bap.jp.thanhbn.web_mvc.service.product.ProductServiceImpl;

@Component
public class DeleteProductReader implements ItemReader<Product> {

	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	private int currentIndex = 0;
	
	private List<Product> products = new ArrayList<Product>();

	
	@Override
	public Product read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		
		if(productServiceImpl.getAllProductNotBuy().size() == 0) {
			products = null;
			currentIndex = 0;
			return null;
		}
				
		if(products.size() < productServiceImpl.getAllProductNotBuy().size()) {
			products = productServiceImpl.getAllProductNotBuy();
		}
			
		if(currentIndex < products.size()) {
			return products.get(currentIndex++);
		}
		
		return null;
		
	}

}
