package com.sics.controller;

import com.sics.code.Code;
import com.sics.pojo.BasePojo;
import com.sics.pojo.EncryptionToDisPatch;
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
    @PostMapping("/dispatch")
    public BasePojo<String> dispatch(@RequestBody EncryptionToDisPatch encryptionToDisPatch){
        System.out.println(encryptionToDisPatch);
        System.out.println(encryptionToDisPatch.getData());
        System.out.println(encryptionToDisPatch.getData().getCiphertext());
        System.out.println(encryptionToDisPatch.getData().getPassword());
        BasePojo<String> basePojo=new BasePojo<String>() {
        };
        basePojo.setCode(Code.SUCCESS.getCode());
        return basePojo;
    }
}
