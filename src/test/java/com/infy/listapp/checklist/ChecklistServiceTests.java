package com.infy.listapp.checklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.listapp.user.UserAdaptor;
import com.infy.listapp.util.TestUtil;

@SpringBootTest
public class ChecklistServiceTests {
	@Mock
	ChecklistRepository checklistRepository;
	
	@InjectMocks
	ChecklistService checklistService;
	
	@Test
	public void getListsByUserTest() throws InterruptedException, ExecutionException {
		List<Checklist> data = TestUtil.generateTestData();
		when(checklistRepository.findAllByOwner(data.get(0).getOwner())).thenReturn(data);
		CompletableFuture<Optional<List<ChecklistDTO>>> result = checklistService.getCheckLists(UserAdaptor.convert(data.get(0).getOwner()));
		assertEquals(data, result.get().get());
	}
	@Test
	public void getListByIdTest() throws InterruptedException, ExecutionException {
		List<Checklist> data = TestUtil.generateTestData();
		when(checklistRepository.findById(data.get(0).getId())).thenReturn(Optional.of(data.get(0)));
		CompletableFuture<Optional<ChecklistDTO>> result = checklistService.getCheckList(data.get(0).getId());
		assertEquals(data.get(0), result.get().get());
	}
	@Test
	public void createListTest() throws InterruptedException, ExecutionException {
		Checklist data = TestUtil.generateTestData().get(0);
		when(checklistRepository.save(data)).thenReturn(data);
		CompletableFuture<Optional<URI>> result = checklistService.create(ChecklistAdaptor.convert(data));
		assertEquals(result.get().get().getPath(), "/api/checklist/1");
	}
	@Test
	public void updateListTest() throws InterruptedException, ExecutionException {
		Checklist data = TestUtil.generateTestData().get(0);
		when(checklistRepository.save(data)).thenReturn(data);
		CompletableFuture<Optional<ChecklistDTO>> result = checklistService.update(ChecklistAdaptor.convert(data));
		assertEquals(result.get().get(), data);
	}
	@Test
	public void deleteByIdTest() throws InterruptedException, ExecutionException {
		Checklist data = TestUtil.generateTestData().get(0);
		CompletableFuture<Optional<Integer>> result = checklistService.deleteById(data.getId());
		assertEquals(result.get().get(), data.getId());
	}
}
