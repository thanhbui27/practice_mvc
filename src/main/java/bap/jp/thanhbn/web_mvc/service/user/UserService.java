package bap.jp.thanhbn.web_mvc.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.batch.item.Chunk;

import bap.jp.thanhbn.web_mvc.model.User;

public interface UserService {
	List<User> getAllUsers();
	List<User> getAllUserNotBuyProduct();
	void createUSer(User u);
	Optional<User> findByEmail(String email); 
	
}
