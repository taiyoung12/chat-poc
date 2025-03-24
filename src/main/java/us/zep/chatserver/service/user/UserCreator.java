package us.zep.chatserver.service.user;

import org.springframework.stereotype.Service;

import us.zep.chatserver.controller.user.request.UserCreateRequest;
import us.zep.chatserver.controller.user.response.UserCreateResponse;
import us.zep.chatserver.entity.User;
import us.zep.chatserver.repository.user.UserRepository;

@Service
public class UserCreator {
	private final UserRepository userRepository;

	public UserCreator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserCreateResponse addUser(UserCreateRequest userCreateRequest){
		User user = userRepository.save(
			userCreateRequest.getName(),
			userCreateRequest.getEmail()
		);

		return new UserCreateResponse(user.getId());
	}
}
