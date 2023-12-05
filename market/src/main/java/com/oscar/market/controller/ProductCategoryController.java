package com.oscar.market.controller;

import com.oscar.market.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/rest/productCategory")
@RestController
public class ProductCategoryController {
    @GetMapping()
    public Map<String, Object> getProductCategory() {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> categories = new ArrayList<>();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("title", "3C");
        item1.put("description", "Electronics, Computers, Cameras, etc.");
        item1.put("pid","00001");
        categories.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("title", "Life");
        item2.put("description", "Home, Lifestyle, Kitchen, etc.");
        item2.put("pid","00002");
        categories.add(item2);

        result.put("resultData", categories);
        return result;
    }
}
