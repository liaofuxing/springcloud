package com.springcloud.generatecode.common;

import lombok.Getter;

/**
 *
 * 代码生成器mysqlType和javaType对照枚举
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/04/09 15:24
 */
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

    /**
     *
     * 通过 mysqlType获取javaType
     *
     * @param mysqlType mysql类型
     * @return javaType
     */
    public static String getJavaType(String mysqlType) {
        for (GenerateMysqlType2JavaTypeEnum g : GenerateMysqlType2JavaTypeEnum.values()) {
            if (g.getMysqlType().equals(mysqlType)) {
                return g.getJavaType();
            }
        }
        return null;
    }
}
