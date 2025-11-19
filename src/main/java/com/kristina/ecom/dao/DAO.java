package com.kristina.ecom.dao;
import java.util.List;
import java.util.ArrayList;

public interface DAO<K, V> {
  V create(V v) throws DAOException;
  List<V> readAll() throws DAOException;
  V read(K k) throws DAOException;
  int update(V v) throws DAOException;
  int delete(K k) throws DAOException;
  default List<String> getTypes() throws DAOException {
    return new ArrayList<>();
  }

  public enum Type {
    SQL, PRODUCT_DAO, ORDER_DAO, MONGO, SHOPPING_CART_DAO, JPA, CUSTOMER_DAO, ADDRESS_DAO
  }
}
