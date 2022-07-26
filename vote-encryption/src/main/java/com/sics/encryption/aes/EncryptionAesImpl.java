package com.sics.encryption.aes;

import com.sics.encryption.AbstractEncryption;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Implementation of AES Algorithm
 *
 * @author liangjc
 * @version 2022/07/26
 */
@Component
public class EncryptionAesImpl extends AbstractEncryption {
    @Override
    public byte[] encryption(String content, String password) {
        super.content = content;
        super.password = password;
        try {
            // 创建AES的Key生产者
            KeyGenerator keyGeneration = KeyGenerator.getInstance("AES");
            // 利用用户密码作为随机数初始化出
            keyGeneration.init(128, new SecureRandom(password.getBytes()));
            // 加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = keyGeneration.generateKey();
            // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            super.ciphertext = cipher.doFinal(byteContent);
            return super.ciphertext;
        } catch (NoSuchPaddingException | UnsupportedEncodingException | NoSuchAlgorithmException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String decryption(byte[] ciphertext, String password) {
        super.ciphertext = ciphertext;
        super.password = password;
        try {
            // 创建AES的Key生产者
            KeyGenerator keyGeneration = KeyGenerator.getInstance("AES");
            keyGeneration.init(128, new SecureRandom(password.getBytes()));
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = keyGeneration.generateKey();
            // 返回基本编码格式的密钥
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            // 初始化为解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(ciphertext);
            super.content = new String(result, "utf-8");
            return super.content;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
