package com.sics.configuration;

import com.sics.encryption.aes.EncryptionAesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * create RestTemplateBean
 *
 * @author liangjc
 * @version 2022/07/27
 */
@Configuration
public class CreateBean {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public EncryptionAesImpl encryptionAes(){
        return new EncryptionAesImpl();
    }
}
