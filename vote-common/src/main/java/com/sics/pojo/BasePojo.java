package com.sics.pojo;


import lombok.Data;

import java.io.Serializable;

/**
 * @author liangjc
 * @version 2022/07/26
 */
@Data
public abstract class BasePojo<T> implements Serializable {
    private T data;
    private int code;
    private String message;
}
