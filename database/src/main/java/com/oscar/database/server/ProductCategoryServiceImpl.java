package com.oscar.database.server;

import com.oscar.database.domain.ProductCategory;
import com.oscar.database.domain.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    public void autowired(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public ProductCategory saveOne(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory updateOne(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory getOneById(Long id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElse(null);
        return productCategory;
    }
}
