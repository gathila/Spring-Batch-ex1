package com.example.batch.write;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.batch.model.User;
import com.example.batch.repository.UserRepository;

@Component
public class DBWriter implements ItemWriter<User> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void write(List<? extends User> items) throws Exception {

		System.out.println("Save all the user " + items);
		userRepository.saveAll(items);
	}

}
