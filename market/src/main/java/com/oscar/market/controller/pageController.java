package com.oscar.market.controller;

import com.oscar.market.model.NavItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class pageController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("headerTemplate", "headers/header");
        model.addAttribute("contentTemplate", "contents/index");
        model.addAttribute("footerTemplate", "footers/footer");

        // 動態生成連結的清單
        List<NavItem> navItems = new ArrayList<>();
        navItems.add(new NavItem("首頁", "/"));
        navItems.add(new NavItem("商品1", "/product1"));
        navItems.add(new NavItem("商品2", "/product2"));
        model.addAttribute("navItems", navItems);
        return "main";
    }
}
