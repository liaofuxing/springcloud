package com.springcloud.generatecode.generate.service.impl;

import com.springcloud.common.utils.ZipUtils;
import com.springcloud.common.vo.SelectFormatVO;
import com.springcloud.generatecode.common.*;
import com.springcloud.generatecode.common.properties.GenerateProperties;
import com.springcloud.generatecode.generate.dto.GenerateCodeDto;
import com.springcloud.generatecode.generate.entity.FieldInfo;
import com.springcloud.generatecode.generate.service.GenerateCodeService;
import com.springcloud.generatecode.utils.DBUtils;
import com.springcloud.generatecode.utils.MysqlFieldConvertJavaHumpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 代码生成器 ServiceImpl
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/04/13 20:24
 */
@Service
public class GenerateCodeServiceImpl implements GenerateCodeService {


    private final String dtoClassSuffix = GenerateSuffixConstants.DTO_CLASS_SUFFIX;

    private final String voClassSuffix = GenerateSuffixConstants.VO_CLASS_SUFFIX;

    private final String daoClassSuffix = GenerateSuffixConstants.DAO_CLASS_SUFFIX;

    private final String serviceClassSuffix = GenerateSuffixConstants.SERVICE_CLASS_SUFFIX;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private GenerateProperties generateProperties;


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
                String javaType = GenerateMysqlType2JavaTypeEnums.getJavaType(fieldInfo.getDbColumnType());
                fieldInfo.setJavaJavaHumpColumnName(javaHumpField);
                fieldInfo.setJavaColumnType(javaType);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tableFields;
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

    @Override
    public void generateCode(GenerateCodeDto generateCodeDto, String token) throws IOException {
        Map<String, String> pathMap = generateCodeFileDir(generateCodeDto.getPackagePath(), token);
        String sourceDirPath = pathMap.get("sourceDirPath");
        String zipDirPath = pathMap.get("zipDirPath");
        if(generateCodeDto.getOnlyGenerateJavaBean()) {
            generateEntityCode(generateCodeDto, sourceDirPath, GenerateTemplatePathConstants.ENTITY_TEMPLATE_PATH);
        }else {
            generateEntityCode(generateCodeDto, sourceDirPath, GenerateTemplatePathConstants.ENTITY_TEMPLATE_PATH);

            generateCodeAll(generateCodeDto, sourceDirPath);
        }

        // zip OutputStream eg: this zip file  writer path : "F://xxx/xxx.zip"
        FileOutputStream fos1 = new FileOutputStream(new File(  zipDirPath + GenerateSuffixConstants.PATH_SIGN_SUFFIX + generateCodeDto.getTableName() + GenerateSuffixConstants.ZIP_FILE_SUFFIX));

        ZipUtils.toZip(generateProperties.getGenerateFileRootPath()
                + GenerateSuffixConstants.PATH_SIGN_SUFFIX
                +token+GenerateSuffixConstants.PATH_SIGN_SUFFIX,
                fos1, true);
    }

    public void generateCodeAll(GenerateCodeDto generateCodeDto, String sourceDirPath) throws IOException {

        generateEntityDtoCode(generateCodeDto, sourceDirPath);

        generateEntityVOCode(generateCodeDto, sourceDirPath);

        generateDaoCode(generateCodeDto, sourceDirPath);

        generateServiceCode(generateCodeDto, sourceDirPath);

        generateServiceImplCode(generateCodeDto, sourceDirPath);

        generateControllerCode(generateCodeDto, sourceDirPath);
    }

