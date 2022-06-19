package com.example.pubservices.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pubservices.dto.ProviderBean;
import com.example.pubservices.model.Role;
import com.example.pubservices.model.Sector;
import com.example.pubservices.model.User;
import com.example.pubservices.repository.SectorRepository;
import com.example.pubservices.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service 
@Transactional 
@Slf4j
public class UserService implements UserDetailsService{
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SectorRepository sectorRepository;

    public User saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));;
        return this.userRepository.save(user);
    }

    public User saveProvider(ProviderBean provider){
        User user = new User();
        Role role = roleService.findByName("Role_SUPERVISOR");
        Sector sector = sectorRepository.findById(provider.getIdSector()).orElseThrow(()-> new IllegalStateException("Sector does not exists !"));
        String token = UUID.randomUUID().toString();
        user.setFirstName(provider.getFirstName());
        user.setLastName(provider.getLastName());
        user.setPhoneNumber(provider.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(provider.getPassword()));
        user.setEmail(provider.getEmail());
        user.setRole(role);
        user.setSector(sector);
        user.setToken(token);
        user.setCreationDate(LocalDateTime.now());
        user.setTokenCreatedAt(LocalDateTime.now());
        user.setTokenExpiresAt(LocalDateTime.now().plusDays(1));
        return this.userRepository.saveAndFlush(user);
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

    public User findById(Long id){
        return this.userRepository.findById(id).orElseThrow(()-> new IllegalStateException("User doesn't exists ! "));
    }

    public User confirmToken(String token){
        User userToken = this.findUserByToken(token).orElseThrow(()-> new IllegalStateException("User doesn't exists ! "));
        this.setConfirmedAt(token);
        this.enableUser(userToken.getEmail());

        User activatedUser = this.findUserByEmail(userToken.getEmail());
        return activatedUser;
    }

    public Optional<User> findUserByToken(String token) {
        return this.userRepository.findUserByToken(token);
    }

    public int enableUser(String email) {
        return this.userRepository.enableUser(email);
    }

    public int setConfirmedAt(String token) {
        return this.userRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
    
    public User findUserByEmail(String email) {
        User user = this.userRepository.findUserByEmail(email);  
        if (user == null) {
            throw new IllegalStateException("User doesn'tready exists !");
        } 
        else if(Boolean.FALSE.equals(user.getActiveAccount())){
            throw new IllegalStateException("Account is not actived yet !");
        }
        else{
            return user;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else{
            log.info("User found in the dataBase: {}", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
       
            authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));//attribuer une authorisation aux roles de cet utilisateur
       
         return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
