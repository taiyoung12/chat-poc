package us.zep.chatserver.entity;

import us.zep.chatserver.common.utils.UUIDGenerator;

public class User {
	private String id;
	private String name;
	private String email;

	public User() {
	}

	public User(String name, String email) {
		this.id = generateId();
		this.name = name;
		this.email = email;
	}

	public String getName(){
		return name;
	}

	public String getEmail(){
		return email;
	}

	private String generateId(){
		return UUIDGenerator.generate();
	}
}
