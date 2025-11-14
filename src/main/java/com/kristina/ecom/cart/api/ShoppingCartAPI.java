package com.kristina.ecom.cart.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kristina.ecom.cart.domain.ShoppingCart;
import com.kristina.ecom.cart.service.ShoppingCartService;

@RestController
@RequestMapping ("ecom/shoppingcart")
public class ShoppingCartAPI {
  private ShoppingCartService service;
  
  public ShoppingCartAPI() {
    service = new ShoppingCartService();
  }

  @GetMapping(value="/getall", produces="application/json")
  public List<ShoppingCart> getAll() {
    return service.readAll();
  }

  @GetMapping(value="/get/{id}", produces="application/json")
  public ShoppingCart get(@PathVariable String id) {
    return service.readId(id);
  }

  @GetMapping(value="/get_by_user/{user_id}", produces="application/json")
  public ShoppingCart get_by_user(@PathVariable String user_id) {
    return service.read(user_id);
  }

  @PostMapping(value="/create", consumes="application/json")
  public int create(@RequestBody ShoppingCart shoppingCart) {
    return service.create(shoppingCart);
  }

  @DeleteMapping(value="/delete/{id}")
  public int delete(@PathVariable String id) {
    return service.delete(id);
  }

  @PutMapping(value="/update", produces="application/json")
  public int update (@RequestBody ShoppingCart shoppingCart) {
    service.update(shoppingCart);
    return 1;
  }

  @PutMapping(value="/cancel", produces="application/json", consumes="application/json")
  public int cancel(@RequestBody ShoppingCart shoppingCart) {
    service.cancel(shoppingCart);
    return 1;
  }
}
