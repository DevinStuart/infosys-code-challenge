package com.infy.listapp.checklist;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.listapp.user.UserDTO;

@RestController
@RequestMapping("/api/checklist")
public class ChecklistController {
	private ChecklistService checklistService;
	
	public ChecklistController(ChecklistService checklistService) {
		this.checklistService = checklistService;
	}
	
	@Async
	@GetMapping("/{id}")
	public CompletableFuture<ResponseEntity<ChecklistDTO>> getById(@PathVariable Integer id){
		return checklistService.getCheckList(id).thenApply(checklistOptional ->
				checklistOptional.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
	}
	@Async
	@GetMapping("/")
	public CompletableFuture<ResponseEntity<List<ChecklistDTO>>> getByUser(@RequestBody UserDTO userDto){
		return checklistService.getCheckLists(userDto).thenApply(checklistOptional -> 
				checklistOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
	}
	@Async
	@PostMapping("/")
	public CompletableFuture<ResponseEntity<URI>> createChecklist(@RequestBody ChecklistDTO checklistDto){
		return checklistService.create(checklistDto).thenApply(checklistOptional ->
				checklistOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
	}
	@Async
	@PutMapping("/")
	public CompletableFuture<ResponseEntity<ChecklistDTO>> updateChecklist(@RequestBody ChecklistDTO checklistDto){
		return checklistService.update(checklistDto).thenApply(checklistOptional ->
			checklistOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
	}
	@Async
	@DeleteMapping("/{id}")
	public CompletableFuture<ResponseEntity<Integer>> deleteChecklist(@PathVariable Integer id){
		return checklistService.deleteById(id).thenApply(checklistIdOptional -> 
				checklistIdOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
	}
	
}
