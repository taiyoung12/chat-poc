package us.zep.chatserver.service.user;

import org.springframework.stereotype.Service;

import us.zep.chatserver.controller.user.response.UserCreateResponse;
import us.zep.chatserver.entity.User;
import us.zep.chatserver.repository.user.UserRepository;

@Service
public class UserCreator {
	private final UserRepository userRepository;

	public UserCreator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserCreateResponse addUser(String name, String email){
		User user = userRepository.save(name, email);

		return new UserCreateResponse(user.getId());
	}
}
