package com.kristina.ecom.cms.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(
    optional = false,
    cascade = CascadeType.PERSIST // saves the address as well
  )
  @JoinColumn(unique = true, name = "address_id")
  private Address address;


  public Customer() {}

  public Customer(Address address) {
    this.address = address;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Customer [id=" + id + ", address=" + address + "]";
  }
}
