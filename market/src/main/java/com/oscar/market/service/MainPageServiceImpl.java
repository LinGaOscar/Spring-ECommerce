package com.oscar.market.service;

import com.oscar.market.api.MainPageApi;
import com.oscar.market.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class MainPageServiceImpl implements MainPageService {
    private MainPageApi mainPageApi;
    @Autowired
    public MainPageServiceImpl(MainPageApi mainPageApi) {
        this.mainPageApi = mainPageApi;
    }


    @Override
    public Map<String, Object> getAllAttribute() {
        Map<String, Object> resultDate = (Map<String, Object>) mainPageApi.getAllAttribute().getResultData();
        return resultDate;
    }
}
