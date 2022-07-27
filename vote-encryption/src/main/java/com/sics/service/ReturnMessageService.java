package com.sics.service;

import com.alibaba.fastjson.JSON;
import com.sics.code.Code;
import com.sics.encryption.aes.EncryptionAesImpl;
import com.sics.pojo.BasePojo;
import com.sics.pojo.CiphertextAndPassword;
import com.sics.pojo.EncryptionToDisPatch;
import com.sics.pojo.WebBackToEncryption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * receive service
 * @author 44380
 * @version 2022~07~27~0:07
 */
@Service
public class ReturnMessageService {
    @Value("${url.sendToDispatch}")
    private String url;
    @Resource
    private EncryptionAesImpl encryptionAes;
    @Resource
    private RestTemplate restTemplate;
    /**
     * Process the data sent by the WebBack
     * @author liangjc
     * @param webBackToEncryption Receive scoring data from WebBack
     * @return Return accepted status
     */
    public BasePojo<CiphertextAndPassword> encode(WebBackToEncryption webBackToEncryption){
        BasePojo<CiphertextAndPassword> returnBasePojo = new EncryptionToDisPatch();
        boolean sendToDispatchJudge = sendToDispatch(webBackToEncryption.getData());
        if (sendToDispatchJudge && Code.SUCCESS.getCode() == webBackToEncryption.getCode()){
            returnBasePojo.setCode(Code.SUCCESS.getCode());
            returnBasePojo.setMessage(Code.SUCCESS.getMessage());
            return returnBasePojo;
        }
        returnBasePojo.setCode(Code.FAIL.getCode());
        returnBasePojo.setMessage(Code.FAIL.getMessage());
        return returnBasePojo;
    }
    /**
     * Send password and ciphertext to vote-dispatch
     * @author liangjc
     * @param data 得分数据
     */
    private boolean sendToDispatch(Map<String, Double> data){
        String json = JSON.toJSONString(data);
        String password = UUID.randomUUID().toString();
        String ciphertext = encryptionAes.encode(json, password);
        CiphertextAndPassword ciphertextAndPassword = new CiphertextAndPassword();
        ciphertextAndPassword.setPassword(password);
        ciphertextAndPassword.setCiphertext(ciphertext);
        EncryptionToDisPatch encryptionToDisPatch = new EncryptionToDisPatch();
        encryptionToDisPatch.setData(ciphertextAndPassword);
        encryptionToDisPatch.setCode(Code.SUCCESS.getCode());
        encryptionToDisPatch.setMessage(Code.SUCCESS.getMessage());
        ResponseEntity<BasePojo> basePojoResponseEntity = restTemplate.postForEntity(url, encryptionToDisPatch, BasePojo.class);
        return Objects.requireNonNull(basePojoResponseEntity.getBody()).getCode() == Code.SUCCESS.getCode();
    }
}