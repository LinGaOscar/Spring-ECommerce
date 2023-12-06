package com.oscar.database.controller;

import com.oscar.database.domain.WebPage;
import com.oscar.database.dto.WebPageDTO;
import com.oscar.database.model.ApiResponse;
import com.oscar.database.server.WebPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/webPage")
public class WebPageController {
    private final WebPageService webPageService;
    ApiResponse response;

    @Autowired
    public WebPageController(WebPageService webPageService) {
        this.webPageService = webPageService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllList() {
        try {
            List<WebPage> allList = webPageService.findAll();
            if (allList == null) {
                response = new ApiResponse("204", "查無資料", null);
            } else {
                response = new ApiResponse("200", "正常", allList);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse("500", "DB連線異常", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/{pid}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable String pid) {
        try {
            WebPage webPage = webPageService.findOneByPid(pid);
            if (webPage == null) {
                response = new ApiResponse("204", "查無資料", null);
            } else {
                response = new ApiResponse("200", "正常", webPage);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse("500", "DB連線異常", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveOne(@Valid @RequestBody WebPageDTO webPageDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            response = new ApiResponse("422", "參數錯誤", bindingResult.getFieldError().getDefaultMessage());
            return ResponseEntity.ok(response);
        }
        try {
            WebPage check = webPageService.findOneByPid(webPageDTO.getPid());
            if (check != null) {
                response = new ApiResponse("422", "pid重複無法新增", null);
                return ResponseEntity.ok(response);
            }
            WebPage webPageSaved = webPageService.saveOne(webPageDTO.convertToWebPage());
            response = new ApiResponse("201", "新增成功", webPageSaved);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse("500", "DB連線異常", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping()
    public ResponseEntity<ApiResponse> updateOne(@Valid @RequestBody WebPageDTO webPageDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            response = new ApiResponse("422", "參數錯誤", bindingResult.getFieldError().getDefaultMessage());
            return ResponseEntity.ok(response);
        }
        try {
            WebPage check = webPageService.findOneByPid(webPageDTO.getPid());
            if (check == null) {
                response = new ApiResponse("204", "查無資料無法更新", null);
                return ResponseEntity.ok(response);
            }
            webPageDTO.convertToWebPage(check);
            WebPage webPageUpdated = webPageService.updateOne(check);
            response = new ApiResponse("200", "更新成功", webPageUpdated);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse("500", "DB連線異常", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }
}
