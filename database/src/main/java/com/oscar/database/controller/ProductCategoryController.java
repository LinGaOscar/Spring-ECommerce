package com.oscar.database.controller;

import com.oscar.database.domain.ProductCategory;
import com.oscar.database.model.ApiResponse;
import com.oscar.database.server.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/productCategory")
@RestController
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;
    ApiResponse response;

    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProductCategory() {
        try {
            List<ProductCategory> allList = productCategoryService.findAll();
            if (allList != null) {
                response = new ApiResponse("200", "正常", allList);
            } else {
                response = new ApiResponse("204", "查無資料", null);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse("500", "連線異常", null);
            return ResponseEntity.ok(response);
        }
    }
}
