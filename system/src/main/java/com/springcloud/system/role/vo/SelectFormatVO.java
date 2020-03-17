package com.springcloud.system.role.vo;

import lombok.Data;

/**
 * 角色前端下拉框实体
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/16 22:41
 *
 **/
@Data
public class SelectFormatVO {
    private Integer value;
    private String label;

    public SelectFormatVO(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
