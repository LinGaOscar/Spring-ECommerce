package com.oscar.manage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NavItem implements Serializable {
    private Number sequence;
    private String name;
    private String url;


}
