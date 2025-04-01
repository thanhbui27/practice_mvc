package bap.jp.thanhbn.web_mvc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bap.jp.thanhbn.web_mvc.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByUser_userID(int userId);
	
	
	//Bài 3.2  lấy ra tổng giá trị của của order trong tháng
	@Query(value = "select \r\n"
			+ "case \r\n"
			+ "	   when sum(total_amount) = 0 then 0\r\n"
			+ "    when sum(total_amount) > 0 then sum(total_amount)\r\n"
			+ "end as total_amount\r\n"
			+ "from orders \r\n"
			+ "where month(CURRENT_DATE()) = month(order_date)",
			nativeQuery = true)
	Optional<Integer> getTotalAmountInMonth();
}