    public Map<String, String> generateCodeFileDir(String packagePath, String token) {
        String generateFileRootPath = generateProperties.getGenerateFileRootPath() + GenerateSuffixConstants.PATH_SIGN_SUFFIX + token;
        String[] packagePathArr = packagePath.split("\\.");
        StringBuilder packagePathDir = new StringBuilder(GenerateSuffixConstants.PATH_SIGN_SUFFIX);
        for (String packagePathStr: packagePathArr) {
            packagePathDir.append(packagePathStr).append(GenerateSuffixConstants.PATH_SIGN_SUFFIX);
        }
        String generatePath = generateFileRootPath + packagePathDir.toString();

        List<String> mkdirPathList = new ArrayList<>();
        mkdirPathList.add(generatePath + GenerateSuffixConstants.PATH_SIGN_SUFFIX + GenerateSuffixConstants.ENTITY_PATH_SUFFIX);
        mkdirPathList.add(generatePath + GenerateSuffixConstants.PATH_SIGN_SUFFIX + GenerateSuffixConstants.SERVICE_PATH_SUFFIX);
        mkdirPathList.add(generatePath + GenerateSuffixConstants.PATH_SIGN_SUFFIX + GenerateSuffixConstants.SERVICE_IMPL_PATH_SUFFIX);
        mkdirPathList.add(generatePath + GenerateSuffixConstants.PATH_SIGN_SUFFIX + GenerateSuffixConstants.DAO_PATH_SUFFIX);
        mkdirPathList.add(generatePath + GenerateSuffixConstants.PATH_SIGN_SUFFIX + GenerateSuffixConstants.DTO_PATH_SUFFIX);
        mkdirPathList.add(generatePath + GenerateSuffixConstants.PATH_SIGN_SUFFIX + GenerateSuffixConstants.VO_PATH_SUFFIX);
        mkdirPathList.add(generatePath + GenerateSuffixConstants.PATH_SIGN_SUFFIX + GenerateSuffixConstants.CONTROLLER_PATH_SUFFIX);


        // mkdirs dir
        for (String mkdirPath: mkdirPathList) {
            File file = new File(mkdirPath);
            if(!file.exists() && !file.isDirectory()) {
                file.mkdirs();
            }
        }

        // mkdir zip dir
        String generateFileRootPathZip = generateProperties.getGenerateFileRootPath()
                + GenerateSuffixConstants.PATH_SIGN_SUFFIX
                + token
                + GenerateSuffixConstants.ZIP_PATH_SUFFIX;
        File fileZip = new File(generateFileRootPathZip);
        if(!fileZip.exists() && !fileZip.isDirectory()) {
            fileZip.mkdir();
        }
        HashMap<String, String> pathMap = new HashMap<>();
        pathMap.put("sourceDirPath", generatePath);
        pathMap.put("zipDirPath", generateFileRootPathZip);
        return pathMap;
    }


    public void generateEntityDtoCode(GenerateCodeDto generateCodeDto, String sourceDirPath) throws IOException {
        generateEntityCode(generateCodeDto, sourceDirPath, GenerateTemplatePathConstants.ENTITY_DTO_TEMPLATE_PATH);
    }

    public void generateEntityVOCode(GenerateCodeDto generateCodeDto, String sourceDirPath) throws IOException {
        generateEntityCode(generateCodeDto, sourceDirPath, GenerateTemplatePathConstants.ENTITY_VO_TEMPLATE_PATH);
    }


    /**
     * 生成entity
     *
     * @param generateCodeDto 生成器Dto
     * @throws IOException 异常
     */
    public void generateEntityCode(GenerateCodeDto generateCodeDto, String sourceDirPath, String templatePath) throws IOException {

        String className = "";
        String packagePath = "";
        if (templatePath.equals(GenerateTemplatePathConstants.ENTITY_TEMPLATE_PATH)) {
            className = MysqlFieldConvertJavaHumpUtils.mysqlTableNameConvertJavaHump(generateCodeDto.getTableName());
            packagePath = GenerateSuffixConstants.ENTITY_PATH_SUFFIX;
        }
        if (templatePath.equals(GenerateTemplatePathConstants.ENTITY_DTO_TEMPLATE_PATH)) {
            className = MysqlFieldConvertJavaHumpUtils.mysqlTableNameConvertJavaHump(generateCodeDto.getTableName()) + GenerateSuffixConstants.DTO_CLASS_SUFFIX;
            packagePath = GenerateSuffixConstants.DTO_PATH_SUFFIX;
        }

        if (templatePath.equals(GenerateTemplatePathConstants.ENTITY_VO_TEMPLATE_PATH)) {
            className = MysqlFieldConvertJavaHumpUtils.mysqlTableNameConvertJavaHump(generateCodeDto.getTableName()) + GenerateSuffixConstants.VO_CLASS_SUFFIX;
            packagePath = GenerateSuffixConstants.VO_PATH_SUFFIX;
        }

        List<String> template = getTemplate(templatePath);

        List<String> templateReplace = new ArrayList<>();
        for (String templateLine : template) {
            templateLine = templateReplaceBasic(generateCodeDto, templateLine, className, packagePath);
            if (templateLine.contains(GenerateReplaceSymbolConstants.TABLE_NAME)) {
                String tableName = generateCodeDto.getTableName();
                templateLine = templateLine.replace(GenerateReplaceSymbolConstants.TABLE_NAME, tableName);
            }
            templateReplace.add(templateLine);
        }

        // mysql 列转化为java bean
        for (FieldInfo fieldInfo : generateCodeDto.getTableFieldList()) {

            if (!"id".equals(fieldInfo.getJavaJavaHumpColumnName())) {
                String javaLineTxt = GenerateConstants.TAB_STRING
                        + GenerateConstants.JAVA_SCOPE_PRIVATE
                        + GenerateConstants.SPACE_STRING
                        + fieldInfo.getJavaColumnType()
                        + GenerateConstants.SPACE_STRING
                        + fieldInfo.getJavaJavaHumpColumnName()
                        + GenerateConstants.JAVA_GRAMMAR_END_TAG;
                templateReplace.add(javaLineTxt);
            }
        }

        templateReplace.add(GenerateConstants.JAVA_TXT_END_TAG);

        String writerPath = sourceDirPath
                + packagePath
                + GenerateSuffixConstants.PATH_SIGN_SUFFIX
                + className
                + GenerateSuffixConstants.JAVA_FILE_SUFFIX;

        writerFile(writerPath, templateReplace);

    }


