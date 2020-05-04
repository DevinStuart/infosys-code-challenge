package com.infy.listapp.checklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.infy.listapp.user.UserAdaptor;
import com.infy.listapp.util.TestUtil;

@SpringBootTest
public class ChecklistControllerTests {
	
	@InjectMocks
	ChecklistController checklistController;
	
	@Mock
	ChecklistService checklistService;
	
	@Test
	public void getListsByUserTest() throws InterruptedException, ExecutionException {
		List<Checklist> data = TestUtil.generateTestData();
		when(checklistService.getCheckLists(UserAdaptor.convert(data.get(0).getOwner())))
			.thenReturn(CompletableFuture.completedFuture(Optional.of(
			data.stream().map(ChecklistAdaptor::convert).collect(Collectors.toList()))));
		CompletableFuture<ResponseEntity<List<ChecklistDTO>>> result = checklistController.getByUser(
				UserAdaptor.convert(data.get(0).getOwner()));
		assertEquals(result.get().getBody(), data);
	}
	
	@Test
	public void getListByIdTest() throws InterruptedException, ExecutionException {
		List<Checklist> data = TestUtil.generateTestData();
		when(checklistService.getCheckList(1))
			.thenReturn(CompletableFuture.completedFuture(Optional.of(ChecklistAdaptor.convert(data.get(0)))));
		CompletableFuture<ResponseEntity<ChecklistDTO>> result = checklistController.getById(1);
		assertEquals(result.get().getBody(), data);
	}
	
	@Test
	public void createChecklistTest() throws URISyntaxException, InterruptedException, ExecutionException {
		List<Checklist> data = TestUtil.generateTestData();
		when(checklistService.create(ChecklistAdaptor.convert(data.get(0))))
			.thenReturn(CompletableFuture.completedFuture(Optional.of(new URI("/api/checklist/1"))));
		CompletableFuture<ResponseEntity<URI>> result = checklistController.createChecklist(ChecklistAdaptor.convert(data.get(0)));
		assertEquals(result.get().getBody().getPath(), "/api/checklist/1");
	}
	
	@Test
	public void updateChecklistTest() throws InterruptedException, ExecutionException {
		List<Checklist> data = TestUtil.generateTestData();
		when(checklistService.update(ChecklistAdaptor.convert(data.get(0))))
			.thenReturn(CompletableFuture.completedFuture(Optional.of(ChecklistAdaptor.convert(data.get(0)))));
		CompletableFuture<ResponseEntity<URI>> result = checklistController.createChecklist(ChecklistAdaptor.convert(data.get(0)));
		assertEquals(result.get().getBody(), data);
	}
	@Test
	public void deleteChecklistTest() throws InterruptedException, ExecutionException {
		when(checklistService.deleteById(1))
			.thenReturn(CompletableFuture.completedFuture(Optional.of(1)));
		CompletableFuture<ResponseEntity<Integer>> result = checklistController.deleteChecklist(1);
		assertEquals(result.get().getBody(), 1);
	}
}
