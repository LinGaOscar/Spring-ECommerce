package com.oscar.database.server;

import com.oscar.database.domain.ProductCategory;
import com.oscar.database.domain.ProductCategoryRepository;
import com.oscar.database.dto.ProductCategoryDTO;
import com.oscar.database.exception.NotFoundException;
import com.oscar.database.exception.ParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

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

    @Caching(
            put = {@CachePut(value = "productCategory", key = "#productCategoryDTO.getPid()")},
            evict = {@CacheEvict(value = "productCategoryList", allEntries = true)}
    )
    @Override
    public ProductCategory saveOneByDto(ProductCategoryDTO productCategoryDTO) {
        String pid = productCategoryDTO.getPid();
        ProductCategory temp = productCategoryRepository.findByPid(pid);
        if (temp != null) {
            throw new ParameterException("pid:" + pid + "，重複無法新增");
        }
        return productCategoryRepository.save(productCategoryDTO.convertToProductCategory());
    }

    @Override
    public ProductCategory updateOne(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @CachePut(value = "productCategory", key = "#productCategoryDTO.getPid()")
    @CacheEvict(value = "productCategoryList", allEntries = true)
    @Override
    public ProductCategory updateOneByDto(ProductCategoryDTO productCategoryDTO) {
        String pid = productCategoryDTO.getPid();
        ProductCategory temp = productCategoryRepository.findByPid(pid);
        if (temp == null) {
            throw new NotFoundException("pid:" + pid + "不存在無法更新");
        }
        productCategoryDTO.convertToProductCategory(temp);
        return productCategoryRepository.save(temp);
    }

    @Cacheable(value = "productCategoryList")
    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll();
        if (productCategoryList.isEmpty()) {
            throw new NotFoundException("產品分類未找到");
        }
        return productCategoryList;
    }

    @Cacheable(value = "productCategory", key = "#pid")
    @Override
    public ProductCategory findOneByPid(String pid) {
        ProductCategory temp = productCategoryRepository.findByPid(pid);
        if (temp == null) {
            throw new NotFoundException("產品分類未找到");
        }
        return temp;
    }
}
