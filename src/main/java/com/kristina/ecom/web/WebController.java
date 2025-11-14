package com.kristina.ecom.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
//@RequestMapping("ecom/admin")
public class WebController {
    @Autowired
    public WebController() {}

    @GetMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }    
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Get existing session, don't create new one
        if (session != null) 
            session.invalidate(); // Invalidate the session
        
        return "redirect:/login?logout"; // Redirect to login page with logout success message
    }
}
