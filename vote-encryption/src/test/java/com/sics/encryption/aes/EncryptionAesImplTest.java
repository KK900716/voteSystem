package com.sics.encryption.aes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class EncryptionAesImplTest {
    @Resource
    private EncryptionAesImpl encryptionAes;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testEncryptionAndDecryption() {
        String content = "我是一颗卤蛋";
        String password = "我是密码";
        byte[] ciphertext = encryptionAes.encryption(content, password);
        String decryption = encryptionAes.decryption(ciphertext, password);
        System.out.println(decryption);
        // assert Objects.equals(decryption, content);
    }
}