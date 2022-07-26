package com.sics.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liangjc
 * @version 2022/07/26
 */
public abstract class BasePojo<T> implements Serializable {
    private T data;
    private String code;
    private String message;
}
