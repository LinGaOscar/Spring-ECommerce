package com.oscar.database.server;

import com.oscar.database.domain.Product;
import com.oscar.database.domain.ProductCategory;

import java.util.List;

public interface ProductService {
    Product saveOne(Product product);

    Product updateOne(Product product);

    List<Product> findAll();

    Product findOneByPid(String pid);
}
