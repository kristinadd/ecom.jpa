package com.kristina.ecom.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.kristina.ecom.cms.dao.CustomerDAO;


public class JPAFactory implements AbstractFactory {
  private static JPAFactory instance = new JPAFactory();
  private static EntityManagerFactory emf;

  private JPAFactory() {
    load("db.properties");
  }


  public static EntityManagerFactory getEmFactory(String pu) {
    if (emf == null ) {
      emf = Persistence.createEntityManagerFactory(pu);
    }
    return emf;
  }

  public static void release() {
    if (emf != null && emf.isOpen()) {
      emf.close();
    }
  }

  public static JPAFactory getInstance() {
    return instance;
  }

  public DAO create(DAO.Type type) {
    return new CustomerDAO();
  }
}