    /**
     * 生成dao
     *
     * @param generateCodeDto 生成器Dto
     * @throws IOException 异常
     */
    public void generateDaoCode(GenerateCodeDto generateCodeDto, String sourceDirPath) throws IOException {

        String classSuffix = GenerateSuffixConstants.DAO_CLASS_SUFFIX;
        String entity = MysqlFieldConvertJavaHumpUtils.mysqlTableNameConvertJavaHump(generateCodeDto.getTableName());
        String className = entity + classSuffix;

        List<String> template = getTemplate(GenerateTemplatePathConstants.DAO_TEMPLATE_PATH);

        List<String> templateReplace = new ArrayList<>();
        for (String templateLine : template) {
            templateLine = templateReplaceBasic(generateCodeDto, templateLine, className, GenerateSuffixConstants.DAO_PATH_SUFFIX);

            if (templateLine.contains(GenerateReplaceSymbolConstants.IMPORT_ENTITY)) {
                String importEntity = GenerateConstants.JAVA_SCOPE_IMPORT
                        + GenerateConstants.SPACE_STRING
                        + generateCodeDto.getPackagePath()
                        + GenerateSuffixConstants.POINT_PATH_SUFFIX
                        + GenerateSuffixConstants.ENTITY_PATH_SUFFIX
                        + GenerateSuffixConstants.POINT_PATH_SUFFIX
                        + entity
                        + GenerateConstants.JAVA_GRAMMAR_END_TAG;
                templateLine = templateLine.replace(GenerateReplaceSymbolConstants.IMPORT_ENTITY, importEntity);
            }

            if (templateLine.contains(GenerateReplaceSymbolConstants.JPA_REPOSITORY)) {

                // 需要分页
                String jpaRepository;
                if (generateCodeDto.getNeedPagination()) {
                    jpaRepository = "JpaRepository<" + entity + ", " + "Integer>, " + "JpaSpecificationExecutor<" + entity + ">";
                } else {
                    jpaRepository = "JpaRepository<" + entity + ", " + "Integer>";
                }

                templateLine = templateLine.replace(GenerateReplaceSymbolConstants.JPA_REPOSITORY, jpaRepository);
            }
            templateReplace.add(templateLine);
        }

        templateReplace.add(GenerateConstants.JAVA_TXT_END_TAG);

        String writerPath = sourceDirPath
                + GenerateSuffixConstants.DAO_PATH_SUFFIX
                + GenerateSuffixConstants.PATH_SIGN_SUFFIX
                + className
                + GenerateSuffixConstants.JAVA_FILE_SUFFIX;

        writerFile(writerPath, templateReplace);

    }


