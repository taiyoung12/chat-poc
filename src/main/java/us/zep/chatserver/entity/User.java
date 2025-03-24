package us.zep.chatserver.entity;

import us.zep.chatserver.common.utils.UUIDGenerator;

public class User {
	private String id;
	private String name;
	private String email;

	public User() {
	}

	public User(String name, String email) {
		this.id = UUIDGenerator.generate();
		this.name = name;
		this.email = email;
	}

	public String getName(){
		return name;
	}

	public String getEmail(){
		return email;
	}
	public String getId(){
		return id;
	}
}
