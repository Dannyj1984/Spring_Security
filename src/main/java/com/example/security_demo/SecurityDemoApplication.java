package com.example.security_demo;

import com.example.security_demo.member.Member;
import com.example.security_demo.member.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(MemberRepository memberRepository) {
		return args -> {
			Member danny = new Member("dannyjebb", "Danny", "Jebb", "dannyjebb@gmail.com", "USER", 4.0, 0, 4.0 ,0, 0,"P4ssword");
			Member mike = new Member("mikedobson", "Mike", "Dobson", "mikedobson@gmail.com", "ADMIN", 18.7, 0, 18.7 ,0, 0,"P4ssword");
			Member dan = new Member("dancross", "Dan", "Cross", "dancross@gmail.com", "EVENTADMIN", 8.2, 0, 8.2,0, 0,"P4ssword");
			Member lee = new Member("leeoconnell", "Lee", "O'Connell", "leeoconnell@gmail.com", "HADNICAPADMIN", 14.2, 0, 14.2,0, 0,"P4ssword"


			);

			memberRepository.save(danny);
			memberRepository.save(dan);
			memberRepository.save(mike);
			memberRepository.save(lee);
		};
	}

}
