package com.springcloud.generatecode.generate.entity;

import lombok.Data;

@Data
public class FieldInfo {

    private String dbColumnName;

    private String dbColumnType;

    private String javaColumnType;

    private String javaJavaHumpColumnName;

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
