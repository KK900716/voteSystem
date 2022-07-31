package com.sics.controller;

import com.sics.pojo.BasePojoImpl;
import com.sics.pojo.WebBackToEncryption;
import com.sics.service.ReturnMessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * receive webBack message
 * @author liangjc
 * @version 2022/07/26
 */
@RestController
public class ReceiveWebBackController {
    @Resource
    ReturnMessageService returnMessageService;
    @PostMapping("/web-back/encode")
    public BasePojoImpl encode(@RequestBody WebBackToEncryption webBackToEncryption){
        return returnMessageService.encode(webBackToEncryption);
    }
}
