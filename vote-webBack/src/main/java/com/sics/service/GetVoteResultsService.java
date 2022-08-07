package com.sics.service;

import com.sics.constant.enums.Code;
import com.sics.params.VoteParam;
import com.sics.pojo.BasePojoImpl;
import com.sics.pojo.VoteMessage;
import com.sics.pojo.VoteResultMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GetVoteResultsService {
    @Value("${url.voteResults}")
    private String url;

    @Resource
    private RestTemplate restTemplate;

    public List<VoteParam> voteResults(){
        ResponseEntity<VoteResultMessage> voteResultMessageResponseEntity =
                restTemplate.postForEntity(url, new BasePojoImpl(), com.sics.pojo.VoteResultMessage.class);
        com.sics.pojo.VoteResultMessage voteResultMessage = voteResultMessageResponseEntity.getBody();

        List<VoteParam> voteResultList = new ArrayList<>();
        if (voteResultMessage.getCode() == Code.SUCCESS.getCode()){
            Map<String,Double> resultMap = voteResultMessage.getData();
            for (String key:
                    resultMap.keySet()) {
                voteResultList.add(new VoteParam(key,resultMap.get(key)));
            }

        }
        return voteResultList;
    }
}
