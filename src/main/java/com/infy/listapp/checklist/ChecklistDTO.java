package com.infy.listapp.checklist;

import java.util.List;

import com.infy.listapp.item.TaskDTO;
import com.infy.listapp.user.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistDTO {
	private int id;
	private String description;
	private List<TaskDTO> tasks;
	private UserDTO owner;
}