    /**
     * 生成 Service
     *
     * @param generateCodeDto 生成器Dto
     * @throws IOException 异常
     */
    public void generateServiceCode(GenerateCodeDto generateCodeDto, String sourceDirPath) throws IOException {
        String classSuffix = GenerateSuffixConstants.SERVICE_CLASS_SUFFIX;
        String entity = MysqlFieldConvertJavaHumpUtils.mysqlTableNameConvertJavaHump(generateCodeDto.getTableName());
        String className = entity + classSuffix;
        String dtoClassName = entity + dtoClassSuffix;
        String voClassName = entity + voClassSuffix;

        String entityParam = MysqlFieldConvertJavaHumpUtils.mysqlFieldConvertJavaHump(generateCodeDto.getTableName());

        List<String> template = getTemplate(GenerateTemplatePathConstants.SERVICE_TEMPLATE_PATH);
        if(generateCodeDto.getNeedPagination()){
            List<String> commonServicePaginationTemplate = getTemplate(GenerateTemplatePathConstants.COMMON_SERVICE_PAGINATION_TEMPLATE);
            template.addAll(commonServicePaginationTemplate);
        }
        List<String> templateReplace = new ArrayList<>();
        for (String templateLine: template) {
            // 公共的模板项
            templateLine = templateReplaceBasic(generateCodeDto, templateLine, className, GenerateSuffixConstants.SERVICE_PATH_SUFFIX);

            // import 模板项 start
            templateLine = templateImportService(generateCodeDto, entity, dtoClassName, voClassName, templateLine);
            // import 模板项 end

            // template Replace start
            templateLine = templateReplaceService(entity, dtoClassName, voClassName,  dtoClassSuffix, voClassSuffix, entityParam, templateLine);
            // template Replace end

            templateReplace.add(templateLine);
        }

        templateReplace.add(GenerateConstants.JAVA_TXT_END_TAG);
            String writerPath = sourceDirPath
                    + GenerateSuffixConstants.SERVICE_PATH_SUFFIX
                    + GenerateSuffixConstants. PATH_SIGN_SUFFIX
                    + className
                    + GenerateSuffixConstants.JAVA_FILE_SUFFIX;
            writerFile(writerPath, templateReplace);

    }


    /**
     * 生成ServiceImpl
     *
     * @param generateCodeDto 生成器Dto
     * @throws IOException 异常
     */
    public void generateServiceImplCode(GenerateCodeDto generateCodeDto, String sourceDirPath) throws IOException {
        // 类后缀
        String classSuffix = GenerateSuffixConstants.SERVICE_CLASS_SUFFIX_IMPL;

        String entity = MysqlFieldConvertJavaHumpUtils.mysqlTableNameConvertJavaHump(generateCodeDto.getTableName());
        String className = entity + classSuffix;
        String serviceClassName = entity + serviceClassSuffix;
        String daoClassName = entity + daoClassSuffix;
        String dtoClassName = entity + dtoClassSuffix;
        String voClassName = entity + voClassSuffix;

        String entityParam = MysqlFieldConvertJavaHumpUtils.mysqlFieldConvertJavaHump(generateCodeDto.getTableName());
        List<String> template = getTemplate(GenerateTemplatePathConstants.SERVICE_IMPL_TEMPLATE_PATH);
        if(generateCodeDto.getNeedPagination()){
            List<String> commonServiceImplPaginationTemplate = templateReplacePagination(generateCodeDto, entity, dtoClassName, voClassName, daoClassName, dtoClassSuffix, voClassSuffix, entityParam, serviceClassName);
            template.addAll(commonServiceImplPaginationTemplate);
        }

        List<String> templateReplace = new ArrayList<>();
        for (String templateLine: template) {
            // 公共的模板项 start
            templateLine = templateReplaceBasic(generateCodeDto, templateLine, className, GenerateSuffixConstants.SERVICE_IMPL_CLASSPATH_SUFFIX);
            // 公共的模板项 end

            // import 模板项 start
            templateLine = templateImportService(generateCodeDto, entity, dtoClassName, voClassName, templateLine);

            templateLine = templateImportServiceImpl(generateCodeDto, serviceClassName, daoClassName, templateLine);
            // import 模板项 end

            // templateReplace start
            templateLine = templateReplaceService(entity, dtoClassName, voClassName,  dtoClassSuffix, voClassSuffix, entityParam, templateLine);

            templateLine = templateReplaceServiceImpl( daoClassName, serviceClassName, daoClassSuffix, entityParam, templateLine);
            // templateReplace end

            templateReplace.add(templateLine);

        }


        templateReplace.add(GenerateConstants.JAVA_TXT_END_TAG);
        String writerPath = sourceDirPath
                    + GenerateSuffixConstants.SERVICE_IMPL_PATH_SUFFIX
                    + GenerateSuffixConstants. PATH_SIGN_SUFFIX
                    + className
                    + GenerateSuffixConstants.JAVA_FILE_SUFFIX;
        writerFile(writerPath, templateReplace);


    }

