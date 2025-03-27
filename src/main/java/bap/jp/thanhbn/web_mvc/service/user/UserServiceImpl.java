package bap.jp.thanhbn.web_mvc.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bap.jp.thanhbn.web_mvc.model.User;
import bap.jp.thanhbn.web_mvc.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public void createUSer(User u) {
		// TODO Auto-generated method stub
		userRepository.save(u);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		 return userRepository.findByEmail(email);
	}

}
