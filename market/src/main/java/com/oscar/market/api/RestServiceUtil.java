package com.oscar.market.api;

import com.oscar.market.model.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestServiceUtil {
    private final RestTemplate restTemplate;

    public RestServiceUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${manage.server}")
    private String manageServerUrl;

    public ApiResponse fetchData(String url) {
        ResponseEntity<ApiResponse> responseEntity = restTemplate.getForEntity(manageServerUrl + url, ApiResponse.class);
        ApiResponse response = responseEntity.getBody();

        // 檢查回應狀態碼等其他處理邏輯
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println("資料傳遞成功！");
        } else {
            System.out.println("傳遞失敗！狀態碼：" + responseEntity.getStatusCodeValue());
        }
        return response;
    }
}
