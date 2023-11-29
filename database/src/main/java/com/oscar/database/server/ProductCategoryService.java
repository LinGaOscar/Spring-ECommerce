package com.oscar.database.server;

import com.oscar.database.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory saveOne(ProductCategory productCategory);

    ProductCategory updateOne(ProductCategory productCategory);

    List<ProductCategory> findAll();

    ProductCategory getOneById(Long Id);
}
