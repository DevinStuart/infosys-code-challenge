package com.infy.listapp.user;

import java.net.URI;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	UserRespository userRepository;
	
	public UserService(UserRespository userRespository) {
		this.userRepository = userRespository;
	}
	
	@Async
	public CompletableFuture<Optional<URI>> create(UserDTO userDto) {
		try {
			return CompletableFuture.completedFuture(Optional.of(
					URI.create("/api/user/" + Integer.toString(userRepository.save(UserAdaptor.convert(userDto)).getId()))));
		} catch (Exception e) {
			e.printStackTrace();
			return CompletableFuture.completedFuture(Optional.empty());
		}
	}

}
