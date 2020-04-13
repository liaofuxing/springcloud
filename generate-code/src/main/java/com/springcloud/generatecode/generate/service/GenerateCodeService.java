package com.springcloud.generatecode.generate.service;

import com.springcloud.common.vo.SelectFormatVO;
import com.springcloud.generatecode.generate.dto.GenerateCodeDto;
import com.springcloud.generatecode.generate.entity.FieldInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface GenerateCodeService {

    List<FieldInfo> getTableField(String tableName) throws SQLException;

    void generateCode(GenerateCodeDto generateCodeDto, String token) throws SQLException, IOException;

//    List<String> generateEntityCode(GenerateCodeDto generateCodeDto) throws SQLException, IOException;

    List<SelectFormatVO> getMysqlTableSelect() throws SQLException;

//    Map<String, String> generateCodeFileDir(String packagePath, String filePathRoot);
}
