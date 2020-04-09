package com.springcloud.generatecode.utils;

import com.springcloud.generatecode.common.GenerateConstants;

/**
 * 数据库字段(表名)转换java驼峰语法工具类
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/04/09 19:56
 */
public class MysqlFieldConvertJavaHumpUtils {

    /**
     *
     * 字段转换
     *
     * @param mysqlField mysql字段
     * @return javaHump java驼峰字符串
     */
    public static String mysqlFieldConvertJavaHump(String mysqlField) {
        // 替换数据库中的"_" 用java驼峰命名
        String javaHump;
        if (mysqlField.contains(GenerateConstants.DB_LINK_TAG)) {
            // 第一个出现的索引位置
            StringBuilder mysqlFieldBuilder = new StringBuilder(mysqlField);
            int indexOf = mysqlField.indexOf(GenerateConstants.DB_LINK_TAG);
            char c = mysqlField.charAt(indexOf + 1);
            javaHump = mysqlFieldBuilder.replace(indexOf,
                            indexOf + 2,
                            GenerateConstants.SPACE_STRING + String.valueOf(c).toUpperCase()).toString();
            while (indexOf != -1) {
                //从这个索引往后开始第一个出现的位置
                indexOf = mysqlField.indexOf(GenerateConstants.DB_LINK_TAG, indexOf + 1);
                if (indexOf != -1) {
                    javaHump = mysqlFieldConvertJavaHump(javaHump);
                }
            }
        } else {
            javaHump = mysqlField;
        }
        return javaHump.replace(GenerateConstants.SPACE_STRING,"");
    }

    /**
     * 表名转换
     *
     * @param mysqlTableName mysql表名
     * @return tableNameBuilder.toString() 表名驼峰字符串
     *
     */
    public static String mysqlTableNameConvertJavaHump(String mysqlTableName) {
        String tableName = mysqlFieldConvertJavaHump(mysqlTableName);
        StringBuilder tableNameBuilder = new StringBuilder(tableName);
        String firstStr = tableName.substring(0, 1);
        tableNameBuilder.replace(0,1, firstStr.toUpperCase());

        return tableNameBuilder.toString();
    }
}
