package com.oscar.database.controller;

import com.oscar.database.domain.ProductCategory;
import com.oscar.database.dto.ProductCategoryDTO;
import com.oscar.database.model.ApiResponse;
import com.oscar.database.server.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<ApiResponse> getAllList() {
        try {
            List<ProductCategory> allList = productCategoryService.findAll();
            if (allList != null) {
                response = new ApiResponse("200", "正常", allList);
            } else {
                response = new ApiResponse("204", "查無資料", null);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse("500", "DB連線異常", null);
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveOne(@Valid @RequestBody ProductCategoryDTO productCategoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            response = new ApiResponse("422", "參數錯誤", (Map<String, Object>) productCategoryDTO);
        }
        try {
            ProductCategory productCategory = productCategoryService.saveOne(productCategoryDTO.convertToProductCategory());
            response = new ApiResponse("201", "新增成功", productCategory);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse("500", "DB連線異常", null);
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateOne(@PathVariable Long id, @Valid @RequestBody ProductCategoryDTO productCategoryDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            response = new ApiResponse("422", "參數錯誤", productCategoryDTO);
        }
        try{
            ProductCategory productCategory = productCategoryService.updateOne(productCategoryDTO.convertToProductCategory());
            response = new ApiResponse("200", "更新成功", productCategory);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            response = new ApiResponse("500", "DB連線異常", null);
            return ResponseEntity.ok(response);
        }
    }

}
