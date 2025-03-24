package us.zep.chatserver.repository.history;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class UserRoomHistoryRepository {
	private final Map<String, Boolean> userRoomHistory = new ConcurrentHashMap<>();

	public boolean hasEnteredRoom(String userId, String roomId) {
		String entryKey = createEntryKey(userId, roomId);
		return userRoomHistory.containsKey(entryKey);
	}

	public void recordRoomEntry(String userId, String roomId) {
		String entryKey = createEntryKey(userId, roomId);
		userRoomHistory.put(entryKey, true);
	}

	public void removeRoomEntry(String userId, String roomId) {
		String entryKey = createEntryKey(userId, roomId);
		userRoomHistory.remove(entryKey);
	}

	private String createEntryKey(String userId, String roomId) {
		return userId + ":" + roomId;
	}
}
