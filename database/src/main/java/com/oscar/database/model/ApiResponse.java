package com.oscar.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/***
 *{"statusCode":"200","status":"正常","resultData":data}
 *{"statusCode":"204","status":"查無資料","resultData":null}
 *{"statusCode":"500","status":"連線異常","resultData":null}
 */
@Data
@AllArgsConstructor
public class ApiResponse {
    private String statusCode;
    private String status;
    private Object resultData;


}
