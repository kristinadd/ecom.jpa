package com.kristina.ecom.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.kristina.ecom.domain.Product;
import com.kristina.ecom.service.ProductService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("ecom/pms")
public class WebController {
    // ProductService productService = new ProductService();
    @Autowired
    ProductService productService;

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @GetMapping("/{id}")
    public String getProduct(Model model, @PathVariable int id) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        List<Product> products = new ArrayList<>();

        products = productService.getAll();
        model.addAttribute("products", products);

        return "products";
    }

    //  It would be better to use a POST or DELETE request for this operation.
    // @GetMapping("/delete/{id}")
    // public String deleteProduct(Model model, @PathVariable int id) {
    //     int rows = productService.delete(id);
    //     model.addAttribute("product", rows);
    //     return "redirect:/ecom/pms/all";
    // }

    @DeleteMapping("/delete/{id}")  // or @PostMapping if DELETE isn't supported
    public String deleteProduct(@PathVariable int id) {
    productService.delete(id);
    return "redirect:/ecom/pms/all";
}

    @GetMapping("/create")
    public String showProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "create";
    }


    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product) {
        productService.create(product);
        return "redirect:/ecom/pms/all";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(Model model, @PathVariable int id) {
        Product product = productService.get(id);
        model.addAttribute("product", product);

        return "update";
    }

    @PostMapping("/update/{id}")
    public String saveProduct(@ModelAttribute Product product, @PathVariable int id) {
        product.setId(id);
        productService.update(product);

        return "redirect:/ecom/pms/all";
    }
}
