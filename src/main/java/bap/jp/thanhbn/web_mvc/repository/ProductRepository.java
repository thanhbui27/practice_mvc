package bap.jp.thanhbn.web_mvc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bap.jp.thanhbn.web_mvc.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
