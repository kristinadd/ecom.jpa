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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kristina.ecom.domain.Product;
import com.kristina.ecom.service.ProductService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("ecom/pms")
public class WebController {

    // Field injection is not recommended. Use constructor injection instead.
    // @Autowired
    private final ProductService productService;

    // Constructor injection is recommended.
    @Autowired
    public WebController(ProductService productService) {
        this.productService = productService;
    }

    private static final Logger logger = LoggerFactory.getLogger(WebController.class);

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

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id, 
                              RedirectAttributes redirectAttributes) {
        logger.info("Delete request received for product ID: {}", id);
        
        try {
            Product product = productService.get(id);
            if (product == null) {
                logger.warn("Delete attempt failed - Product not found. ID: {}", id);
                redirectAttributes.addFlashAttribute("error", 
                    "Product not found. It may have been already deleted.");
                return "redirect:/ecom/pms/all";
            }

            productService.delete(id);
            
            logger.info("Product successfully deleted. ID: {}", id);
            redirectAttributes.addFlashAttribute("success", 
                "Product '" + product.getName() + "' has been successfully deleted.");
            
        } catch (Exception e) {
            logger.error("Error deleting product ID: {}. Error: {}", 
                id, e.getMessage(), e);
            
            redirectAttributes.addFlashAttribute("error", 
                "An error occurred while deleting the product. Please try again later.");
        }
        
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
