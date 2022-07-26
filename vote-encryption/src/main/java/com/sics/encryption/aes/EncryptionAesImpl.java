package com.sics.encryption.aes;

import com.sics.encryption.AbstractEncryption;
import com.sics.utils.ParseSystemUtil;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Implementation of AES Algorithm
 *
 * @author liangjc
 * @version 2022/07/26
 */
@Component
public class EncryptionAesImpl extends AbstractEncryption {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    @Override
    public String encode(String content, String password) {
        super.content = content;
        super.password = password;
        try {

            // Cipher：密码，获取加密对象
            // transformation:参数表示使用什么类型加密
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            // 指定秘钥规则
            // 第一个参数表示：密钥，key的字节数组 长度必须是16位
            // 第二个参数表示：算法
            SecretKeySpec sks = new SecretKeySpec(password.getBytes(), ALGORITHM);
            // 对加密进行初始化
            // 第一个参数：表示模式，有加密模式和解密模式
            // 第二个参数：表示秘钥规则
            cipher.init(Cipher.ENCRYPT_MODE,sks);
            // 进行加密
            byte[] bytes = cipher.doFinal(content.getBytes());
            super.ciphertext = ParseSystemUtil.parseByte2HexStr(bytes);
            return super.ciphertext;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String decode(String ciphertext, String password) {
        try {
            // 算法
            // Cipher：密码，获取加密对象
            // transformation:参数表示使用什么类型加密
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            // 指定秘钥规则
            // 第一个参数表示：密钥，key的字节数组 长度必须是16位
            // 第二个参数表示：算法
            SecretKeySpec sks = new SecretKeySpec(password.getBytes(), ALGORITHM);
            // 对加密进行初始化
            // 第一个参数：表示模式，有加密模式和解密模式
            // 第二个参数：表示秘钥规则
            cipher.init(Cipher.DECRYPT_MODE,sks);
            // 进行解密
            byte [] inputBytes = ParseSystemUtil.parseHexStr2Byte(ciphertext);
            assert inputBytes != null;
            byte[] bytes = cipher.doFinal(inputBytes);
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
