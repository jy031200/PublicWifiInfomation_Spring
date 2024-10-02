package com.example.spring_controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/showJsp")
    public String showJspPage(Model model) {
        model.addAttribute("message", "Hello from Spring Boot Controller!");
        System.out.println("AAAAAAA");
        return "create_post"; // JSP 파일 이름 (확장자 제외)
    }
}