    /**
     * 生成 Controller
     *
     * @param generateCodeDto 生成器Dto
     * @throws IOException 异常
     */
    public void generateControllerCode(GenerateCodeDto generateCodeDto, String sourceDirPath) throws IOException {
        // 类后缀
        String classSuffix = GenerateSuffixConstants.CONTROLLER_CLASS_SUFFIX;
        String entity = MysqlFieldConvertJavaHumpUtils.mysqlTableNameConvertJavaHump(generateCodeDto.getTableName());
        String className = entity + classSuffix;
        String serviceClassName = entity + serviceClassSuffix;
        String daoClassName = entity + daoClassSuffix;
        String dtoClassName = entity + dtoClassSuffix;
        String voClassName = entity + voClassSuffix;

        String entityParam = MysqlFieldConvertJavaHumpUtils.mysqlFieldConvertJavaHump(generateCodeDto.getTableName());
        List<String> template = getTemplate(GenerateTemplatePathConstants.CONTROLLER_TEMPLATE_PATH);


        if(generateCodeDto.getNeedPagination()){
            List<String> templateMethod = getTemplate(GenerateTemplatePathConstants.COMMON_CONTROLLER_PAGINATION_TEMPLATE);
            template.addAll(templateMethod);
        }

        List<String> templateReplace = new ArrayList<>();
        for (String templateLine: template) {
            // 公共的模板项 start
            templateLine = templateReplaceBasic(generateCodeDto, templateLine, className, GenerateSuffixConstants.CONTROLLER_PATH_SUFFIX);
            // 公共的模板项 end


            //Service 公共模板导入和替换，Controller 同样适用， 并添加特有模板替换即可
            // import 模板项 start
            templateLine = templateImportService(generateCodeDto, entity, dtoClassName, voClassName, templateLine);

            templateLine = templateImportServiceImpl(generateCodeDto, serviceClassName, daoClassName, templateLine);
            // import 模板项 end

            // templateReplace start
            templateLine = templateReplaceService(entity, dtoClassName, voClassName,  dtoClassSuffix, voClassSuffix, entityParam, templateLine);

            templateLine = templateReplaceServiceImpl( daoClassName, serviceClassName, daoClassSuffix, entityParam, templateLine);
            // templateReplace end

            // 特有模板
            if (templateLine.contains(GenerateReplaceSymbolConstants.ENTITY_SERVICE_PARAM)) {
                String entityServiceParam = entityParam + serviceClassSuffix;
                templateLine = templateLine.replace(GenerateReplaceSymbolConstants.ENTITY_SERVICE_PARAM, entityServiceParam);
            }

            if (templateLine.contains(GenerateReplaceSymbolConstants.CONTROLLER_REQUEST_URL)) {
                String controllerRequestUrl =  GenerateSuffixConstants.PATH_SIGN_SUFFIX + entityParam;
                templateLine = templateLine.replace(GenerateReplaceSymbolConstants.CONTROLLER_REQUEST_URL, controllerRequestUrl);
            }

            if (templateLine.contains(GenerateReplaceSymbolConstants.ENTITY_SERVICE)) {
                templateLine = templateLine.replace(GenerateReplaceSymbolConstants.ENTITY_SERVICE, serviceClassName);
            }

            templateReplace.add(templateLine);
        }

        templateReplace.add(GenerateConstants.JAVA_TXT_END_TAG);
        String writerPath = sourceDirPath
                    + GenerateSuffixConstants.CONTROLLER_PATH_SUFFIX
                    + GenerateSuffixConstants.PATH_SIGN_SUFFIX
                    + className
                    + GenerateSuffixConstants.JAVA_FILE_SUFFIX;
        writerFile(writerPath, templateReplace);
    }

