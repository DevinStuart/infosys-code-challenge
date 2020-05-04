package com.infy.listapp.user;

public class UserAdaptor {
	public static UserDTO convert(User user) {
		return new UserDTO(user.getId(), user.getUsername());
	}
	public static User convert(UserDTO UserDto) {
		return new User(UserDto.getId(), UserDto.getUsername());
	}
}
