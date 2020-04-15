package com.springcloud.generatecode.generate.entity;

import lombok.Data;

/**
 * 代码生成器 表列信息
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/04/13 20:24
 */
@Data
public class FieldInfo {

    /**
     * 数据库列名
     */
    private String dbColumnName;

    /**
     * 数据库列类型
     */
    private String dbColumnType;

    /**
     * java字段名
     */
    private String javaJavaHumpColumnName;

    /**
     * java列字段类型
     */
    private String javaColumnType;

    /**
     * 是否作为分页条件
     */
    private Boolean paginationQueryCondition = false;

    public FieldInfo(){

    }
    public FieldInfo(String dbColumnName, String dbColumnType){
        this.dbColumnName = dbColumnName;
        this.dbColumnType = dbColumnType;
    }

    public FieldInfo(String dbColumnName, String dbColumnType, String javaColumnType, Boolean paginationQueryCondition){
        this.dbColumnName = dbColumnName;
        this.dbColumnType = dbColumnType;
        this.javaColumnType = javaColumnType;
        this.paginationQueryCondition = paginationQueryCondition;
    }
}
