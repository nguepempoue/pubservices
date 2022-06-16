package com.example.pubservices;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.pubservices.model.Role;
import com.example.pubservices.model.Sector;
import com.example.pubservices.model.User;
import com.example.pubservices.service.RoleService;
import com.example.pubservices.service.SectorService;
import com.example.pubservices.service.UserService;

@SpringBootApplication
public class PubservicesApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(PubservicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Bean
	CommandLineRunner run(RoleService roleService, UserService userService, SectorService sectorService){
		return args ->{
			sectorService.saveSector(new Sector(null, "batiment"));
			sectorService.saveSector(new Sector(null, "informatique"));
			sectorService.saveSector(new Sector(null, "medecine"));

			roleService.saveRole(new Role(null, "Role_ADMIN"));
			roleService.saveRole(new Role(null, "Role_SUPERVISOR"));

			User user = new User();
			user.setFirstName("julio");
			user.setLastName("nguepempoue");
			user.setPassword("123456");
			user.setEmail("julio@gmail.com");
			user.setPhoneNumber(681053332);
			user.setSector(null);
			
			Role role = roleService.findByName("Role_ADMIN");
			user.setRole(role);
			userService.saveAdmin(user);

			
			
		};
	}

}
