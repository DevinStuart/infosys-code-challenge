package com.infy.listapp.checklist;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.listapp.user.User;

@Repository
public interface ChecklistRepository extends CrudRepository<Checklist, Integer> {
	public List<Checklist> findAllByOwner(User user);
	
}
