package com.oscar.database.server;

import com.oscar.database.domain.Product;
import com.oscar.database.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void autowired(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveOne(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateOne(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findOneByPid(String pid) {
        return productRepository.findByPid(pid);
    }
}
