package com.sics.controller;

import com.sics.pojo.BasePojo;
import com.sics.pojo.ReceiveMessage;
import com.sics.pojo.Return;
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
    @PostMapping("/encode")
    public BasePojo<Return> encode(@RequestBody ReceiveMessage receiveMessage){
        return returnMessageService.encode(receiveMessage);
    }
}
