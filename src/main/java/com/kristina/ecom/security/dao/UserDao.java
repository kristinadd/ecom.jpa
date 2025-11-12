package com.kristina.ecom.security.dao;

import java.util.Optional;
import java.util.Date;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kristina.ecom.security.domain.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserDao extends JpaRepository<User, Long> { 
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query(value="update user set email = :email, password = :password, first_name = :fname, last_name = :lname, dob = :dob, gender = :gender where id = :id", nativeQuery=true)
    void updateProfile(Long id, String email, String password, String fname, String lname, Date dob, String gender);   
    
    @Transactional
    @Modifying
    @Query(value="update user set qubcoin = :qubcoin where id = :id", nativeQuery=true)
    void updateQUBCoin(Long id, Integer qubcoin);          
}
