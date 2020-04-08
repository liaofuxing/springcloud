package com.springcloud.generatecode.generate.entity;

import lombok.Data;

@Data
public class FieldInfo {

    private String dbColumnName;

    private String dbColumnTypeName;

    public FieldInfo(String dbColumnName, String dbColumnTypeName){
        this.dbColumnName = dbColumnName;
        this.dbColumnTypeName = dbColumnTypeName;
    }
}
