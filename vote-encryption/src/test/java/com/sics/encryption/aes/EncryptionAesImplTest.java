package com.sics.encryption.aes;

import com.alibaba.fastjson.JSON;
import com.sics.EncryptionStartApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;


@SpringBootTest(classes = EncryptionStartApplication.class)
class EncryptionAesImplTest {
    @Resource
    private EncryptionAesImpl encryptionAes;
    @Test
    void testEncryptionAndDecryption() {
        HashMap<String, Double> hashMap = new HashMap<>();
        hashMap.put("a1",6.8);
        hashMap.put("a2",9.8);
        hashMap.put("a3",5.8);
        hashMap.put("a4",7.8);
        String content = JSON.toJSONString(hashMap);
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        String password = uuid.substring(uuid.length()-17,uuid.length()-1);
        String encryption = encryptionAes.encode(content, password);
        System.out.println(encryption);
        String decryption = encryptionAes.decode(encryption, password);
        System.out.println(decryption);
        assert Objects.equals(decryption, content);
    }
}