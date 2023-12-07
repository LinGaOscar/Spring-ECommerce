package com.oscar.database.server;

import com.oscar.database.domain.ProductCategory;
import com.oscar.database.domain.ProductCategoryRepository;
import com.oscar.database.dto.ProductCategoryDTO;
import com.oscar.database.exception.NotFoundException;
import com.oscar.database.exception.ParameterException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public ProductCategory saveOneByDto(ProductCategoryDTO productCategoryDTO) {
        ProductCategory temp = productCategoryRepository.findByPid(productCategoryDTO.getPid());
        if (temp != null) {
            throw new ParameterException("pid重複無法新增");
        }
        return productCategoryRepository.save(productCategoryDTO.convertToProductCategory());
    }

    @Override
    public ProductCategory updateOne(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory updateOneByDto(ProductCategoryDTO productCategoryDTO) {
        ProductCategory temp = productCategoryRepository.findByPid(productCategoryDTO.getPid());
        if (temp == null) {
            throw new NotFoundException("無法更新");
        }
        productCategoryDTO.convertToProductCategory(temp);
        return productCategoryRepository.save(temp);
    }

    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll();
        if (productCategoryList.isEmpty()) {
            throw new NotFoundException("產品分類未找到");
        }
        return productCategoryList;
    }

    @Override
    public ProductCategory findOneByPid(String pid) {
        ProductCategory temp = productCategoryRepository.findByPid(pid);
        if (temp == null) {
            throw new NotFoundException("產品分類未找到");
        }
        return temp;
    }
}
