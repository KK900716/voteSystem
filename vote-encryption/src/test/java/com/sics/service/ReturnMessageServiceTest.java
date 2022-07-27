package com.sics.service;

import com.sics.EncryptionStartApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = EncryptionStartApplication.class)
class ReturnMessageServiceTest {
    @Resource
    ReturnMessageService returnMessageService;
    @Test
    void sendToDispatch() {
        HashMap<String, Double> hashMap = new HashMap<>();
        hashMap.put("a1",6.8);
        hashMap.put("a2",9.8);
        hashMap.put("a3",5.8);
        hashMap.put("a4",7.8);
        // todo
        returnMessageService.sendToDispatch(hashMap);
    }
}