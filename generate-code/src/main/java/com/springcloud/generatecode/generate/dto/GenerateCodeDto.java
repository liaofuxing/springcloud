package com.springcloud.generatecode.generate.dto;

import com.springcloud.generatecode.generate.entity.FieldInfo;
import lombok.Data;

import java.util.List;

@Data
public class GenerateCodeDto {

    private String tableName;

    private Boolean onlyGenerateJavaBean;

    private String packagePath;

    private Boolean needPagination;

    private List<FieldInfo> tableFieldList;

}
