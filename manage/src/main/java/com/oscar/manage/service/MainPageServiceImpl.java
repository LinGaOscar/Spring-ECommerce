package com.oscar.manage.service;

import com.oscar.manage.model.NavItemList;
import com.oscar.manage.model.PageList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainPageServiceImpl implements MainPageService{
    @Override
    public Map<String, Object> getMainPageAttribute() {
        List<NavItemList> navItemList = new ArrayList<>();
        navItemList.add(new NavItemList(0,"首頁", "/"));
        navItemList.add(new NavItemList(1,"商品清單", "/productList"));
        navItemList.add(new NavItemList(2,"購物車", "/shoppingCart"));


        List<PageList> pageList = new ArrayList<>();
        pageList.add(new PageList("商品清單", "productList"));
        pageList.add(new PageList("購物車", "shoppingCart"));

        Map<String, Object> result = new HashMap<>();
        result.put("navItemList",navItemList);
        result.put("pageList",pageList);
        return result;
    }
}
