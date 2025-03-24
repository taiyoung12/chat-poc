package us.zep.chatserver.repository.user;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import us.zep.chatserver.entity.User;

@Repository
public class UserRepository {
	private final Set<User> userChatRooms = ConcurrentHashMap.newKeySet();

	public User save(String name, String email){
		return new User(name, email);
	}
}
