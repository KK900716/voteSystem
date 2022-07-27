package com.sics.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 返回加密信息
 * @author liangjc
 * @version 2022/07/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WebBackToEncryption extends BasePojo<Map<String,Double>>{

}