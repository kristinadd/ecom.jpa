package com.kristina.ecom.oms.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.kristina.ecom.oms.domain.Order;
import com.kristina.ecom.oms.service.OrderService;
import com.kristina.ecom.pms.service.ProductService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("oms/")
public class OmsController {

    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public OmsController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("{id}")
    public String getOrder(Model model, @PathVariable String id) {
        Order order = orderService.get(id);
        model.addAttribute("order", order);
        return "oms/order";
    }

    @GetMapping("all")
    public String getAllOrders(Model model) {
        List<Order> orders = new ArrayList<>();

        orders = orderService.getAll();
        model.addAttribute("orders", orders);

        return "oms/orders"; // orders.html
    }

    @PostMapping("delete/{id}")
    public String deleteOrder(@PathVariable String id) {
        orderService.delete(id);
        return "redirect:/oms/all";
    }

    @GetMapping("update/{id}")
    public String updateOrder(Model model, @PathVariable String id) {
        Order order = orderService.get(id);
        model.addAttribute("order", order);
        model.addAttribute("products", productService.getAll());

        return "oms/update";
    }

    @PostMapping("update/{id}")
    public String saveOrder(@ModelAttribute Order order, @PathVariable String id) {
        
        orderService.update(order);

        return "redirect:/oms/all";
    }
}
