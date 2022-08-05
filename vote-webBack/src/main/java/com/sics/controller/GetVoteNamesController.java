package com.sics.controller;

import com.sics.params.VoteName;
import com.sics.service.GetVoteNamesService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Tlinian
 * @Description
 * @Date 2022/7/31
 */
@RestController
@CrossOrigin
public class GetVoteNamesController {
    @Resource
    GetVoteNamesService getVoteNamesService;
    //reture type
    @GetMapping("/getVoteNames")
    public List<VoteName> getVotePojo(){
        return getVoteNamesService.getVoteNames();
    }
}
