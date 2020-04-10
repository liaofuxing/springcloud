package com.springcloud.common.vo;

import lombok.Data;

/**
 * 下拉框实体VO
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/17 15:41
 **/
@Data
public class SelectFormatVO {
    private Object value;
    private String label;

    public SelectFormatVO(Object value, String label) {
        this.value = value;
        this.label = label;
    }
}
