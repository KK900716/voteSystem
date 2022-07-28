package com.sics.pojo;

import com.sics.constant.enums.CiphertextOrPasswordType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liangjc
 * @version 2022/07/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CiphertextOrPassword {
    private CiphertextOrPasswordType type;
    private String value;
}
