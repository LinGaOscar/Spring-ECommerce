package com.oscar.database.dto;

import com.oscar.database.domain.ProductCategory;
import com.oscar.database.util.CustomBeanUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ProductCategoryDTO implements Serializable {
    @NotBlank
    private String title;
    private String description;
    @NotBlank
    private String pid;
    @NotBlank
    private Boolean isDelete;

    public void convertToBook(ProductCategory productCategory) {
        new productCategoryConvert().convert(this, productCategory);
    }
    public ProductCategory convertToProductCategory(){
        return new productCategoryConvert().convert(this);
    }

    private class productCategoryConvert implements Convert<ProductCategoryDTO,ProductCategory>{
        @Override
        public ProductCategory convert(ProductCategoryDTO productCategoryDTO, ProductCategory productCategory) {
            String[] nullPropertyNames = CustomBeanUtils.getNullPropertyNames(productCategoryDTO);
            BeanUtils.copyProperties(productCategoryDTO, productCategory, nullPropertyNames);
            return productCategory;
        }

        @Override
        public ProductCategory convert(ProductCategoryDTO productCategoryDTO) {
            ProductCategory productCategory = new ProductCategory();
            BeanUtils.copyProperties(productCategoryDTO, productCategory);
            return productCategory;
        }
    }
}
