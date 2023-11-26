package com.oscar.market.controller;

import com.oscar.market.model.NavItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class pageController {
    @GetMapping({"", "/", "/{page}"})
    public String home(Model model, @PathVariable(required = false) String page) {
        String pageName = pageFilter(page);

        if (pageName.equals("redirect:")) {
            return "redirect:/"; // 導向根目錄
        }
        buildPage(model, pageName);
        return "main";
    }

    private void buildPage(Model model, String pageName) {
        buildHeader(model, pageName);
        buildContents(model, pageName);
        buildFooter(model, pageName);
    }

    private String pageFilter(String page) {
        // 判斷跟目錄
        if (page == null || page.isEmpty()) {
            return "index";
        }
        // 判斷現有頁面
        if (page.equals("productList") || page.equals("shoppingCart")) {
            return page;
        } else {
            return "redirect:";
        }
    }

    private void buildFooter(Model model, String pageName) {
        model.addAttribute("footerTemplate", "footers/footer");
    }

    private void buildContents(Model model, String pageName) {
        model.addAttribute("contentTemplate", "contents/" + pageName);
    }

    private void buildHeader(Model model, String pageName) {
        buildNav(model, pageName);
        model.addAttribute("headerTemplate", "headers/header");
    }

    private void buildNav(Model model, String pageName) {
        if (pageName.equals("")) {

        }
        // 動態生成連結的清單
        List<NavItem> navItems = new ArrayList<>();
        navItems.add(new NavItem("首頁", "/"));
        navItems.add(new NavItem("商品清單", "/productList"));
        navItems.add(new NavItem("購物車", "/shoppingCart"));
        model.addAttribute("navItems", navItems);
    }
}