    /**
     * Service 公共 Import 模板
     * @param generateCodeDto 代码生成器参数
     * @param entity entity类名
     * @param dtoClassName dto类名
     * @param voClassName vo类名
     * @param str  上级方法传入的字符流
     * @return 处理完的字符流
     */
    private String templateImportService(GenerateCodeDto generateCodeDto,
                                         String entity,
                                         String dtoClassName,
                                         String voClassName,
                                         String str) {
        if (str.contains(GenerateReplaceSymbolConstants.IMPORT_ENTITY)) {
            String importEntity = GenerateConstants.JAVA_SCOPE_IMPORT
                    + GenerateConstants.SPACE_STRING
                    + generateCodeDto.getPackagePath()
                    + GenerateSuffixConstants.POINT_PATH_SUFFIX
                    + GenerateSuffixConstants.ENTITY_PATH_SUFFIX
                    + GenerateSuffixConstants.POINT_PATH_SUFFIX
                    + entity
                    + GenerateConstants.JAVA_GRAMMAR_END_TAG;
            str = str.replace(GenerateReplaceSymbolConstants.IMPORT_ENTITY, importEntity);
        }

        if (str.contains(GenerateReplaceSymbolConstants.IMPORT_DTO)) {
            String importDto = GenerateConstants.JAVA_SCOPE_IMPORT
                    + GenerateConstants.SPACE_STRING
                    + generateCodeDto.getPackagePath()
                    + GenerateSuffixConstants.POINT_PATH_SUFFIX
                    + GenerateSuffixConstants.DTO_PATH_SUFFIX
                    + GenerateSuffixConstants.POINT_PATH_SUFFIX
                    + dtoClassName
                    + GenerateConstants.JAVA_GRAMMAR_END_TAG;
            str = str.replace(GenerateReplaceSymbolConstants.IMPORT_DTO, importDto);
        }

        if (str.contains(GenerateReplaceSymbolConstants.IMPORT_VO)) {

            String importVo = GenerateConstants.JAVA_SCOPE_IMPORT
                    + GenerateConstants.SPACE_STRING
                    + generateCodeDto.getPackagePath()
                    + GenerateSuffixConstants.POINT_PATH_SUFFIX
                    + GenerateSuffixConstants.VO_PATH_SUFFIX
                    + GenerateSuffixConstants.POINT_PATH_SUFFIX
                    + voClassName
                    + GenerateConstants.JAVA_GRAMMAR_END_TAG;
            str = str.replace(GenerateReplaceSymbolConstants.IMPORT_VO, importVo);
        }
        return str;
    }

    /**
     * ServiceImpl 公共 Import 模板
     *
     * @param generateCodeDto 代码生成器参数
     * @param serviceClassName service类名
     * @param daoClassName dao类名
     * @param str str  上级方法传入的字符流
     * @return 处理完的字符流
     */
    private String templateImportServiceImpl(GenerateCodeDto generateCodeDto,
                                             String serviceClassName,
                                             String daoClassName,
                                             String str) {
        if (str.contains(GenerateReplaceSymbolConstants.IMPORT_SERVICE)) {

            String importService = GenerateConstants.JAVA_SCOPE_IMPORT
                    + GenerateConstants.SPACE_STRING
                    + generateCodeDto.getPackagePath()
                    + GenerateSuffixConstants.POINT_PATH_SUFFIX
                    + GenerateSuffixConstants.SERVICE_PATH_SUFFIX
                    + GenerateSuffixConstants.POINT_PATH_SUFFIX
                    + serviceClassName
                    + GenerateConstants.JAVA_GRAMMAR_END_TAG;
            str = str.replace(GenerateReplaceSymbolConstants.IMPORT_SERVICE, importService);
        }

        if (str.contains(GenerateReplaceSymbolConstants.IMPORT_DAO)) {

            String importService = GenerateConstants.JAVA_SCOPE_IMPORT
                    + GenerateConstants.SPACE_STRING
                    + generateCodeDto.getPackagePath()
                    + GenerateSuffixConstants.POINT_PATH_SUFFIX
                    + GenerateSuffixConstants.DAO_PATH_SUFFIX
                    + GenerateSuffixConstants.POINT_PATH_SUFFIX
                    + daoClassName
                    + GenerateConstants.JAVA_GRAMMAR_END_TAG;
            str = str.replace(GenerateReplaceSymbolConstants.IMPORT_DAO, importService);
        }
        return str;
    }

