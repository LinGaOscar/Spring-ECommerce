package com.oscar.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/***
 *{"statusCode":"200","status":"正常","resultData":data}
 *{"statusCode":"201","status":"新增成功","resultData":data}
 *{"statusCode":"204","status":"查無資料","resultData":null}
 *{"statusCode":"422","status":"參數錯誤","resultData":bindingResult.getFieldError().getDefaultMessage()}
 *{"statusCode":"500","status":"連線異常","resultData":e.getMessage()}
 */
@Data
@AllArgsConstructor
public class ApiResponse {
    private String statusCode;
    private String status;
    private Object resultData;
}
