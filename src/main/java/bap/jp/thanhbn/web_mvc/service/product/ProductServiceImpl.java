package bap.jp.thanhbn.web_mvc.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bap.jp.thanhbn.web_mvc.model.Product;
import bap.jp.thanhbn.web_mvc.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(int id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id);
	}
	
	
	
}