    /**
     * Service 公共 Replace 模板
     * @param entity entity 类名
     * @param dtoClassName dto类名
     * @param voClassName vo类名
     * @param dtoClassSuffix dto类后缀
     * @param voClassSuffix vo类后缀
     * @param entityParam   entity变量名
     * @param str  str  上级方法传入的字符流
     * @return 处理完的字符流
     */
    private String templateReplaceService(String entity,
                                          String dtoClassName,
                                          String voClassName,
                                          String dtoClassSuffix,
                                          String voClassSuffix,
                                          String entityParam,
                                          String str) {
        if (str.contains(GenerateReplaceSymbolConstants.ENTITY)) {
            str = str.replace(GenerateReplaceSymbolConstants.ENTITY, entity);
        }

        if (str.contains(GenerateReplaceSymbolConstants.ENTITY_DTO)) {
            str = str.replace(GenerateReplaceSymbolConstants.ENTITY_DTO, dtoClassName);
        }

        if (str.contains(GenerateReplaceSymbolConstants.ENTITY_VO)) {
            str = str.replace(GenerateReplaceSymbolConstants.ENTITY_VO, voClassName);
        }

        if (str.contains(GenerateReplaceSymbolConstants.ENTITY_DTO_PARAM)) {
            String entityDtoParam = entityParam + dtoClassSuffix;
            str = str.replace(GenerateReplaceSymbolConstants.ENTITY_DTO_PARAM, entityDtoParam);
        }

        if (str.contains(GenerateReplaceSymbolConstants.ENTITY_VO_PARAM)) {
            String entityVoParam = entityParam + voClassSuffix;
            str = str.replace(GenerateReplaceSymbolConstants.ENTITY_VO_PARAM, entityVoParam);
        }
        return str;
    }

    /**
     * ServiceImpl 公共 Replace 模板
     * @param daoClassName dao类名
     * @param serviceClassName service类名
     * @param daoClassSuffix dao后缀
     * @param entityParam entity变量名
     * @param str str  上级方法传入的字符流
     * @return 处理完的字符流
     */
    private String templateReplaceServiceImpl(String daoClassName,
                                              String serviceClassName,
                                              String daoClassSuffix,
                                              String entityParam,
                                              String str) {

        if (str.contains(GenerateReplaceSymbolConstants.ENTITY_DAO)) {
            str = str.replace(GenerateReplaceSymbolConstants.ENTITY_DAO, daoClassName);
        }

        if (str.contains(GenerateReplaceSymbolConstants.ENTITY_SERVICE_INTERFACE)) {
            str = str.replace(GenerateReplaceSymbolConstants.ENTITY_SERVICE_INTERFACE, serviceClassName);
        }

        if (str.contains(GenerateReplaceSymbolConstants.ENTITY_DAO_PARAM)) {
            String entityDaoParam = entityParam + daoClassSuffix;
            str = str.replace(GenerateReplaceSymbolConstants.ENTITY_DAO_PARAM, entityDaoParam);
        }

        if (str.contains(GenerateReplaceSymbolConstants.ENTITY_PARAM)) {
            str = str.replace(GenerateReplaceSymbolConstants.ENTITY_PARAM, entityParam);
        }
        return str;
    }

    /**
     *
     * Basic基础模板替换
     *
     * @param generateCodeDto 代码生成器参数
     * @param str    调用者传入的模板字符流
     * @param className 模板类名
     * @param classPathSuffix 包路径后缀
     * @return str 处理完成的模板字符流
     */
    public String templateReplaceBasic(GenerateCodeDto generateCodeDto, String str, String className, String classPathSuffix){
        if (str.contains(GenerateReplaceSymbolConstants.PACKAGE_PATH)) {
            str = str.replace(GenerateReplaceSymbolConstants.PACKAGE_PATH, generateCodeDto.getPackagePath() + GenerateSuffixConstants.POINT_PATH_SUFFIX + classPathSuffix)
                    + GenerateConstants.JAVA_GRAMMAR_END_TAG;
        }
        if (str.contains(GenerateReplaceSymbolConstants.GENERATE_DATE)) {
            ZonedDateTime now = ZonedDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            str = str.replace(GenerateReplaceSymbolConstants.GENERATE_DATE, now.format(formatter));
        }
        if (str.contains(GenerateReplaceSymbolConstants.CLASS_NAME)) {
            str = str.replace(GenerateReplaceSymbolConstants.CLASS_NAME, className);
        }
        return str;
    }


