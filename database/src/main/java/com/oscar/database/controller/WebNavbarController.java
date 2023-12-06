package com.oscar.database.controller;

import com.oscar.database.domain.WebNavbar;
import com.oscar.database.dto.WebNavbarDTO;
import com.oscar.database.model.ApiResponse;
import com.oscar.database.server.WebNavbarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/webNavbar")
public class WebNavbarController {
    private final WebNavbarService webNavbarService;
    ApiResponse response;

    @Autowired
    public WebNavbarController(WebNavbarService webNavbarService) {
        this.webNavbarService = webNavbarService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllList() {
        try {
            List<WebNavbar> allList = webNavbarService.findAll();
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
            WebNavbar webNavbar = webNavbarService.findOneByPid(pid);
            if (webNavbar == null) {
                response = new ApiResponse("204", "查無資料", null);
            } else {
                response = new ApiResponse("200", "正常", webNavbar);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse("500", "DB連線異常", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveOne(@Valid @RequestBody WebNavbarDTO webNavbarDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            response = new ApiResponse("422", "參數錯誤", bindingResult.getFieldError().getDefaultMessage());
            return ResponseEntity.ok(response);
        }
        try {
            WebNavbar check = webNavbarService.findOneByPid(webNavbarDTO.getPid());
            if (check != null) {
                response = new ApiResponse("422", "pid重複無法新增", null);
                return ResponseEntity.ok(response);
            }
            WebNavbar webNavbarSaved = webNavbarService.saveOne(webNavbarDTO.convertToWebNavbar());
            response = new ApiResponse("201", "新增成功", webNavbarSaved);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse("500", "DB連線異常", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping()
    public ResponseEntity<ApiResponse> updateOne(@Valid @RequestBody WebNavbarDTO webNavbarDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            response = new ApiResponse("422", "參數錯誤", bindingResult.getFieldError().getDefaultMessage());
            return ResponseEntity.ok(response);
        }
        try {
            WebNavbar check = webNavbarService.findOneByPid(webNavbarDTO.getPid());
            if (check == null) {
                response = new ApiResponse("204", "查無資料無法更新", null);
                return ResponseEntity.ok(response);
            }
            webNavbarDTO.convertToWebNavbar(check);
            WebNavbar webNavbarUpdated = webNavbarService.updateOne(check);
            response = new ApiResponse("200", "更新成功", webNavbarUpdated);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse("500", "DB連線異常", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }
}
