package bap.jp.thanhbn.web_mvc.service.user;

import java.util.List;
import java.util.Optional;

import bap.jp.thanhbn.web_mvc.model.User;

public interface UserService {
	User getCurrentUser();
	List<User> getAllUsers();
	void createUser(User u);
	void updateUser(User u);
	void removeUser(User u);
	Optional<User> findByEmail(String email); 
	Optional<User> findById(int id); 
}
