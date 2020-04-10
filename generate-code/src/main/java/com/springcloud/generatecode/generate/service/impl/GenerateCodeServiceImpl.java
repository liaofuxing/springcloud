package com.springcloud.generatecode.generate.service.impl;

import com.springcloud.generatecode.common.GenerateConstants;
import com.springcloud.generatecode.common.GenerateMysqlType2JavaTypeEnum;
import com.springcloud.generatecode.generate.dto.GenerateCodeDto;
import com.springcloud.generatecode.generate.entity.FieldInfo;
import com.springcloud.common.vo.SelectFormatVO;
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
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 代码生成器Service
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/04/09 20:24
 */
@Service
public class GenerateCodeServiceImpl implements GenerateCodeService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;


    @Override
    public List<FieldInfo> getTableField(String tableName) throws SQLException {
        List<FieldInfo> tableFields = null;
        try {
            DBUtils dbUtils = new DBUtils(dataSource);
            tableFields = dbUtils.getDBFields(tableName);
            for (FieldInfo fieldInfo: tableFields) {
                // mysql列风格转换java驼峰风格
                String javaHumpField = MysqlFieldConvertJavaHumpUtils.mysqlFieldConvertJavaHump(fieldInfo.getDbColumnName());
                // mysql类型转java类型
                String javaType = GenerateMysqlType2JavaTypeEnum.getJavaType(fieldInfo.getDbColumnType());
                fieldInfo.setJavaJavaHumpColumnName(javaHumpField);
                fieldInfo.setJavaColumnType(javaType);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tableFields;
    }

    @Override
    public List<String> generateCode(GenerateCodeDto generateCodeDto) throws IOException {
        if(generateCodeDto.getOnlyGenerateJavaBean()) {
            return generateEntityCode(generateCodeDto);
        }else {
            return null;
        }
    }

    /**
     * 生成entity
     *
     * @param generateCodeDto 生成器Dto
     * @return templateInner 实际生成java文件的字符流
     * @throws IOException 异常
     */
    @Override
    public List<String> generateEntityCode(GenerateCodeDto generateCodeDto) throws IOException {
        FileReader fr = null;
        BufferedReader bf = null;
        List<String> templateInner = new ArrayList<>();
        String tableName = generateCodeDto.getTableName();
        try {
            Resource resource = resourceLoader.getResource(GenerateConstants.ENTITY_TEMPLATE_PATH);
            File file = resource.getFile();
            fr = new FileReader(file);
            bf = new BufferedReader(fr);
            String str;

            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                if (str.contains(GenerateConstants.PACKAGE_PATH)) {
                    str = str.replace(GenerateConstants.PACKAGE_PATH, generateCodeDto.getPackagePath())
                            + GenerateConstants.JAVA_GRAMMAR_END_TAG;
                }
                if (str.contains(GenerateConstants.GENERATE_DATE)) {
                    ZonedDateTime now = ZonedDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    str = str.replace(GenerateConstants.GENERATE_DATE, now.format(formatter));
                }
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
            for (FieldInfo fieldInfo : generateCodeDto.getTableFieldList()) {

                if (!"id".equals(fieldInfo.getJavaJavaHumpColumnName())){
                    String javaLineTxt = GenerateConstants.TAB_STRING
                            + GenerateConstants.JAVA_SCOPE_PRIVATE
                            + GenerateConstants.SPACE_STRING
                            + fieldInfo.getJavaColumnType()
                            + GenerateConstants.SPACE_STRING
                            + fieldInfo.getJavaJavaHumpColumnName()
                            + GenerateConstants.JAVA_GRAMMAR_END_TAG;
                    templateInner.add(javaLineTxt);
                }
            }
            templateInner.add(GenerateConstants.JAVA_TXT_END_TAG);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert bf != null;
            bf.close();
            fr.close();
        }
        return templateInner;
    }


    @Override
    public List<SelectFormatVO> getMysqlTableSelect() throws SQLException {
        DBUtils dbUtils = new DBUtils(dataSource);
        List<String> tables = dbUtils.getTables();
        List<SelectFormatVO> selectFormatVOList = new ArrayList<>();
        for (String tableName: tables ) {
            SelectFormatVO selectFormatVO = new SelectFormatVO(tableName,tableName );
            selectFormatVOList.add(selectFormatVO);
        }
        return selectFormatVOList;
    }
}
