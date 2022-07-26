package com.sics.encryption;

/**
 * By creating this class, you get a key that can be used for encryption and decryption
 * @author liangjc
 * @version 2022~07~25~15:34
 */
public abstract class AbstractEncryption {
    protected String content;
    protected String password;
    protected String ciphertext;

    /**
     * encryption
     * @author liangjc
     * @param content content
     * @param password password
     * @return ciphertext
     */
    public abstract String encode(String content, String password);
    /**
     * decryption
     * @author liangjc
     * @param ciphertext ciphertext
     * @param password password
     * @return content
     */
    public abstract String decode(String ciphertext, String password);

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCiphertext() {
        return ciphertext;
    }
}