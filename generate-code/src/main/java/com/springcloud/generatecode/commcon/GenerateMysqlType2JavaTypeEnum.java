package com.springcloud.generatecode.commcon;

import lombok.Getter;

@Getter
public enum  GenerateMysqlType2JavaTypeEnum {

    MYSQL_TYPE_INT("INT", "Integer"),
    MYSQL_TYPE_CHAR("CHAR", "String"),
    MYSQL_TYPE_VARCHAR("VARCHAR", "String"),
    MYSQL_TYPE_DATETIME("DATETIME", "String"),
    ;

    private String mysqlType;

    private String javaType;

    GenerateMysqlType2JavaTypeEnum(String mysqlType, String javaType) {
        this.mysqlType = mysqlType;
        this.javaType = javaType;
    }

    // 普通方法
    public static String getJavaType(String mysqlType) {
        for (GenerateMysqlType2JavaTypeEnum g : GenerateMysqlType2JavaTypeEnum.values()) {
            if (g.getMysqlType().equals(mysqlType)) {
                return g.getJavaType();
            }
        }
        return null;
    }
}
