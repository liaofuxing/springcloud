package com.springcloud.generatecode.generate.dto;

import com.springcloud.generatecode.generate.entity.FieldInfo;
import lombok.Data;

import java.util.List;

/**
 *
 * GenerateCodeDto
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/04/13 21:40
 */
@Data
public class GenerateCodeDto {

    /**
     * 表名(前端下拉框选中的)
     */
    private String tableName;

    /**
     * 是否仅生成javaBean
     */
    private Boolean onlyGenerateJavaBean;

    /**
     * 包路径
     */
    private String packagePath;

    /**
     * 是否需要分页
     */
    private Boolean needPagination;

    /**
     * 字段列表
     */
    private List<FieldInfo> tableFieldList;

}
