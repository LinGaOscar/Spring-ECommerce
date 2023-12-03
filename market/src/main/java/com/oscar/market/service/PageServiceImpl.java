package com.oscar.market.service;

import com.oscar.market.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class PageServiceImpl implements PageService {
    private final RestTemplate restTemplate;

    public PageServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private ApiResponse fetchData(String url) {
        ResponseEntity<ApiResponse> responseEntity = restTemplate.getForEntity(url, ApiResponse.class);
        ApiResponse response = responseEntity.getBody();

        // 檢查回應狀態碼等其他處理邏輯
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println("資料傳遞成功！");
        } else {
            System.out.println("傳遞失敗！狀態碼：" + responseEntity.getStatusCodeValue());
        }
        return response;
    }

    @Override
    public Map<String, Object> getNav() {
        ApiResponse navData = fetchData("url");
        return (Map<String, Object>) navData.getResultData();
    }

    @Override
    public Map<String, Object> getPageList() {
        ApiResponse pageListData = fetchData("url");
        return (Map<String, Object>) pageListData.getResultData();
    }
}
