package com.change.hippo.dto;

/**
 * User: change.long Date: 16/6/14 Time: 上午9:56
 */
public class UserDto {
	private String username;
	public String address;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override public String toString() {
		return "UserDto{" +
				"username='" + username + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
