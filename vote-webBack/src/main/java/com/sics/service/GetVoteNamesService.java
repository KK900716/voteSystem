package com.sics.service;

import com.sics.constant.enums.Code;
import com.sics.params.VoteName;
import com.sics.pojo.BasePojoImpl;
import com.sics.pojo.VoteMessage;
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
    public List<VoteName> getVoteNames(){
        ResponseEntity<VoteMessage> voteMessageResponseEntity =
            restTemplate.postForEntity(url, new BasePojoImpl(), VoteMessage.class);
        VoteMessage voteMessage = voteMessageResponseEntity.getBody();

        List<VoteName> voteNameList = new ArrayList<>();
        for (int i = 0; i < voteMessage.getData().size(); i++) {
            if (voteMessage.getCode() == Code.SUCCESS.getCode()){
                voteNameList.add(new VoteName(voteMessage.getData().get(i)));
            }
        }
        System.out.println(voteMessage);

        return voteNameList;
    }

}
