package com.kristina.ecom.security.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.kristina.ecom.security.domain.User;
import com.kristina.ecom.security.dao.UserDao;

@Service
public class UserService {

    @Autowired
    private UserDao dao;

    public User saveUser(User user) {
        return dao.save(user);
    }

    public Iterable<User> getAllUsers() {
        return dao.findAll();
    }

    public User getUserById(Long id) {
        return dao.findById(id).orElse(null);
    }

    public void deleteUserById(Long id) {
        dao.deleteById(id);
    }
}
