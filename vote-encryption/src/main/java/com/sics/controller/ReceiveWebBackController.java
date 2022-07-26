package com.sics.controller;

import com.sics.pojo.ReceiveMessage;
import com.sics.pojo.ReturnMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * receive webBack message
 * @author liangjc
 * @version 2022/07/26
 */
@RestController
public class ReceiveWebBackController {
    @GetMapping("/encode")
    public ReturnMessage encode(@RequestParam ReceiveMessage receiveMessage){
        return null;
    }
}
