package bap.jp.thanhbn.web_mvc.batch.processor;

import java.math.BigDecimal;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import bap.jp.thanhbn.web_mvc.model.Product;

@Component
public class ImportProductProcess implements ItemProcessor<Product, Product> {

	@Override
	public Product process(Product item) throws Exception {
		// TODO Auto-generated method stub
		
		return item;
		
		
	}
	
	

}
