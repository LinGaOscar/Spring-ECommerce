package com.oscar.database.controller;

import com.oscar.database.domain.Product;
import com.oscar.database.dto.ProductDTO;
import com.oscar.database.exception.ParameterException;
import com.oscar.database.model.ApiResponse;
import com.oscar.database.server.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllList() {
        List<Product> allList = productService.findAll();
        return ResponseEntity.ok(new ApiResponse("200", "正常", allList));
    }

    @GetMapping("/{pid}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable String pid) {
        Product productCategory = productService.findOneByPid(pid);
        return ResponseEntity.ok(new ApiResponse("200", "正常", productCategory));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveOne(@Valid @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ParameterException(bindingResult.getFieldError().getDefaultMessage());
        }
        Product productSaved = productService.saveOneByDto(productDTO);
        return ResponseEntity.ok(new ApiResponse("201", "新增成功", productSaved));
    }

    @PutMapping()
    public ResponseEntity<ApiResponse> updateOne(@Valid @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ParameterException(bindingResult.getFieldError().getDefaultMessage());
        }
        Product productUpdated = productService.updateOneByDto(productDTO);
        return ResponseEntity.ok(new ApiResponse("200", "更新成功", productUpdated));
    }
}