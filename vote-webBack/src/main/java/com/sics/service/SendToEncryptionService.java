package com.sics.service;

import com.sics.pojo.BasePojoImpl;
import com.sics.pojo.WebBackToEncryption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author Tlinian
 * @Description
 * @Date 2022/7/30
 */
@Service
public class SendToEncryptionService {
    @Value("${url.sendToEncrytion}")
    private String url;

    @Resource
    private RestTemplate restTemplate;

    public boolean sendEncrytion(WebBackToEncryption webBackToEncryption){
        if(restTemplate.postForEntity(url,webBackToEncryption, BasePojoImpl.class)!=null)
            return true;
        return false;
    }

}
