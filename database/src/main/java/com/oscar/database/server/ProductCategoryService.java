package com.oscar.database.server;

import com.oscar.database.domain.ProductCategory;
import com.oscar.database.dto.ProductCategoryDTO;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory saveOne(ProductCategory productCategory);

    ProductCategory saveOneByDto(ProductCategoryDTO productCategoryDTO);

    ProductCategory updateOne(ProductCategory productCategory);

    ProductCategory updateOneByDto(ProductCategoryDTO productCategoryDTO);

    List<ProductCategory> findAll();

    ProductCategory findOneByPid(String pid);
}
