package com.sics.controller;

import com.sics.pojo.BasePojoImpl;
import com.sics.pojo.EncryptionToDisPatch;
import com.sics.service.ReceiveEncryptionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * receive encryption
 *
 * @author liangjc
 * @version 2022/07/27
 */
@RestController
public class ReceiveEncryptionController {
    @Resource
    ReceiveEncryptionService receiveEncryptionService;
    @PostMapping("/dispatch")
    public BasePojoImpl dispatch(@RequestBody EncryptionToDisPatch encryptionToDisPatch){
        return receiveEncryptionService.dispatch(encryptionToDisPatch);
    }
}
