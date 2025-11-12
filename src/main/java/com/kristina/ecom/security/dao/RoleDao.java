package com.kristina.ecom.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kristina.ecom.security.domain.Role;
import com.kristina.ecom.security.domain.Roles;

import jakarta.transaction.Transactional;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> { // Data Access Object: design pattern
    Optional<Role> findByName(Roles name);

    @Transactional
    @Modifying
    @Query(value="update user_role set role_id = :rid where user_id = :uid", nativeQuery=true)
    void updateRole(Long uid, Integer rid);      
}

