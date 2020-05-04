package com.infy.listapp.util;

import java.util.ArrayList;
import java.util.List;

import com.infy.listapp.checklist.Checklist;
import com.infy.listapp.item.Task;
import com.infy.listapp.user.User;

public class TestUtil {
	public static List<Checklist> generateTestData(){
		List<Checklist> data = new ArrayList<Checklist>();
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(new Task(1, false, "Wash the car"));
		tasks.add(new Task(2, true, "Take the dog out"));
		tasks.add(new Task(3, false, "Make food"));
		List<Task> tasks2 = new ArrayList<Task>();
		tasks2.add(new Task(4, true, "Meet the bakers"));
		tasks2.add(new Task(5, true, "Lunch with dan"));
		tasks2.add(new Task(6, false, "Go for a drive"));
		List<Task> tasks3 = new ArrayList<Task>();
		tasks3.add(new Task(4, true, "Eat some ramen"));
		tasks3.add(new Task(5, false, "make some soup"));
		tasks3.add(new Task(6, false, "Take a bit of me time"));
		User user = new User(1, "test");
		User user2 = new User(1, "test2");
		data.add(new Checklist(1, "New checklist", tasks, user));
		data.add(new Checklist(2, "checklist #2", tasks2, user));
		data.add(new Checklist(2, "checklist #3", tasks3, user2));
		return data;
	}
}
