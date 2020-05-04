package com.infy.listapp.user;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
	UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@Async
	@PostMapping("/")
	public CompletableFuture<ResponseEntity<URI>> createChecklist(@RequestBody UserDTO userDto){
		return userService.create(userDto).thenApply(userOptional ->
				userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
	}
	
}
