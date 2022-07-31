package com.sics.service;

import com.sics.constant.enums.Code;
import com.sics.pojo.BasePojoImpl;
import com.sics.pojo.DisPatchToOtherDisPatchOrServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * The ciphertext and key are received and sent to the linkServer
 *
 * @author liangjc
 * @version 2022/07/28
 */
@Service
public class ReceiveOtherDispatchService {
    private final Logger logger = LoggerFactory.getLogger(ReceiveOtherDispatchService.class);
    @Value("${url.sendToOtherDispatch}")
    private String sendToLinkServerUrl;
    @Resource
    private RestTemplate restTemplate;
    public BasePojoImpl linkServer(DisPatchToOtherDisPatchOrServer disPatchToOtherDisPatchOrServer) {
        BasePojoImpl basePojo = new BasePojoImpl();
        if (disPatchToOtherDisPatchOrServer.getCode() == Code.SUCCESS.getCode()){
            // Send the ciphertext key to the linkServer
            ResponseEntity<BasePojoImpl> linkServerResponseEntity =
                    restTemplate.postForEntity(sendToLinkServerUrl, disPatchToOtherDisPatchOrServer, BasePojoImpl.class);
            if (Objects.requireNonNull(linkServerResponseEntity.getBody()).getCode() == Code.SUCCESS.getCode()){
                basePojo.setCode(Code.SUCCESS.getCode());
                basePojo.setMessage(Code.SUCCESS.getMessage());
                return basePojo;
            }else {
                try {
                    throw new Exception("send to server result fail！");
                } catch (Exception e) {
                    logger.error("send to server result fail！");
                    e.printStackTrace();
                }
            }
        }
        basePojo.setCode(Code.FAIL.getCode());
        basePojo.setMessage(Code.FAIL.getMessage());
        logger.error("dispatch receive fail");
        return basePojo;
    }
}
