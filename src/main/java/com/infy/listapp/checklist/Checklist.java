package com.infy.listapp.checklist;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.infy.listapp.item.Task;
import com.infy.listapp.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Checklist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)//this allows for tasks to be created along with a checklist
	private List<Task> tasks;
	@ManyToOne(cascade = CascadeType.MERGE)//users should be previously created, not created via the checklist
	private User owner;
}
