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

import com.kristina.ecom.domain.Order;
import com.kristina.ecom.service.OrderService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("ecom/admin/oms/")
public class OmsController {

    private final OrderService orderService;

    @Autowired
    public OmsController(OrderService orderService) {
        this.orderService = orderService;
    }

    // private static final Logger logger = LoggerFactory.getLogger(WebController.class);


    @GetMapping("{id}")
    public String getOrder(Model model, @PathVariable String id) {
        Order order = orderService.get(id);
        model.addAttribute("order", order);
        return "order";
    }

    @GetMapping("all")
    public String getAllOrders(Model model) {
        List<Order> orders = new ArrayList<>();

        orders = orderService.getAll();
        model.addAttribute("orders", orders);

        return "orders"; // orders.html
    }

    @PostMapping("delete/{id}")
    public String deleteOrder(@PathVariable String id) {
        orderService.delete(id);
        return "redirect:/ecom/admin/oms/all";
    }

    @GetMapping("update/{id}")
    public String updateOrder(Model model, @PathVariable String id) {
        Order order = orderService.get(id);
        model.addAttribute("order", order);

        return "update";
    }

    @PostMapping("update/{id}")
    public String saveOrder(@ModelAttribute Order order, @PathVariable String id) {
        
        orderService.update(order);

        return "redirect:/ecom/admin/oms/all";
    }
}
