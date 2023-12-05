package com.oscar.manage.service;

import com.oscar.manage.domain.Navbar;
import com.oscar.manage.domainConvert.NavbarConverter;
import com.oscar.manage.model.NavItem;
import com.oscar.manage.model.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainPageServiceImpl implements MainPageService {
    private final NavbarService navbarService;

    @Autowired
    public MainPageServiceImpl(NavbarService navbarService) {
        this.navbarService = navbarService;
    }

    @Override
    public Map<String, Object> getMainPageAttribute() {
        List<Navbar> navbarList = navbarService.findAll();
        List<NavItem> navItemList = NavbarConverter.convertToNavItemList(navbarList);

        List<PageList> pageList = new ArrayList<>();
        pageList.add(new PageList("商品清單", "productList"));
        pageList.add(new PageList("購物車", "shoppingCart"));

        Map<String, Object> result = new HashMap<>();
        result.put("navItemList", navItemList);
        result.put("pageList", pageList);
        return result;
    }
}
