package com.sics.controller;

import com.sics.pojo.WebBackToEncryption;
import com.sics.service.GetVoteNamesService;
import com.sics.service.SendToEncryptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Tlinian
 * @Description
 * @Date 2022/7/31
 */
@RestController
public class GetVoteNamesController {
    @Resource
    GetVoteNamesService getVoteNamesService;
    //reture type
    @GetMapping("/getVoteNames")
    public void  getVotePojo(WebBackToEncryption webBackToEncryption){
//        return null;
        getVoteNamesService.getVoteNames();
    }
}
