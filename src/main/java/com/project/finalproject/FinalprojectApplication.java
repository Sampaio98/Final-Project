package com.project.finalproject;

import com.project.finalproject.model.Professional;
import com.project.finalproject.model.Service;
import com.project.finalproject.model.User;
import com.project.finalproject.repository.ProfessionalRepository;
import com.project.finalproject.repository.ServiceRepository;
import com.project.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalprojectApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfessionalRepository professionalRepository;

	@Autowired
	private ServiceRepository serviceRepository;


	public static void main(String[] args) {
		SpringApplication.run(FinalprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user = new User();
		user.setUsername("alfredo98");
		user.setName("Alfredo");

		Professional professional = new Professional();
		professional.setName("Zé Porva");
		professional.setUsername("ZehPorva98");

		userRepository.save(user);
		professionalRepository.save(professional);

		Service service = new Service();
		service.setUser(user);
		service.setProfessional(professional);
		service.setWorth(true);
		user.getServices().add(service);
		userRepository.save(user);

	}
}
