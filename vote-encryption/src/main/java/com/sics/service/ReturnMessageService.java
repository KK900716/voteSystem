package com.sics.service;

import com.alibaba.fastjson.JSON;
import com.sics.code.Code;
import com.sics.encryption.aes.EncryptionAesImpl;
import com.sics.pojo.BasePojo;
import com.sics.pojo.ReceiveMessage;
import com.sics.pojo.Return;
import com.sics.pojo.ReturnMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

/**
 * receive service
 * @author 44380
 * @version 2022~07~27~0:07
 */
@Service
public class ReturnMessageService {
    @Resource
    EncryptionAesImpl encryptionAes;
    public BasePojo<Return> encode(ReceiveMessage receiveMessage){
        BasePojo<Return> returnBasePojo = new ReturnMessage();
        int code = receiveMessage.getCode();
        if (Code.SUCCESS.getCode() == code){
            Map<String, Double> data = receiveMessage.getData();
            String json = JSON.toJSONString(data);
            String password = UUID.randomUUID().toString();
            String encode = encryptionAes.encode(json, password);
            Return rt = new Return();
            rt.setPassword(password);
            rt.setCiphertext(encode);
            returnBasePojo.setData(rt);
            return returnBasePojo;
        }
        returnBasePojo.setCode(Code.FAIL.getCode());
        returnBasePojo.setMessage(Code.FAIL.getMessage());
        return returnBasePojo;
    }
}