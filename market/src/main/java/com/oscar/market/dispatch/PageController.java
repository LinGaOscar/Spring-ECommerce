package com.oscar.market.dispatch;

import com.oscar.market.model.NavItems;
import com.oscar.market.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {
    private final MainPageService mainPageService;
    private static final String DEFAULT_PAGE = "productCategory";
    private static final String REDIRECT = "redirect:";

    @Autowired
    public PageController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

    @GetMapping({"", "/", "/{page}"})
    public String home(Model model, @PathVariable(required = false) String page) {
        String pageName = pageFilter(page);

        if (pageName.equals(REDIRECT)) {
            return "redirect:/"; // 導向根目錄
        }

        buildPageContents(model, pageName);
        return "main";
    }

    private void buildPageContents(Model model, String pageName) {
        Map<String, Object> allAttributes = mainPageService.getAllAttribute();
        List<Map<String, Object>> navItemList = (List<Map<String, Object>>) allAttributes.get("navItemList");

        buildHeader(model, navItemList);
        buildContents(model, pageName);
        buildFooter(model);
    }

    private void buildHeader(Model model, List<Map<String, Object>> navItemList) {
        buildNav(model, navItemList);
        model.addAttribute("headerTemplate", "headers/header");
    }

    private void buildContents(Model model, String pageName) {
        model.addAttribute("contentTemplate", "contents/" + pageName);
    }

    private void buildFooter(Model model) {
        model.addAttribute("footerTemplate", "footers/footer");
    }

    private void buildNav(Model model, List<Map<String, Object>> navItemList) {
        model.addAttribute("navItems", navItemList);
    }

    private String pageFilter(String page) {
        if (page == null || page.isEmpty()) {
            return DEFAULT_PAGE;
        }

        if (page.equals("productList") || page.equals("shoppingCart")) {
            return page;
        } else {
            return REDIRECT;
        }
    }
}