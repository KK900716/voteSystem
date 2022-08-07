package com.sics.controller;

import com.sics.params.VoteParam;
import com.sics.service.GetVoteResultsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
public class GetVoteResultsController {
    @Resource
    private GetVoteResultsService getVoteResultsService;
    @PostMapping("/voteResult")
    public List<VoteParam> voteResult(){
        return getVoteResultsService.voteResults();
    }
}
