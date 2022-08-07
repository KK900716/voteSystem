package com.sics.service;

import com.sics.constant.enums.Code;
import com.sics.params.VoteParam;
import com.sics.pojo.BasePojoImpl;
import com.sics.pojo.WebBackToEncryption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    public boolean sendEncrytion(VoteParam voteParam){
        WebBackToEncryption webBackToEncryption = new WebBackToEncryption();
        webBackToEncryption.setCode(Code.SUCCESS.getCode());
        webBackToEncryption.setData((Map<String, Double>) new HashMap<>().put(voteParam.getName(),voteParam.getScore()));
        System.out.println(new Date()+" receive from web :"+ voteParam.toString());
        ResponseEntity<BasePojoImpl> basePojo = restTemplate.postForEntity(url,webBackToEncryption, BasePojoImpl.class);
        System.out.println("send encrytion:"+basePojo);
        if(basePojo!=null)
            return true;
        return false;
    }

}
