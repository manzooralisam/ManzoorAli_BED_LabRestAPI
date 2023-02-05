package com.glearning.students.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.glearning.students.dao.StudentRepository;
import com.glearning.students.dao.UserRepository;
import com.glearning.students.model.Role;
import com.glearning.students.model.Student;
import com.glearning.students.model.User;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootstrapAppData {
	
	private final UserRepository userRepository;
	private final StudentRepository studentRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void initializeData(ApplicationReadyEvent readyEvent) {
		
		Student suresh = new Student("Suresh", "Reddy", "B.Tech", "India");
		Student murali = new Student("Murali", "Mohan", "B.Arch", "Canada");
		Student daniel = new Student("Daniel", "Denson", "B.Tech", "New Zealand");
		Student taniya = new Student("Taniya", "Gupta", "B.Com", "USA");
		
		this.studentRepository.save(suresh);
		this.studentRepository.save(murali);
		this.studentRepository.save(daniel);
		this.studentRepository.save(taniya);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void initializeUsersData(ApplicationReadyEvent readyEvent) {
		
			User suresh = new User("suresh", passwordEncoder.encode("welcome"));
			User murali = new User("murali", passwordEncoder.encode("welcome"));
			
			Role userRole = new Role("ROLE_USER");
			Role adminRole = new Role("ROLE_ADMIN");
			
			suresh.addRole(userRole);
			
			murali.addRole(userRole);
			murali.addRole(adminRole);
			
			this.userRepository.save(suresh);
			this.userRepository.save(murali);
		
	}

}
