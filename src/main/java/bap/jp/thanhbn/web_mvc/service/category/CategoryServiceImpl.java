package bap.jp.thanhbn.web_mvc.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bap.jp.thanhbn.web_mvc.model.Category;
import bap.jp.thanhbn.web_mvc.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> findById(int id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id);
	}

	@Override
	public void createCategory(Category p) {
		// TODO Auto-generated method stub
		categoryRepository.save(p);
	}

	@Override
	public void updateCategory(Category p) {
		// TODO Auto-generated method stub
		categoryRepository.save(p);
	}

	@Override
	public void removeCategory(Category p) {
		// TODO Auto-generated method stub
		categoryRepository.delete(p);
	}

}
