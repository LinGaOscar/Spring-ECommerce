package com.oscar.database.server;

import com.oscar.database.domain.Product;
import com.oscar.database.domain.ProductCategory;
import com.oscar.database.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    Product saveOne(Product product);

    Product saveOneByDto(ProductDTO productDTO);

    Product updateOne(Product product);

    Product updateOneByDto(ProductDTO productDTO);

    List<Product> findAll();

    Product findOneByPid(String pid);
}
