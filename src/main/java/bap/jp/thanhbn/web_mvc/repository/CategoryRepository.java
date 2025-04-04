package bap.jp.thanhbn.web_mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bap.jp.thanhbn.web_mvc.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
