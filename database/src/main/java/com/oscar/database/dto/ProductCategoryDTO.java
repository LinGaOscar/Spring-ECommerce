package com.oscar.database.dto;

import com.oscar.database.domain.ProductCategory;
import com.oscar.database.util.CustomBeanUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ProductCategoryDTO implements Serializable {
    @NotBlank(message = "title not blank")
    private String title;
    private String description;

    @NotBlank(message = "pid not blank")
    private String pid;

    @NotBlank(message = "isDelete not blank")
    private String isDelete;

    // for update
    public void convertToProductCategory(ProductCategory productCategory) {
        new productCategoryConvert().convert(this, productCategory);
    }
    // for save
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
