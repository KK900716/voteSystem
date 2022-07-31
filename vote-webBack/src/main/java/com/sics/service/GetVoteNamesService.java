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
public class GetVoteNamesService {
    @Value("${url.receiveServer}")
    private String url;

    @Resource
    private RestTemplate restTemplate;
    //Variable return parameters will be refined in due course
    public BasePojoImpl getVoteNames(){
        return restTemplate.getForObject(url, BasePojoImpl.class);

    }

}
