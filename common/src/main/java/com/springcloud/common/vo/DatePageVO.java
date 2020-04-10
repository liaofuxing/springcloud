package com.springcloud.common.vo;

import lombok.Data;

import java.util.List;

/**
 * 分页数据返回类
 * @author liaofuxing
 * @date 2020/03/13 22:01
 */

/**
 *
 * @param <T> 需要返回数据的泛型类型
 */
@Data
public class DatePageVO<T>{

    /**
     * 一共多少条数据(分页)
     */
    private Long total;

    /**
     * 分页的的实际数据
     */
    private List<T> pageData;

    public DatePageVO() {

    }

    public DatePageVO(Long total, List<T> pageData) {
        this.total = total;
        this.pageData = pageData;
    }
}
