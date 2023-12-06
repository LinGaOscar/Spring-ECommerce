package com.oscar.database.dto;

import com.oscar.database.domain.ProductCategory;
import com.oscar.database.domain.WebPage;
import com.oscar.database.util.CustomBeanUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class WebPageDTO implements Serializable {
    @NotBlank(message = "title not blank")
    private String title;

    @NotBlank(message = "pageName not blank")
    private String pageName;
    private String description;

    @NotBlank(message = "pid not blank")
    private String pid;

    @NotBlank(message = "isDelete not blank")
    private String isDelete;

    // for update
    public void convertToWebPage(WebPage webPage) {
        new WebPageConvert().convert(this, webPage);
    }
    // for save
    public WebPage convertToWebPage(){
        return new WebPageConvert().convert(this);
    }

    private class WebPageConvert implements Convert<WebPageDTO,WebPage>{
        @Override
        public WebPage convert(WebPageDTO webPageDTO, WebPage webPage) {
            String[] nullPropertyNames = CustomBeanUtils.getNullPropertyNames(webPageDTO);
            BeanUtils.copyProperties(webPageDTO, webPage, nullPropertyNames);
            return webPage;
        }

        @Override
        public WebPage convert(WebPageDTO webPageDTO) {
            WebPage webPage = new WebPage();
            BeanUtils.copyProperties(webPageDTO, webPage);
            return webPage;
        }
    }
}
