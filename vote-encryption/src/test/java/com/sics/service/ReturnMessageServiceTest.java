package com.sics.service;

import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ReturnMessageServiceTest {
    @Resource
    ReturnMessageService returnMessageService;
    @Test
    void encode() {
    }

    @Test
    void sendToDispatch() {
        HashMap<String, Double> hashMap = new HashMap<>();
        hashMap.put("a1",6.8);
        hashMap.put("a2",9.8);
        hashMap.put("a3",5.8);
        hashMap.put("a4",7.8);
        // returnMessageService.sendToDispatch();
    }
}