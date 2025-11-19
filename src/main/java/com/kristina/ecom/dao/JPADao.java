package com.kristina.ecom.dao;

import java.lang.reflect.ParameterizedType;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public abstract class JPADao<K, V> implements DAO<K, V> {
  
  protected final EntityManagerFactory emf;
  private final Class<V> clazz;

  @SuppressWarnings("unchecked")
  public JPADao() {
    this.emf = JPAFactory.getEmFactory("jpa");
    this.clazz = (Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  @Override
  public V create(V v) {
      emf.runInTransaction(em -> {
        em.persist(v);
      });
      return v;
  }

  public List<V> readAll() {
    return emf.callInTransaction(em  -> {
      return em.createQuery("from " + clazz.getSimpleName(), clazz).getResultList();
    });
  }

  public V read(K k) {
    return emf.callInTransaction(em  -> {
      return em.find(clazz, k);
    });
  }


  public int update(V v) {
    emf.runInTransaction(em -> {
      em.merge(v);
    });
    return 1;
  }

  public int delete(K k) {
    emf.runInTransaction(em -> {
      em.remove(em.contains(k) ? k : em.merge(k));
    });
    return 1;
  }
}
