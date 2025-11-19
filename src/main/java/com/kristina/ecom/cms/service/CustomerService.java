package com.kristina.ecom.cms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kristina.ecom.dao.DAO;
import com.kristina.ecom.dao.DAOException;
import com.kristina.ecom.dao.JPAFactory;
import com.kristina.ecom.cms.domain.Customer;

@Component
public class CustomerService {
  private DAO<Long, Customer> dao;

  public CustomerService() {
    dao = JPAFactory.getInstance().create(DAO.Type.CUSTOMER_DAO);
  }

  public int create(Customer customer) {
    try {
      dao.create(customer);
    } catch (DAOException ex) {
      ex.printStackTrace();
    }
    return 1;
  }

  public List<Customer> readAll() {
    List<Customer> customers = new ArrayList<Customer>();
    try {
      customers = dao.readAll();
    } catch (DAOException ex) {
      ex.printStackTrace();
    }

    return customers;
  }

  public Customer read(Long id) {
    Customer customer = null;
    try {
      customer = dao.read(id);
    } catch (DAOException ex) {
      ex.printStackTrace();
    }

    return customer;
  }

  public int update (Customer customer) {
    int rows = 0;
    try {
      rows = dao.update(customer);
    } catch (DAOException ex) {
      ex.printStackTrace();
    }

    return rows;
  }

  public int delete(Long id) {
    int rows = 0;
    try {
      rows = dao.delete(id);
    } catch (DAOException ex) {
      ex.printStackTrace();
    }
    return rows;
  }
}
