package com.oscar.manage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageList implements Serializable {
    private String pageTitle;
    private String pageName;
}
