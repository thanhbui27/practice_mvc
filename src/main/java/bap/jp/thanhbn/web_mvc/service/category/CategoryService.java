package bap.jp.thanhbn.web_mvc.service.category;

import java.util.List;
import java.util.Optional;

import bap.jp.thanhbn.web_mvc.model.Category;

public interface CategoryService {
	List<Category> getAllCategory();
	Optional<Category> findById(int id);
	void createCategory(Category p);
	void updateCategory(Category p);
	void removeCategory(Category p);
}
