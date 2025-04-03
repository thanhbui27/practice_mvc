package bap.jp.thanhbn.web_mvc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bap.jp.thanhbn.web_mvc.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email")String email);
	
	@Query(value = "select u.user_id,user_name, u.email, u.password \r\n"
			+ "from users u\r\n"
			+ "left join\r\n"
			+ "(select  user_id\r\n"
			+ "from orders \r\n"
			+ "where month(order_date) = month(current_date())\r\n"
			+ "group by user_id)  o\r\n"
			+ "on u.user_id = o.user_id\r\n"
			+ "where o.user_id is null\r\n"
			+ "",nativeQuery = true)
	List<User> getAllUserNotBuyProduct();
}
