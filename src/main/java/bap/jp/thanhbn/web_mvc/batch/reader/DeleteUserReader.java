package bap.jp.thanhbn.web_mvc.batch.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bap.jp.thanhbn.web_mvc.model.User;
import bap.jp.thanhbn.web_mvc.service.user.UserServiceImpl;

@Component
public class DeleteUserReader implements ItemReader<User> {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	private int currentIndex = 0;
	
	private List<User> users = new ArrayList<User>();
	
	@Override
	public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		
		if(userServiceImpl.getAllUserNotBuyProduct().size() == 0) {
			currentIndex = 0;
			return null;
		}
		
		if(users.size() < userServiceImpl.getAllUserNotBuyProduct().size()) {
			users = userServiceImpl.getAllUserNotBuyProduct();
		}
		
		if(currentIndex < users.size()) {
			return users.get(currentIndex++);
		}
		
		return null;
		
	}

}
