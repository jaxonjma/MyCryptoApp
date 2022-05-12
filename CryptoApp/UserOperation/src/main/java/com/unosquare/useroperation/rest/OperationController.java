package com.unosquare.useroperation.rest;

import com.unosquare.useroperation.service.OperationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/")
public class OperationController {

    @Autowired
    private OperationHistoryService operationHistoryService;

//    GET /user/{email}/operation/hours/12
//    GET /user/{email}/operation/hours/24
//    GET /user/{email}/operation/days/7
//    GET /user/{email}/operation/days/30
//    POST /user/{email}/operation/registerOperation



}
