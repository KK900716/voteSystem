package com.sics.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetAllVoters {

    @GetMapping("/getAll")
    public void getVoters(){

    }

}
