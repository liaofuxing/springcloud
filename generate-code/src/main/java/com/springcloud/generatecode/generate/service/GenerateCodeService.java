package com.springcloud.generatecode.generate.service;

import com.springcloud.generatecode.generate.dto.GenerateCodeDto;
import com.springcloud.generatecode.generate.entity.FieldInfo;
import com.springcloud.common.vo.SelectFormatVO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface GenerateCodeService {

    List<FieldInfo> getTableField(String tableName) throws SQLException;

    List<String> generateCode(GenerateCodeDto generateCodeDto) throws SQLException, IOException;

    List<String> generateEntityCode(GenerateCodeDto generateCodeDto) throws SQLException, IOException;

    List<SelectFormatVO> getMysqlTableSelect() throws SQLException;
}
