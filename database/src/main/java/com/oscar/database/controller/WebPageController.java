package com.oscar.database.controller;

import com.oscar.database.domain.WebPage;
import com.oscar.database.dto.WebPageDTO;
import com.oscar.database.exception.ParameterException;
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
    @Autowired
    private WebPageService webPageService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllList() {
        List<WebPage> allList = webPageService.findAll();
        return ResponseEntity.ok(new ApiResponse("200", "正常", allList));
    }

    @GetMapping("/{pid}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable String pid) {
        WebPage webPage = webPageService.findOneByPid(pid);
        return ResponseEntity.ok(new ApiResponse("200", "正常", webPage));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveOne(@Valid @RequestBody WebPageDTO webPageDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ParameterException(bindingResult.getFieldError().getDefaultMessage());
        }
        WebPage webPageSaved = webPageService.saveOneByDto(webPageDTO);
        return ResponseEntity.ok(new ApiResponse("201", "新增成功", webPageSaved));
    }

    @PutMapping()
    public ResponseEntity<ApiResponse> updateOne(@Valid @RequestBody WebPageDTO webPageDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ParameterException(bindingResult.getFieldError().getDefaultMessage());
        }
        WebPage webPageUpdated = webPageService.updateOneByDto(webPageDTO);
        return ResponseEntity.ok(new ApiResponse("200", "更新成功", webPageUpdated));
    }
}