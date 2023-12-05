package com.oscar.manage.outbound;

import com.oscar.manage.model.ApiResponse;
import com.oscar.manage.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/restfulApi/mainPage")
@RestController
public class MainPageController {
    private final MainPageService mainPageService;
    ApiResponse response;

    @Autowired
    public MainPageController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getMainPageAttribute(){
        System.out.println("getMainPageAttribute");
        try {
            Map<String, Object> allAttribute = mainPageService.getMainPageAttribute();
            if (allAttribute != null) {
                response = new ApiResponse("200", "正常", allAttribute);
            } else {
                response = new ApiResponse("204", "查無資料", null);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse("500", "DB連線異常", null);
            return ResponseEntity.ok(response);
        }
    }
}
