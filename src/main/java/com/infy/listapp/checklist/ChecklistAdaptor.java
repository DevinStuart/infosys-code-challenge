package com.infy.listapp.checklist;

import java.util.ArrayList;
import java.util.List;

import com.infy.listapp.item.Task;
import com.infy.listapp.item.TaskAdaptor;
import com.infy.listapp.item.TaskDTO;
import com.infy.listapp.user.UserAdaptor;

public class ChecklistAdaptor {
	public static ChecklistDTO convert(Checklist checklist) {
		List<TaskDTO> tasks = new ArrayList<TaskDTO>();
		checklist.getTasks().forEach(task -> tasks.add(TaskAdaptor.convert(task)));
		return new ChecklistDTO(checklist.getId(), checklist.getDescription(), tasks, UserAdaptor.convert(checklist.getOwner()));
	}
	public static Checklist convert(ChecklistDTO checklistDto) {
		List<Task> tasks = new ArrayList<Task>();
		checklistDto.getTasks().forEach(taskDto -> tasks.add(TaskAdaptor.convert(taskDto)));
		return new Checklist(checklistDto.getId(), checklistDto.getDescription(), tasks, UserAdaptor.convert(checklistDto.getOwner()));
	}
}
