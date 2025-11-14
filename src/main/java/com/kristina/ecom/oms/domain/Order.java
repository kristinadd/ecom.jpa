package com.kristina.ecom.oms.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.kristina.ecom.pms.domain.Computer;
import com.kristina.ecom.pms.domain.ComputerBase;
import com.kristina.ecom.pms.domain.Product;

public class Order {
  private static final int SIZE = 10000;
  private static List<Integer> ids = new Random().ints(1, SIZE+1)
  .distinct().limit(SIZE).boxed().collect((Collectors.toList()));

  private String id;
  private LocalDateTime date;
  private Computer computer;
  private String description;
  private double total;

  public Order() {
    this.computer = new ComputerBase();
    this.date = LocalDateTime.now();
    this.description = "";
  }
  
  // Create order from computer
  public Order(Computer computer) {
    this( // chaining
      getID(),
      LocalDateTime.now(),
      computer
    );
  }

  public Order(String id, LocalDateTime date, Computer computer) {
    this.id = id;
    this.date = date;
    this.computer = computer;
    this.description = computer.getDescription();
    this.total = computer.getPrice();
  }

  // Reading order from the database
  public Order(String id, LocalDateTime date, List<Product> products, String description, double total) {
    this.id = id;
    this.date = date;
    this.computer = new ComputerBase(products);
    this.description = description;
    this.total = total;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  // public String getDescription() {
  //   return computer.getDescription();
  // }

  public String getDescription() {
    return description;
  }

  public void update() {
    ((ComputerBase)this.computer).update();
    this.description = computer.getDescription();
    this.total = computer.getPrice();
    this.setDate(LocalDateTime.now());
  }

  // public double getTotal() {
  //   return computer.getPrice();
  // }

  public double getTotal() {
    return total;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public Computer getComputer() {
    return computer;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public List<Product> getProducts() {
    return computer.getComponents();
  }

  public void setProducts(List<Product> products) {
    this.computer.setComponents(products);
  }

  @Override
  public String toString() {
    return String.format("OrderID@%s: %s $%.2f", this.id, this.description, this.total);
  }

  private static String getID() {
    return Integer.toString(ids.remove(0));
  }
}
