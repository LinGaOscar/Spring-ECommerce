package com.oscar.database.controller;

import com.oscar.database.domain.WebNavbar;
import com.oscar.database.dto.WebNavbarDTO;
import com.oscar.database.exception.ParameterException;
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
    @Autowired
    private WebNavbarService webNavbarService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllList() {
        List<WebNavbar> allList = webNavbarService.findAll();
        return ResponseEntity.ok(new ApiResponse("200", "正常", allList));
    }

    @GetMapping("/{pid}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable String pid) {
        WebNavbar webNavbar = webNavbarService.findOneByPid(pid);
        return ResponseEntity.ok(new ApiResponse("200", "正常", webNavbar));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveOne(@Valid @RequestBody WebNavbarDTO webNavbarDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ParameterException(bindingResult.getFieldError().getDefaultMessage());
        }
        WebNavbar webNavbarSaved = webNavbarService.saveOneByDto(webNavbarDTO);
        return ResponseEntity.ok(new ApiResponse("201", "新增成功", webNavbarSaved));
    }

    @PutMapping()
    public ResponseEntity<ApiResponse> updateOne(@Valid @RequestBody WebNavbarDTO webNavbarDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ParameterException(bindingResult.getFieldError().getDefaultMessage());
        }
        WebNavbar webNavbarUpdated = webNavbarService.updateOneByDto(webNavbarDTO);
        return ResponseEntity.ok(new ApiResponse("200", "更新成功", webNavbarUpdated));
    }
}