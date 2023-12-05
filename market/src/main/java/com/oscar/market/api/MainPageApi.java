package com.oscar.market.api;

import com.oscar.market.model.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class MainPageApi {
    private final RestServiceUtil restServiceUtil;

    public MainPageApi(RestServiceUtil restServiceUtil) {
        this.restServiceUtil = restServiceUtil;
    }

    public ApiResponse getAllAttribute() {
        String url = "mainPage"; // Replace with your specific URL
        ApiResponse response = restServiceUtil.fetchData(url);
        return response;
    }
}
