package com.oscar.manage.controller;

import com.oscar.manage.domain.Navbar;
import com.oscar.manage.service.NavbarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/rest/navbar")
@RestController
public class NavbarController {
    private final NavbarService navbarService;

    @Autowired
    public NavbarController(NavbarService navbarService) {
        this.navbarService = navbarService;
    }

    @GetMapping
    public Map<String, Object> getNavbar() {
        Map<String, Object> result = new HashMap<>();
        List<Navbar> NavbarList = navbarService.findAll();
        result.put("resultData",NavbarList);
        return result;
    }
}
