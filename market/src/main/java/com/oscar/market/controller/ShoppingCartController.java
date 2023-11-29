package com.oscar.market.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/rest/shoppingCart")
@RestController
public class ShoppingCartController {
    @GetMapping()
    public Map<String, Object> getShoppingCart() {
        Map<String, Object> result = new HashMap<>();

        return result;
    }
}
