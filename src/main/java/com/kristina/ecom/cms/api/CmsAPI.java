package com.kristina.ecom.cms.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.kristina.ecom.cms.domain.Customer;
import com.kristina.ecom.cms.service.CustomerService;

@RestController
@RequestMapping("ecom/cms")
public class CmsAPI {
  private CustomerService service;

  public CmsAPI() {
    service = new CustomerService();
  }

  @PostMapping(value="/create", consumes="application/json")
  public int create(@RequestBody Customer customer) {
    return service.create(customer);
  }

  @GetMapping(value="/readall", produces="application/json") 
  public List<Customer> readAll() {
    return service.readAll();
  }

  @GetMapping(value="/read/{id}", produces="application/json")
  public Customer read(@PathVariable Long id) {
    return service.read(id);
  }

  @PutMapping(value="/update", produces="application/json")
  public int update (@RequestBody Customer customer) {
    return service.update(customer);
  }

  @DeleteMapping(value="/delete/{id}")
  public int delete(@PathVariable Long id) {
    return service.delete(id);
  }
}
