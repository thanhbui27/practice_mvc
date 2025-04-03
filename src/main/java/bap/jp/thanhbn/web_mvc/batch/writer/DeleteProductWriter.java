package bap.jp.thanhbn.web_mvc.batch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bap.jp.thanhbn.web_mvc.model.Product;
import bap.jp.thanhbn.web_mvc.repository.ProductRepository;

@Component
public class DeleteProductWriter implements ItemWriter<Product> {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void write(Chunk<? extends Product> chunk) throws Exception {
		// TODO Auto-generated method stub
		productRepository.deleteAll(chunk);
	}

}
