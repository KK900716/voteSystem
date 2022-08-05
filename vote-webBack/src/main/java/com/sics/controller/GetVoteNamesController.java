package com.sics.controller;

import com.sics.pojo.VoteMessage;
import com.sics.service.GetVoteNamesService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Tlinian
 * @Description
 * @Date 2022/7/31
 */
@RestController
public class GetVoteNamesController {
    @Resource
    GetVoteNamesService getVoteNamesService;
    //reture type
    @GetMapping("/getVoteNames")
    public VoteMessage getVotePojo(){
        return getVoteNamesService.getVoteNames();
    }
}
