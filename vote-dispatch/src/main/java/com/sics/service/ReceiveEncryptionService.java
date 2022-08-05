package com.sics.service;

import com.sics.constant.enums.CiphertextOrPasswordType;
import com.sics.constant.enums.Code;
import com.sics.pojo.BasePojoImpl;
import com.sics.pojo.CipherTextOrPassword;
import com.sics.pojo.DisPatchToOtherDisPatchOrServer;
import com.sics.pojo.EncryptionToDisPatchCiphertextAndPassword;
import com.sics.pojo.ServerToDisPatchIpList;
import java.net.http.HttpTimeoutException;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Handle distribution services and implement algorithmic logic
 *
 * @author liangjc
 * @version 2022/07/27
 */
@Service
public class ReceiveEncryptionService {
    private final Logger logger = LoggerFactory.getLogger(ReceiveEncryptionService.class);
    @Value("${url.sendToServerGetIp}")
    private String sendToServerUrl;
    @Value("TEMPLATE")
    private String hostTemplate;
    @Value("${server.sendToOtherDispatch}")
    private String sendOtherDispatchUrl;
    @Resource
    private RestTemplate restTemplate;
    /**
     * Implementing distribution logic
     * @author liangjc
     * @param encryptionToDisPatchCiphertextAndPassword Forms that accept encryption from Encryption
     * @return Return accepted status
     */
    public BasePojoImpl dispatch(EncryptionToDisPatchCiphertextAndPassword encryptionToDisPatchCiphertextAndPassword) {
        // new return message
        BasePojoImpl returnBasePojo = new BasePojoImpl();
        // Determine whether acceptance is successful, Processing distribution
        if (encryptionToDisPatchCiphertextAndPassword.getCode() == Code.SUCCESS.getCode() && buildingTheEntity(encryptionToDisPatchCiphertextAndPassword)){
            returnBasePojo.setCode(Code.SUCCESS.getCode());
            returnBasePojo.setMessage(Code.SUCCESS.getMessage());
            return returnBasePojo;
        }
        returnBasePojo.setMessage(Code.FAIL.getMessage());
        returnBasePojo.setCode(Code.FAIL.getCode());
        try {
            throw new Exception("encryption to disPatch error！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("encryption to disPatch error！");
        }
        return returnBasePojo;
    }
    /**
     * Processing distribution
     * @author liangjc
     * @param encryptionToDisPatchCiphertextAndPassword password and ciphertext
     * @return whether success
     */
    private boolean buildingTheEntity(EncryptionToDisPatchCiphertextAndPassword encryptionToDisPatchCiphertextAndPassword) {
        BasePojoImpl basePojo = new BasePojoImpl();
        basePojo.setCode(Code.SUCCESS.getCode());
        basePojo.setMessage(Code.SUCCESS.getMessage());
        // Request a voting user from the server
        ResponseEntity<ServerToDisPatchIpList> linkServerToDisPatchResponseEntity =
                null;
        try {
            linkServerToDisPatchResponseEntity = restTemplate.postForEntity(sendToServerUrl, basePojo, ServerToDisPatchIpList.class);
            if (Objects.requireNonNull(linkServerToDisPatchResponseEntity.getBody()).getCode() != Code.SUCCESS.getCode() ||
                linkServerToDisPatchResponseEntity.getBody().getData().size() < 2){
                logger.error("DisPatchToLinkServer error！");
                return false;
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            logger.error("DisPatchToLinkServer error！");
        }
        // Building the Entity class
        CipherTextOrPassword firstCipherTextOrPassword = new CipherTextOrPassword(CiphertextOrPasswordType.CIPHERTEXT, encryptionToDisPatchCiphertextAndPassword.getData().getCiphertext());
        CipherTextOrPassword secondCipherTextOrPassword = new CipherTextOrPassword(CiphertextOrPasswordType.PASSWORD, encryptionToDisPatchCiphertextAndPassword.getData().getPassword());
        DisPatchToOtherDisPatchOrServer firstDisPatchToOtherDisPatchOrServer = new DisPatchToOtherDisPatchOrServer();
        firstDisPatchToOtherDisPatchOrServer.setCode(Code.SUCCESS.getCode());
        firstDisPatchToOtherDisPatchOrServer.setMessage(Code.SUCCESS.getMessage());
        firstDisPatchToOtherDisPatchOrServer.setData(firstCipherTextOrPassword);
        DisPatchToOtherDisPatchOrServer secondDisPatchToOtherDisPatchOrServer = new DisPatchToOtherDisPatchOrServer();
        secondDisPatchToOtherDisPatchOrServer.setCode(Code.SUCCESS.getCode());
        secondDisPatchToOtherDisPatchOrServer.setMessage(Code.SUCCESS.getMessage());
        secondDisPatchToOtherDisPatchOrServer.setData(secondCipherTextOrPassword);
        // Get the ids of all users
        List<String> userUrlList = linkServerToDisPatchResponseEntity.getBody().getData();
        // Send to other dispatches
        boolean[] ipWhetherIsUsed = new boolean[userUrlList.size()];
        int size = ipWhetherIsUsed.length;
        int firstDispatchIp = randomIpIndex(ipWhetherIsUsed, userUrlList.size());
        size--;
        // Send first
        boolean sendWhetherSuccess = false;
        while (size > 0){
            sendWhetherSuccess = sendToOtherDisPatch(sendOtherDispatchUrl.replace(hostTemplate, userUrlList.get(firstDispatchIp))
                    , firstDisPatchToOtherDisPatchOrServer);
            if (sendWhetherSuccess){
                break;
            }
            firstDispatchIp = randomIpIndex(ipWhetherIsUsed, userUrlList.size());
            size--;
        }
        if (!sendWhetherSuccess) {
            logger.error("send to other fail!");
            try {
                throw new HttpTimeoutException("send to other fail!");
            } catch (HttpTimeoutException e) {
                e.printStackTrace();
                logger.error("send to other fail!");
            }
        }
        // send second
        sendWhetherSuccess = false;
        int secondDispatchIp = randomIpIndex(ipWhetherIsUsed, userUrlList.size());
        size--;
        while (size >= 0){
            sendWhetherSuccess = sendToOtherDisPatch(sendOtherDispatchUrl.replace(hostTemplate, userUrlList.get(secondDispatchIp))
                    , secondDisPatchToOtherDisPatchOrServer);
            if (sendWhetherSuccess){
                break;
            }
            secondDispatchIp = randomIpIndex(ipWhetherIsUsed, userUrlList.size());
            size--;
        }
        if (!sendWhetherSuccess) {
            logger.error("send to other fail!");
            try {
                throw new HttpTimeoutException("send to other fail!");
            } catch (HttpTimeoutException e) {
                e.printStackTrace();
                logger.error("send to other fail!");
            }
        }
        return size >= 0;
    }
    /**
     * random return ip group index
     * @param ipWhetherIsUsed judge usage
     * @param size random range
     * @return index
     */
    private int randomIpIndex(boolean[] ipWhetherIsUsed, int size) {
        while (true) {
            int result = (int) (Math.random() * size);
            if (!ipWhetherIsUsed[result]) {
                ipWhetherIsUsed[result] = true;
                return result;
            }
        }
    }
    /**
     * send module
     * @param url Send the url of the
     * @param disPatchToOtherDisPatch entity
     * @return whether success
     */
    private boolean sendToOtherDisPatch(String url, DisPatchToOtherDisPatchOrServer disPatchToOtherDisPatch){
        try {
            ResponseEntity<BasePojoImpl> disPatchResponse = restTemplate.postForEntity(url, disPatchToOtherDisPatch, BasePojoImpl.class);
            if (Objects.requireNonNull(disPatchResponse.getBody()).getCode() != Code.SUCCESS.getCode()){
                throw new Exception("send to other dispatch fail!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(url + "connection timeout!");
            return false;
        }
        return true;
    }
}
