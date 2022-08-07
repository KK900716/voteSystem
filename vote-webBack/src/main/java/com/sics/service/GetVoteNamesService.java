package com.sics.service;

import com.sics.constant.enums.Code;
import com.sics.params.VoteParam;
import com.sics.pojo.BasePojoImpl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public List<VoteParam> getVoteNames(){
        ResponseEntity<com.sics.pojo.VoteMessage> voteMessageResponseEntity =
            restTemplate.postForEntity(url, new BasePojoImpl(), com.sics.pojo.VoteMessage.class);
        com.sics.pojo.VoteMessage voteMessage = voteMessageResponseEntity.getBody();

        List<VoteParam> voteNameList = new ArrayList<>();
        for (int i = 0; i < voteMessage.getData().size(); i++) {
            if (voteMessage.getCode() == Code.SUCCESS.getCode()){
                VoteParam voteParam= new VoteParam();
                voteParam.setName(voteMessage.getData().get(i));
                voteNameList.add(voteParam);
            }
        }
        System.out.println(voteMessage);

        return voteNameList;
    }

}
