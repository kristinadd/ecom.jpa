package com.kristina.ecom.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.kristina.ecom.domain.Product;
import com.kristina.ecom.service.ProductService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("pms/")
public class PmsController {
  
  private final ProductService productService;

  @Autowired
    public PmsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public String getProduct(Model model, @PathVariable int id) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "pms/product";
    }

    @GetMapping("all")
    public String getAllProducts(Model model) {
        List<Product> products = new ArrayList<>();

        products = productService.getAll();
        model.addAttribute("products", products);

        return "pms/products";
    }

    @PostMapping("delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/ecom/admin/pms/all";
    }

    @GetMapping("create")
    public String showProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        model.addAttribute("types", productService.getTypes());

        return "pms/create";
    }

    @PostMapping("create")
    public String createProduct(@ModelAttribute Product product) {
        productService.create(product);
        return "redirect:/ecom/admin/pms/all";
    }

    @GetMapping("update/{id}")
    public String updateProduct(Model model, @PathVariable int id) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        model.addAttribute("types", productService.getTypes());

        return "pms/update";
    }

    @PostMapping("update/{id}")
    public String saveProduct(@ModelAttribute Product product, @PathVariable int id) {
        product.setId(id);
        productService.update(product);

        return "redirect:/ecom/admin/pms/all";
    }
}
