package com.sics.encryption.idea;

import com.sics.encryption.AbstractEncryption;
import org.springframework.stereotype.Component;

/**
 * idea impl
 *
 * @author liangjc
 * @version 2022/07/26
 */
@Component
public class EncryptionIdeaImpl extends AbstractEncryption{

    @Override
    public String encode(String content, String password) {
        return null;
    }

    @Override
    public String decode(String ciphertext, String password) {
        return null;
    }
}
