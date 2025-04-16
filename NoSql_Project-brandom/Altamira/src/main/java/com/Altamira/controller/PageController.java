package com.Altamira.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class PageController {

    @GetMapping("/services")
    public String services() {
        return "services";
    }

    @GetMapping("/servicio")
    public String servicio() {
        return "servicio";
    }

    @GetMapping("/faqs")
    public String faqs() {
        return "faqs";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}