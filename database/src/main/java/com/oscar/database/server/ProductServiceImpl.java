package com.oscar.database.server;

import com.oscar.database.domain.Product;
import com.oscar.database.domain.ProductRepository;
import com.oscar.database.dto.ProductDTO;
import com.oscar.database.exception.NotFoundException;
import com.oscar.database.exception.ParameterException;
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
    public Product saveOneByDto(ProductDTO productDTO) {
        Product temp = productRepository.findByPid(productDTO.getPid());
        if (temp != null) {
            throw new ParameterException("pid重複無法新增");
        }
        return productRepository.save(productDTO.convertToPruduct());
    }

    @Override
    public Product updateOne(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateOneByDto(ProductDTO productDTO) {
        Product temp = productRepository.findByPid(productDTO.getPid());
        if (temp == null) {
            throw new NotFoundException("無法更新");
        }
        productDTO.convertToPruduct(temp);
        return productRepository.save(temp);
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()) {
            throw new NotFoundException("產品分類未找到");
        }
        return productList;
    }

    @Override
    public Product findOneByPid(String pid) {
        Product temp = productRepository.findByPid(pid);
        if (temp == null) {
            throw new NotFoundException("該產品未找到");
        }
        return temp;
    }
}
