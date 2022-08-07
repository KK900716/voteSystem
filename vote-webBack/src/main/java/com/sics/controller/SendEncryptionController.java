package com.sics.controller;

import com.sics.constant.enums.Code;
import com.sics.params.VoteParam;
import com.sics.pojo.WebBackToEncryption;
import com.sics.service.SendToEncryptionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Tlinian
 * @Description
 * @Date 2022/7/30
 */
@RestController
@CrossOrigin
public class SendEncryptionController {
    @Resource
    SendToEncryptionService sendToEncryptionService;

    @PostMapping("/voteMessage")
    public boolean  getVotePojo(VoteParam voteParam){
        System.out.println(new Date()+"/voteMessage"+voteParam.toString());
        return sendToEncryptionService.sendEncrytion(voteParam);
    }
}
