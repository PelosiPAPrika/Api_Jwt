package com.jwt.test.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//	@Bean
//	CommandLineRunner run (UserService userService, FoodRepo foodRepo) {
//		return args -> {
//			foodRepo.save(new Food(null, "Hamburger", "bla", new BigDecimal(13.66)));
//			foodRepo.save(new Food(null, "Francesinha", "bla", new BigDecimal(17.66)));

//			userService.saveRole(new Role(null, "ROLE_USER"));
//			userService.saveRole(new Role(null, "ROLE_MANAGER"));
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
//
//			userService.saveUser(new User(null, "John" , "jhon", "1234", new ArrayList<>()));
//			userService.saveUser(new User(null, "Nhoj" , "nhoj", "1234", new ArrayList<>()));
//			userService.saveUser(new User(null, "Paulo" , "oluaP", "1234", new ArrayList<>()));
//			userService.saveUser(new User(null, "Miguel" , "miguel", "1234", new ArrayList<>()));
//
//			userService.addRole("jhon", "ROLE_USER");
//			userService.addRole("jhon", "ROLE_MANAGER");
//			userService.addRole("Nhoj", "ROLE_MANAGER");
//			userService.addRole("oluaP", "ROLE_ADMIN");
//			userService.addRole("miguel", "ROLE_USER");
//			userService.addRole("miguel", "ROLE_ADMIN");
//			userService.addRole("miguel", "ROLE_SUPER_ADMIN");
//			userService.saveUser(new User(null, "Losi" , "losi", "1234", new ArrayList<>()));
//			userService.addRole("jhon", "ROLE_USER");
//
//
//		};
//	}

}
