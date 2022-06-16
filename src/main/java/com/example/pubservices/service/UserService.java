package com.example.pubservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pubservices.dto.ProviderBean;
import com.example.pubservices.model.Role;
import com.example.pubservices.model.Sector;
import com.example.pubservices.model.User;
import com.example.pubservices.repository.SectorRepository;
import com.example.pubservices.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SectorRepository sectorRepository;

    public User saveAdmin(User user){
        return this.userRepository.save(user);
    }

    public User saveProvider(ProviderBean provider){
        User user = new User();
        Role role = roleService.findByName("Role_SUPERVISOR");
        Sector sector = sectorRepository.findById(provider.getIdSector()).orElseThrow(()-> new IllegalStateException("Sector does not exists !"));
        user.setFirstName(provider.getFirstName());
        user.setLastName(provider.getLastName());
        user.setPhoneNumber(provider.getPhoneNumber());
        user.setPassword(provider.getPassword());
        user.setEmail(provider.getEmail());
        user.setRole(role);
        user.setSector(sector);
        return this.userRepository.save(user);
    }

    public User updateUser(User user){
        return this.userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User findByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

}
