package com.pim.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "/pages/homepage.html"; // default homepage
    }

    @GetMapping("/product/page/add")
    public String serveAddProductPage() {
        return "/pages/form.html";
    }

    @GetMapping("/product/page/edit")
    public String serveEditProductPage() {
        return "/pages/update.html";
    }

    @GetMapping("/product/page/add-attribute")
    public String serveAddAttributePage() {
        return "/pages/attribute.html";
    }
}
