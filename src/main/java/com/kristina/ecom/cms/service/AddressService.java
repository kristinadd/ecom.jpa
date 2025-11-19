package com.kristina.ecom.cms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kristina.ecom.dao.DAO;
import com.kristina.ecom.dao.DAOException;
import com.kristina.ecom.dao.JPAFactory;
import com.kristina.ecom.cms.domain.Address;

@Component
public class AddressService {
  private DAO<Long, Address> dao;

  public AddressService() {
    dao = JPAFactory.getInstance().create(DAO.Type.ADDRESS_DAO);
  }

  public int create(Address address) {
    try {
      dao.create(address);
    } catch (DAOException ex) {
      ex.printStackTrace();
    }
    return 1;
  }

  public List<Address> readAll() {
    List<Address> addresses = new ArrayList<Address>();
    try {
      addresses = dao.readAll();
    } catch (DAOException ex) {
      ex.printStackTrace();
    }

    return addresses;
  }

  public Address read(Long id) {
    Address address = null;
    try {
      address = dao.read(id);
    } catch (DAOException ex) {
      ex.printStackTrace();
    }

    return address;
  }

  public int update (Address address) {
    int rows = 0;
    try {
      rows = dao.update(address);
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
