package com.example.pubservices.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pubservices.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long>{

    User findByEmail(String email);
    Optional<User> findUserByToken(String token);
    User findUserByEmail(String email);

    @Modifying
    @Query("UPDATE User a  SET a.activeAccount = TRUE WHERE a.email = ?1")
    int enableUser(String email);

    @Modifying
    @Query("UPDATE User c SET c.tokenConfirmedAt = ?2 WHERE c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
