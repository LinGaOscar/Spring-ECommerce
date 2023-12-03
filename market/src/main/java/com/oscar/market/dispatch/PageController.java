package com.oscar.market.dispatch;

import com.oscar.market.model.NavItems;
import com.oscar.market.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {
    PageService pageService;
    @Autowired
    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

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
        // 判斷跟目錄返回預設
        if (page == null || page.isEmpty()) {
            return "productCategory";
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
        List<NavItems> navItems = new ArrayList<>();
        navItems.add(new NavItems(0,"首頁", "/"));
        navItems.add(new NavItems(1,"商品清單", "/productList"));
        navItems.add(new NavItems(2,"購物車", "/shoppingCart"));
        model.addAttribute("navItems", navItems);
    }
}