    public List<String> getTemplate(String templatePath) throws IOException {
        FileReader fr = null;
        BufferedReader bf = null;
        List<String> template = new ArrayList<>();
        try {
            Resource resource = resourceLoader.getResource(templatePath);
            File file = resource.getFile();
            fr = new FileReader(file);
            bf = new BufferedReader(fr);
            // 按行读取字符串
            String str;
            while ((str = bf.readLine()) != null) {
                template.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert bf != null;
            bf.close();
            fr.close();
        }
        return template;
    }

    /**
     *
     * Basic基础模板替换
     *
     * @param generateCodeDto 代码生成器参数
     * @return str 处理完成的模板字符流
     */
    public List<String> templateReplacePagination(GenerateCodeDto generateCodeDto,
                                                  String entity,
                                                  String dtoClassName,
                                                  String voClassName,
                                                  String daoClassName,
                                                  String dtoClassSuffix,
                                                  String voClassSuffix,
                                                  String entityParam,
                                                  String serviceClassName) throws IOException {
        List<String> template = getTemplate(GenerateTemplatePathConstants.COMMON_SERVICE_IMPL_PAGINATION_TEMPLATE);
        List<String> templateReplace = new ArrayList<>();
        for (String templateLine: template) {
            templateLine = templateReplaceService(entity,
                    dtoClassName,
                    voClassName,
                    dtoClassSuffix,
                    voClassSuffix,
                    entityParam,
                    templateLine);
            templateLine = templateReplaceServiceImpl(daoClassName,
                    serviceClassName,
                    daoClassSuffix,
                    entityParam,
                    templateLine);
            templateReplace.add(templateLine);
        }

        List<String> paginationFieldList = getTemplate(GenerateTemplatePathConstants.COMMON_SERVICE_IMPL_PAGINATION_FIELD_TEMPLATE);

        List<String> paginationFieldReplaceList = new ArrayList<>();
        int indexOf = 0;
        List<FieldInfo> tableFieldList = generateCodeDto.getTableFieldList();
        for(int i= 0; i < templateReplace.size(); i++){
            if(GenerateReplaceSymbolConstants.PAGINATION_FIELD.equals(templateReplace.get(i).trim())){
                indexOf = i;
                for (FieldInfo fieldInfo: tableFieldList) {
                    if(fieldInfo.getPaginationQueryCondition()){
                        String fieldName = fieldInfo.getJavaJavaHumpColumnName();
                        String getterFieldName = MysqlFieldConvertJavaHumpUtils.mysqlTableNameConvertJavaHump(fieldName);
                        for (String paginationField: paginationFieldList) {
                            if (paginationField.contains(GenerateReplaceSymbolConstants.ENTITY_DAO_PARAM)) {
                                paginationField = paginationField.replace(GenerateReplaceSymbolConstants.ENTITY_DAO_PARAM, dtoClassName);
                            }
                            if (paginationField.contains(GenerateReplaceSymbolConstants.GETTER_FIELD_NAME)) {
                                paginationField = paginationField.replace(GenerateReplaceSymbolConstants.GETTER_FIELD_NAME, getterFieldName);
                            }

                            if (paginationField.contains(GenerateReplaceSymbolConstants.FIELD_NAME)) {
                                paginationField = paginationField.replace(GenerateReplaceSymbolConstants.FIELD_NAME, "\""+fieldName+"\"");
                            }
                            paginationFieldReplaceList.add(paginationField);
                        }
                    }
                }
            }
        }
        templateReplace.addAll(indexOf + 1, paginationFieldReplaceList);
        templateReplace.remove(indexOf);
        return templateReplace;
    }


    /**
     * 将 List 逐行写入文件
     * @param writerFilePath 文件路径
     * @param sourceCode List 源文件
     * @throws IOException 异常
     */
    public void writerFile(String writerFilePath, List<String> sourceCode) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(writerFilePath));
        for (String line : sourceCode){
            writer.write(line + "\r\n");
        }
        writer.close();
    }

}
