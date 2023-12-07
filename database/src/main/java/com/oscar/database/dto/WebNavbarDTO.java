package com.oscar.database.dto;

import com.oscar.database.domain.WebNavbar;
import com.oscar.database.util.CustomBeanUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class WebNavbarDTO implements Serializable {
    @NotBlank(message = "title not blank")
    private String title;

    @NotNull(message = "sequence not blank")
    private Number sequence;
    private String url;

    @NotBlank(message = "pid not blank")
    private String pid;

    @NotBlank(message = "isDelete not blank")
    private String isDelete;

    // for update
    public void convertToWebNavbar(WebNavbar webNavbar) {
        new WebNavbarConvert().convert(this, webNavbar);
    }

    // for save
    public WebNavbar convertToWebNavbar() {
        return new WebNavbarConvert().convert(this);
    }

    private class WebNavbarConvert implements Convert<WebNavbarDTO, WebNavbar> {
        @Override
        public WebNavbar convert(WebNavbarDTO webNavbarDTO, WebNavbar webNavbar) {
            String[] nullPropertyNames = CustomBeanUtils.getNullPropertyNames(webNavbarDTO);
            BeanUtils.copyProperties(webNavbarDTO, webNavbar, nullPropertyNames);
            return webNavbar;
        }

        @Override
        public WebNavbar convert(WebNavbarDTO webNavbarDTO) {
            WebNavbar webNavbar = new WebNavbar();
            BeanUtils.copyProperties(webNavbarDTO, webNavbar);
            return webNavbar;
        }
    }
}
