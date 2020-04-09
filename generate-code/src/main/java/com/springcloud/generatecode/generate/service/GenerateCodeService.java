package com.springcloud.generatecode.generate.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface GenerateCodeService {

    List<String> generateEntityCode(String tableName) throws SQLException, IOException;
}
