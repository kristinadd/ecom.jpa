package com.kristina.ecom.cms.dao;

import java.util.List;

import com.kristina.ecom.cms.domain.Customer;
import com.kristina.ecom.dao.JPADao;

import jakarta.persistence.EntityManager;

public class CustomerDAO extends JPADao<Long, Customer> {
  public Customer findByName(String name) {
    EntityManager em = emf.createEntityManager();

    String jpql = "SELECT c FROM Customer c WHERE c.name = :name";
    return em.createQuery(jpql, Customer.class)
      .setParameter("name", name)
      .getSingleResult();
  }

  public List<Customer> findByCity(String city) {
    EntityManager em = emf.createEntityManager();

    // 3 levels of nesting
    String jpql = "SELECT c FROM Customer c WHERE c.address.city.name = :city";
    return em.createQuery(jpql, Customer.class)
      .setParameter("city", city)
      .getResultList();
  }
}
