package com.springcloud.generatecode.generate.service.impl;

import com.springcloud.generatecode.commcon.GenerateConstants;
import com.springcloud.generatecode.commcon.GenerateMysqlType2JavaTypeEnum;
import com.springcloud.generatecode.generate.entity.FieldInfo;
import com.springcloud.generatecode.generate.service.GenerateCodeService;
import com.springcloud.generatecode.utils.DBUtils;
import com.springcloud.generatecode.utils.MysqlFieldConvertJavaHumpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenerateCodeServiceImpl implements GenerateCodeService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public List<String> generateEntityCode(String tableName) throws IOException {
        FileReader fr = null;
        BufferedReader bf = null;
        List<String> templateInner = new ArrayList<>();
        try {
            DBUtils dbUtils = new DBUtils(dataSource);
            List<FieldInfo> systemUserFields = dbUtils.getDBFields(tableName);
            Resource resource = resourceLoader.getResource("classpath:templates/entity/entityTemplate.tmp");
            File file = resource.getFile();
            fr = new FileReader(file);
            bf = new BufferedReader(fr);
            String str;

            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                if (str.contains(GenerateConstants.TABLE_NAME)) {
                    str = str.replace(GenerateConstants.TABLE_NAME, tableName);
                }
                if (str.contains(GenerateConstants.CLASS_NAME)) {
                    String javaHumpClassName = MysqlFieldConvertJavaHumpUtils.mysqlTableNameConvertJavaHump(tableName);
                    str = str.replace(GenerateConstants.CLASS_NAME, javaHumpClassName);
                }
                templateInner.add(str);
            }

            // mysql 列转化为java bean
            for (FieldInfo fieldInfo : systemUserFields) {

                String dbColumnName = fieldInfo.getDbColumnName();
                if (!"id".equals(dbColumnName)){
                    String dbColumnTypeName = fieldInfo.getDbColumnTypeName();
                    // mysql类型转java类型
                    String javaType = GenerateMysqlType2JavaTypeEnum.getJavaType(dbColumnTypeName);
                    // mysql列风格转换java驼峰风格
                    String javaHumpField = MysqlFieldConvertJavaHumpUtils.mysqlFieldConvertJavaHump(dbColumnName);

                    String javaLineTxt = GenerateConstants.TAB_STRING
                                            +GenerateConstants.JAVA_SCOPE_PUBLIC
                                            + GenerateConstants.SPACE_STRING
                                            + javaType
                                            + GenerateConstants.SPACE_STRING
                                            + javaHumpField
                                            + GenerateConstants.JAVA_GRAMMAR_END_TAG;
                    templateInner.add(javaLineTxt);
                }
            }
            templateInner.add(GenerateConstants.JAVA_TXT_END_TAG);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            assert bf != null;
            bf.close();
            fr.close();
        }
        return templateInner;
    }
}
