package com.sics.service;

import com.alibaba.fastjson.JSON;
import com.sics.constant.enums.Code;
import com.sics.encryption.aes.EncryptionAesImpl;
import com.sics.pojo.BasePojoImpl;
import com.sics.pojo.CiphertextAndPassword;
import com.sics.pojo.EncryptionToDisPatchCiphertextAndPassword;
import com.sics.pojo.WebBackToEncryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(ReturnMessageService.class);
    /**
     * The URL sent to the Dispatch module
     */
    @Value("${url.sendToDispatch}")
    private String url;
    /**
     * Aes Encryption system
     */
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
    public BasePojoImpl encode(WebBackToEncryption webBackToEncryption){
        // new return message
        BasePojoImpl returnBasePojo = new BasePojoImpl();
        // Determine whether acceptance is successful
        if (Code.SUCCESS.getCode() == webBackToEncryption.getCode()){
            // Send information to the Dispatch module
            if (sendToDispatch(webBackToEncryption.getData())){
                returnBasePojo.setCode(Code.SUCCESS.getCode());
                returnBasePojo.setMessage(Code.SUCCESS.getMessage());
                return returnBasePojo;
            }
        }
        // Otherwise, return failure.
        returnBasePojo.setCode(Code.FAIL.getCode());
        returnBasePojo.setMessage(Code.FAIL.getMessage());
        logger.error("webBackToEncryption error！");
        return returnBasePojo;
    }
    /**
     * Send password and ciphertext to vote-dispatch
     * @author liangjc
     * @param data 得分数据
     */
    protected boolean sendToDispatch(Map<String, Double> data){
        // Convert the data into a JSON string
        String json = JSON.toJSONString(data);
        // Random key generation
        String uuid = UUID.randomUUID().toString();
        String password = uuid.substring(uuid.length()-17,uuid.length()-1);
        // Obtain ciphertext
        String ciphertext = encryptionAes.encode(json, password);
        logger.info("uses the AES encryption algorithm");
        // Encapsulating an entity class
        CiphertextAndPassword ciphertextAndPassword = new CiphertextAndPassword();
        EncryptionToDisPatchCiphertextAndPassword encryptionToDisPatchCiphertextAndPassword = new EncryptionToDisPatchCiphertextAndPassword();
        ciphertextAndPassword.setPassword(password);
        ciphertextAndPassword.setCiphertext(ciphertext);
        encryptionToDisPatchCiphertextAndPassword.setData(ciphertextAndPassword);
        encryptionToDisPatchCiphertextAndPassword.setCode(Code.SUCCESS.getCode());
        encryptionToDisPatchCiphertextAndPassword.setMessage(Code.SUCCESS.getMessage());
        // send ciphertext and password dispatch
        ResponseEntity<BasePojoImpl> basePojoResponseEntity =
                restTemplate.postForEntity(url, encryptionToDisPatchCiphertextAndPassword, BasePojoImpl.class);
        logger.info("send ciphertext and password dispatch");
        return Objects.requireNonNull(basePojoResponseEntity.getBody()).getCode() == Code.SUCCESS.getCode();
    }
}