package com.project.finalproject;

import com.project.finalproject.model.Professional;
import com.project.finalproject.model.Attendance;
import com.project.finalproject.model.User;
import com.project.finalproject.repository.ProfessionalRepository;
import com.project.finalproject.repository.AttendanceRepository;
import com.project.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class FinalprojectApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;


	public static void main(String[] args) {
		SpringApplication.run(FinalprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user = new User();
		user.setName("ZÉ PORVA");
		user.setUsername("zeporva");
		user.setCpf("557.727.410-98");
		user.setEmail("zeporva@hotmail.com");
		user.setPhone("43 9999-9999");
		user.setPassword("12345");

		User user2 = new User();
		user2.setName("Rogerin bala tensa");
		user2.setUsername("rogerin");
		user2.setPassword("12345");
		user2.setCpf("622.182.160-60");
		user2.setEmail("rogerin@hotmail.com");
		user2.setPhone("43 8888-8888");

		User user3 = new User();
		user3.setName("Duana Nou");
		user3.setUsername("duana");
		user3.setPassword("12345");
		user3.setCpf("677.293.640-00");
		user3.setEmail("duana@hotmail.com");
		user3.setPhone("43 7777-7777");

		userRepository.saveAll(Arrays.asList(user, user2, user3));

	}
}
