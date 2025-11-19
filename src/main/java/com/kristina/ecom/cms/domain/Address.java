package com.kristina.ecom.cms.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.JoinColumn;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Table;


@Entity
@Table(name = "address")
public class Address implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "street", nullable = false)
  private String street;
  
  @NotNull
  @AttributeOverrides({
    @AttributeOverride(name = "name", column = @Column(name = "city_name")),
  })
  private String city;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
    name = "contact",
    joinColumns = @JoinColumn(name = "customer_id")
  )
  @Column(name = "name-for-contact")
  private Set<String> contacts = new HashSet<>();


  public Address() {}

  public Address(String street, String city, Set<String> contacts) {
    this.street = street;
    this.city = city;
    this.contacts = contacts;
  }

  public Long getId() {
    return id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public Set<String> getContacts() {
    return contacts;
  }

  public void setContacts(Set<String> contacts) {
    this.contacts = contacts;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Override
  public String toString() {
    return "Address [street=" + street + ", city=" + city + ", contacts=" + contacts + "]";
  } 
}
