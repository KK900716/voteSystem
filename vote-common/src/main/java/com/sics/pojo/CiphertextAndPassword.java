package com.sics.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Return class
 *
 * @author 44380
 * @version 2022~07~26~23:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CiphertextAndPassword {
    private String ciphertext;
    private String password;
}
