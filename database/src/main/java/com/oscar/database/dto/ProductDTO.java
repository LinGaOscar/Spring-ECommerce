package com.oscar.database.dto;

import com.oscar.database.domain.Product;
import com.oscar.database.domain.WebPage;
import com.oscar.database.util.CustomBeanUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ProductDTO implements Serializable {
    @NotBlank(message = "title not blank")
    private String title;
    private String description;
    private String categoryPid;

    @NotBlank(message = "pid not blank")
    private String pid;

    @NotBlank(message = "isDelete not blank")
    private String isDelete;

    // for update
    public void convertToPruduct(Product product) {
        new ProductConvert().convert(this, product);
    }

    // for save
    public Product convertToPruduct() {
        return new ProductConvert().convert(this);
    }

    private class ProductConvert implements Convert<ProductDTO, Product> {
        @Override
        public Product convert(ProductDTO productDTO, Product product) {
            String[] nullPropertyNames = CustomBeanUtils.getNullPropertyNames(productDTO);
            BeanUtils.copyProperties(productDTO, product, nullPropertyNames);
            return product;
        }

        @Override
        public Product convert(ProductDTO productDTO) {
            Product product = new Product();
            BeanUtils.copyProperties(productDTO, product);
            return product;
        }
    }
}
