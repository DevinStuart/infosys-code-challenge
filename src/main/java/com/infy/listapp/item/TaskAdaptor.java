package com.infy.listapp.item;

public class TaskAdaptor {
	public static TaskDTO convert(Task task) {
		return new TaskDTO(task.getId(), task.isDone(), task.getDescription());
	}
	public static Task convert(TaskDTO taskDto) {
		return new Task(taskDto.getId(), taskDto.isDone(), taskDto.getDescription());
	}
}
