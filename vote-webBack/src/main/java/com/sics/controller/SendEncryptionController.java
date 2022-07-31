package com.sics.controller;

import com.sics.pojo.WebBackToEncryption;
import com.sics.service.SendToEncryptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Tlinian
 * @Description
 * @Date 2022/7/30
 */
@RestController
public class SendEncryptionController {
    @Resource
    SendToEncryptionService sendToEncryptionService;

    @GetMapping("/voteMessage")
    public boolean  getVotePojo(WebBackToEncryption webBackToEncryption){
        return sendToEncryptionService.sendEncrytion(webBackToEncryption);
    }
}
