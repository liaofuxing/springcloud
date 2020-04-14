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
     * entity Template 实体类模板路径
     */
    public static final String ENTITY_TEMPLATE_PATH = "classpath:templates/entity/entityTemplate.tmp";

    /**
     * entityDto Template
     */
    public static final String ENTITY_DTO_TEMPLATE_PATH = "classpath:templates/dto/entityDtoTemplate.tmp";

    /**
     * entityVO Template
     */
    public static final String ENTITY_VO_TEMPLATE_PATH = "classpath:templates/vo/entityVoTemplate.tmp";

    /**
     * dao Template dao模板路径(Repository模板路径)
     */
    public static final String DAO_TEMPLATE_PATH = "classpath:templates/dao/daoTemplate.tmp";

    /**
     * service Template service 模板路径
     */
    public static final String SERVICE_TEMPLATE_PATH = "classpath:templates/service/serviceTemplate.tmp";

    /**
     * service Template serviceImpl 模板路径
     */
    public static final String SERVICE_IMPL_TEMPLATE_PATH = "classpath:templates/service/impl/serviceImplTemplate.tmp";

    /**
     * Controller Template Controller 模板路径
     */
    public static final String CONTROLLER_TEMPLATE_PATH = "classpath:templates/web/controllerTemplate.tmp";

    /**
     * common Service paginationServiceImplTemplate
     */
    public static final String COMMON_SERVICE_PAGINATION_TEMPLATE = "classpath:templates/common/paginationServiceTemplate.tmp";

    /**
     * common ServiceImpl paginationServiceImplTemplate
     */
    public static final String COMMON_SERVICE_IMPL_PAGINATION_TEMPLATE = "classpath:templates/common/paginationServiceImplTemplate.tmp";

    /**
     * common serviceImpl paginationField paginationFieldTemplate
     */
    public static final String COMMON_SERVICE_IMPL_PAGINATION_FIELD_TEMPLATE = "classpath:templates/common/paginationServiceImplFieldTemplate.tmp";

    /**
     * common controller pagination paginationControllerTemplate
     */
    public static final String COMMON_CONTROLLER_PAGINATION_TEMPLATE = "classpath:templates/common/paginationControllerTemplate.tmp";

    /**
     * 生成时间
     */
    public static final String GENERATE_DATE = "${generateDate}";

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
     * jpaRepository
     */
    public static final String JPA_REPOSITORY = "${jpaRepository}";

    /**
     * entity
     */
    public static final String ENTITY = "${entity}";

    /**
     * entityDto
     */
    public static final String ENTITY_DTO = "${entityDto}";

    /**
     * entityVO
     */
    public static final String ENTITY_VO = "${entityVO}";

    /**
     * entityDao
     */
    public static final String ENTITY_DAO = "${entityDao}";

    /**
     * service
     */
    public static final String ENTITY_SERVICE = "${entityService}";

    /**
     * serviceInterface
     */
    public static final String ENTITY_SERVICE_INTERFACE = "${entityServiceInterface}";


    /**
     * entityParam
     */
    public static final String ENTITY_PARAM = "${entityParam}";

    /**
     * entityDaoParam
     */
    public static final String ENTITY_DAO_PARAM = "${entityDaoParam}";

    /**
     * entityDtoParam
     */
    public static final String ENTITY_DTO_PARAM = "${entityDtoParam}";

    /**
     * entityVOParam
     */
    public static final String ENTITY_VO_PARAM = "${entityVOParam}";

    /**
     * serviceParam
     */
    public static final String ENTITY_SERVICE_PARAM = "${entityServiceParam}";

    /**
     * fieldName
     */
    public static final String FIELD_NAME = "${fieldName}";

    /**
     * getterFieldName
     */
    public static final String GETTER_FIELD_NAME = "${getterFieldName}";

    /**
     * paginationField
     */
    public static final String PAGINATION_FIELD = " ${paginationField}";


    /**
     * importEntity
     */
    public static final String IMPORT_ENTITY = "${importEntity}";

    /**
     * importDto
     */
    public static final String IMPORT_DTO = "${importDto}";

    /**
     * importVO
     */
    public static final String IMPORT_VO = "${importVO}";


    /**
     * importService
     */
    public static final String IMPORT_SERVICE = "${importService}";

    /**
     * importDao
     */
    public static final String IMPORT_DAO = "${importDao}";


    /**
     * controllerRequestUrl
     */
    public static final String CONTROLLER_REQUEST_URL = "${controllerRequestUrl}";


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
     * java作用域修饰符 private
     */
    public static final String JAVA_SCOPE_IMPORT = "import";

    /**
     * 空格
     */
    public static final String SPACE_STRING = " ";

    /**
     * 制表符
     */
    public static final String TAB_STRING = "\t";

}
