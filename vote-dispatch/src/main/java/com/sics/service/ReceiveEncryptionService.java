package com.sics.service;

import com.sics.code.Code;
import com.sics.pojo.BasePojoImpl;
import com.sics.pojo.EncryptionToDisPatch;
import com.sics.pojo.LinkServerToDisPatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * Handle distribution services and implement algorithmic logic
 *
 * @author liangjc
 * @version 2022/07/27
 */
@Service
public class ReceiveEncryptionService {
    Logger logger = LoggerFactory.getLogger(ReceiveEncryptionService.class);
    @Value("${url.sendToLinkServer}")
    String sendToLinkServerUrl;
    @Resource
    RestTemplate restTemplate;
    /**
     * Implementing distribution logic
     * @author liangjc
     * @param encryptionToDisPatch Forms that accept encryption from Encryption
     * @return Return accepted status
     */
    public BasePojoImpl dispatch(EncryptionToDisPatch encryptionToDisPatch) {
        // new return message
        BasePojoImpl returnBasePojo = new BasePojoImpl();
        // Determine whether acceptance is successful, Processing distribution
        if (encryptionToDisPatch.getCode() == Code.SUCCESS.getCode() || sendOtherDispatch(encryptionToDisPatch)){
            returnBasePojo.setCode(Code.SUCCESS.getCode());
            returnBasePojo.setMessage(Code.SUCCESS.getMessage());
            return returnBasePojo;
        }
        returnBasePojo.setMessage(Code.FAIL.getMessage());
        returnBasePojo.setCode(Code.FAIL.getCode());
        logger.error("encryptionToDisPatch error！");
        return returnBasePojo;
    }
    /**
     * Processing distribution
     * @author liangjc
     * @param encryptionToDisPatch password and ciphertext
     * @return whether success
     */
    private boolean sendOtherDispatch(EncryptionToDisPatch encryptionToDisPatch) {
        BasePojoImpl basePojo = new BasePojoImpl();
        basePojo.setCode(Code.SUCCESS.getCode());
        basePojo.setMessage(Code.SUCCESS.getMessage());
        // Request a voting user from the server
        ResponseEntity<LinkServerToDisPatch> linkServerToDisPatchResponseEntity =
                restTemplate.postForEntity(sendToLinkServerUrl, basePojo, LinkServerToDisPatch.class);
        if (Objects.requireNonNull(linkServerToDisPatchResponseEntity.getBody()).getCode() != Code.SUCCESS.getCode()){
            return false;
        }
        // Get the ids of all users
        List<String> userUrlMap = linkServerToDisPatchResponseEntity.getBody().getData();
        // Distribute to random IP addresses
        int firstDispatchIp = (int)(Math.random() * userUrlMap.size());
        int secondDispatchIp = (int)(Math.random() * userUrlMap.size());
        while (firstDispatchIp == secondDispatchIp){
            secondDispatchIp = (int)(Math.random() * userUrlMap.size());
        }
        // dispatch
        // todo
        return false;
    }
}
