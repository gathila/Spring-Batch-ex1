package com.example.batch.process;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.batch.model.User;

@Component
public class Processor implements ItemProcessor<User, User> {

	private static Map<String, String> DEPT_NAMES = new HashMap<>();

	public Processor() {
		DEPT_NAMES.put("001", "Technology");
		DEPT_NAMES.put("002", "Operations");
		DEPT_NAMES.put("003", "Accounts");
	}

	@Override
	public User process(User item) throws Exception {

		String dept = DEPT_NAMES.get(item.getDept());
		item.setDept(dept);
		System.out.println("Processed " + item);
		return item;
	}

}
