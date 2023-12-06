package com.oscar.database.controller;

import com.oscar.database.domain.Product;
import com.oscar.database.dto.ProductDTO;
import com.oscar.database.model.ApiResponse;
import com.oscar.database.server.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {
    private final ProductService productService;
    ApiResponse response;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllList() {
        try {
            List<Product> allList = productService.findAll();
            if (allList == null) {
                response = new ApiResponse("204", "查無資料", null);
            } else {
                response = new ApiResponse("200", "正常", allList);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse("500", "DB連線異常", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/{pid}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable String pid) {
        try {
            Product productCategory = productService.findOneByPid(pid);
            if (productCategory == null) {
                response = new ApiResponse("204", "查無資料", null);
            } else {
                response = new ApiResponse("200", "正常", productCategory);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse("500", "DB連線異常", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }


    @PostMapping
    public ResponseEntity<ApiResponse> saveOne(@Valid @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            response = new ApiResponse("422", "參數錯誤", bindingResult.getFieldError().getDefaultMessage());
            return ResponseEntity.ok(response);
        }
        try {
            Product check = productService.findOneByPid(productDTO.getPid());
            if (check != null) {
                response = new ApiResponse("422", "pid重複無法新增", null);
                return ResponseEntity.ok(response);
            }
            Product productSaved = productService.saveOne(productDTO.convertToPruduct());
            response = new ApiResponse("201", "新增成功", productSaved);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse("500", "DB連線異常", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping()
    public ResponseEntity<ApiResponse> updateOne(@PathVariable String id, @Valid @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            response = new ApiResponse("422", "參數錯誤", bindingResult.getFieldError().getDefaultMessage());
            return ResponseEntity.ok(response);
        }
        try {
            Product check = productService.findOneByPid(id);
            if (check == null) {
                response = new ApiResponse("204", "查無資料無法更新", null);
                return ResponseEntity.ok(response);
            }
            productDTO.convertToPruduct(check);
            Product productUpdated = productService.updateOne(check);
            response = new ApiResponse("200", "更新成功", productUpdated);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse("500", "DB連線異常", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }
}
