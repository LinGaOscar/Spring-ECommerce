package com.oscar.database.controller;

import com.oscar.database.domain.ProductCategory;
import com.oscar.database.dto.ProductCategoryDTO;
import com.oscar.database.exception.ParameterException;
import com.oscar.database.model.ApiResponse;
import com.oscar.database.server.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllList() {
        List<ProductCategory> allList = productCategoryService.findAll();
        return ResponseEntity.ok(new ApiResponse("200", "正常", allList));
    }

    @GetMapping("/{pid}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable String pid) {
        ProductCategory productCategory = productCategoryService.findOneByPid(pid);
        return ResponseEntity.ok(new ApiResponse("200", "正常", productCategory));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveOne(@Valid @RequestBody ProductCategoryDTO productCategoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ParameterException(bindingResult.getFieldError().getDefaultMessage());
        }
        ProductCategory productCategorySaved = productCategoryService.saveOneByDto(productCategoryDTO);
        return ResponseEntity.ok(new ApiResponse("201", "新增成功", productCategorySaved));
    }

    @PutMapping()
    public ResponseEntity<ApiResponse> updateOne(@Valid @RequestBody ProductCategoryDTO productCategoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ParameterException(bindingResult.getFieldError().getDefaultMessage());
        }
        ProductCategory productCategoryUpdated = productCategoryService.updateOneByDto(productCategoryDTO);
        return ResponseEntity.ok(new ApiResponse("200", "更新成功", productCategoryUpdated));
    }
}