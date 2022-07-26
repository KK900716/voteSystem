package com.sics.pojo;

import lombok.Data;

/**
 * 返回加密信息
 * @author liangjc
 * @version 2022/07/26
 */
@Data
public class ReturnMessage extends BasePojo<Return>{

}
class Return{
    private String password;
    private String ciphertext;
}