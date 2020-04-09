package com.springcloud.generatecode.common;

/**
 *
 * 代码生成器常量类
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/04/09 15:24
 */
public class GenerateConstants {

    /**
     * entity Template 类路径(模板路径)
     */
    public static final String ENTITY_TEMPLATE_PATH = "classpath:templates/entity/entityTemplate.tmp";

    /**
     * 表名
     */
    public static final String TABLE_NAME = "${tableName}";

    /**
     * 类名
     */
    public static final String CLASS_NAME = "${className}";

    /**
     * java 包路径
     */
    public static final String PACKAGE_PATH = "${packagePath}";

    /**
     * 数据库字段连接符
     */
    public static final String DB_LINK_TAG = "_";

    /**
     * java类结束花括号
     */
    public static final String JAVA_TXT_END_TAG = "}";

    /**
     * java语法结束符号
     */
    public static final String JAVA_GRAMMAR_END_TAG = ";";

    /**
     * java作用域修饰符 public
     */
    public static final String JAVA_SCOPE_PUBLIC = "public";

    /**
     * java作用域修饰符 private
     */
    public static final String JAVA_SCOPE_PRIVATE = "private";

    /**
     * 空格
     */
    public static final String SPACE_STRING = " ";

    /**
     * 制表符
     */
    public static final String TAB_STRING = "\t";

}
