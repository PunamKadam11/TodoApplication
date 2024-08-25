package com.example.TaskApplication;

import com.example.TaskApplication.model.Role;
import com.example.TaskApplication.model.User;

import com.example.TaskApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootApplication

public class TaskApplication implements CommandLineRunner  {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<User> adminAccount = userRepository.findByRole(Role.ADMIN);

		if (adminAccount.isEmpty()) {
			User user = new User();
			user.setUsername("admin@ctpl.in");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setRole(Role.ADMIN);
			userRepository.save(user);
		}
	}
}
