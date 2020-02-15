package com.myspringcloud.common.vo;

import lombok.Data;

/**
 * @author liaofuxing
 * @date 2019/03/10 4:10
 */
@Data
public class ResultVO<T> {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体数据
     */
    private T data;

    public ResultVO() {

    }

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
