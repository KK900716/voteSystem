package com.sics.encryption.aes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Objects;


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
        String content = "defsadfasdfasdfsadfsadfasdfasfasfsadfsadfdsaf";
        String password = "1234567890123456";
        String encryption = encryptionAes.encode(content, password);
        String decryption = encryptionAes.decode(encryption, password);
        assert Objects.equals(decryption, content);
    }
}