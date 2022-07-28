package com.sics.constant.enums;

/**
 * Ciphertext or key type that describes the contents of an entity class
 *
 * @author liangjc
 * @version 2022/07/28
 */
public enum CiphertextOrPasswordType {
    // Ciphertext
    CIPHERTEXT("Ciphertext"),
    // password
    PASSWORD("password");
    private final String type;
    CiphertextOrPasswordType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
