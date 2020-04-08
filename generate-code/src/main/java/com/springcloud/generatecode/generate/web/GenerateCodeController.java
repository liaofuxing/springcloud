package com.springcloud.generatecode.generate.web;

import com.springcloud.generatecode.generate.entity.FieldInfo;
import com.springcloud.generatecode.utils.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/generateCode")
public class GenerateCodeController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/test")
    @ResponseBody
    public void getRouters() throws SQLException {
        DBUtils dbUtils = new DBUtils(dataSource);
        List<FieldInfo> system_user = dbUtils.getDBFields("system_user");
        List<String> tables = dbUtils.getTables();
        System.out.println(system_user.toString());
        System.out.println(tables.toString());
    }
}
