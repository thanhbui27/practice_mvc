package bap.jp.thanhbn.web_mvc.batch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bap.jp.thanhbn.web_mvc.model.User;
import bap.jp.thanhbn.web_mvc.repository.UserRepository;

@Component
public class DeleteUserWriter implements ItemWriter<User> {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void write(Chunk<? extends User> chunk) throws Exception {
		// TODO Auto-generated method stub
		userRepository.deleteAll(chunk);
	}

}
