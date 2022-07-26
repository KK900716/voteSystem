package com.sics.instance;

/**
 * The encryption algorithm
 *
 * @author liangjc
 * @version 2022~07~25~15:34
 */
public abstract class AbstractEncryption {
    /**
     * encryption
     * @author liangjc
     * @param value content
     * @return ciphertext
     */
    public abstract String encryption(String value);
    /**
     * decryption
     * @author liangjc
     * @param code ciphertext
     * @return content
     */
    public abstract String decryption(String code);
    /**
     * get content
     * @author liangjc
     * @return content
     */
    public abstract String getContent();
    /**
     * get ciphertext
     * @author liangjc
     * @return ciphertext
     */
    public abstract String getCiphertext();
    /**
     * get key
     * @author liangjc
     * @return key
     */
    public abstract String getKey();
    /**
     * set key
     * @author liangjc
     */
    public abstract void setKey();
}
