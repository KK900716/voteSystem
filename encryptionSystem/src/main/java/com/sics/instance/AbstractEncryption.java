package com.sics.instance;

/**
 * The encryption algorithm
 *
 * @author liangjc
 * @version 2022~07~25~15:34
 */
public abstract class AbstractEncryption {
    public abstract String encryption(String value);
    public abstract String decryption(String code);
}
