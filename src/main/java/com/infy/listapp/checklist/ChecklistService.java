package com.infy.listapp.checklist;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.infy.listapp.user.UserAdaptor;
import com.infy.listapp.user.UserDTO;

@Service
public class ChecklistService {
	ChecklistRepository checklistRepository;
	
	@Async
	public CompletableFuture<Optional<ChecklistDTO>> getCheckList(int id) {
		try {
			return CompletableFuture.completedFuture(checklistRepository.findById(id).map(ChecklistAdaptor::convert));
		}catch (Exception e) {
			return CompletableFuture.completedFuture(Optional.empty());
		}
	}
	@Async
	public CompletableFuture<Optional<List<ChecklistDTO>>> getCheckLists(UserDTO userDto){
		try {
			return CompletableFuture.completedFuture(Optional.of(checklistRepository.findAllByOwner(UserAdaptor.convert(userDto))
					.stream().map(ChecklistAdaptor::convert).collect(Collectors.toList())));
		} catch (Exception e) {
			return CompletableFuture.completedFuture(Optional.empty());
		}
	}
	@Async
	public CompletableFuture<Optional<URI>> create(ChecklistDTO checklistDto){
		try {
			return CompletableFuture.completedFuture(Optional.of(
					URI.create("/api/checklist/" + Integer.toString(checklistRepository.save(ChecklistAdaptor.convert(checklistDto)).getId()))));
		} catch (Exception e) {
			e.printStackTrace();
			return CompletableFuture.completedFuture(Optional.empty());
		}
	}
	@Async
	public CompletableFuture<Optional<ChecklistDTO>> update(ChecklistDTO checklistDto){
		try {
			return CompletableFuture.completedFuture(Optional.of(ChecklistAdaptor.convert(
					checklistRepository.save(ChecklistAdaptor.convert(checklistDto)))));
		} catch (Exception e) {
			e.printStackTrace();
			return CompletableFuture.completedFuture(Optional.empty());
		}
	}
	@Async
	public CompletableFuture<Optional<Integer>> deleteById(int id){
		try {
			checklistRepository.deleteById(id);
			return CompletableFuture.completedFuture(Optional.of(new Integer(id)));
		} catch (Exception e) {
			return CompletableFuture.completedFuture(Optional.empty());
		}
	}
	public ChecklistService(ChecklistRepository checklistRepository) {
		this.checklistRepository = checklistRepository;
	}
}
