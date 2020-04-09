package com.springcloud.generatecode.generate.web;

import com.springcloud.generatecode.generate.service.GenerateCodeService;
import com.springcloud.generatecode.utils.MysqlFieldConvertJavaHumpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/generateCode")
public class GenerateCodeController {

    @Autowired
    private GenerateCodeService generateCodeService;


    @GetMapping("/test")
    @ResponseBody
    public void getRouters(HttpServletResponse response) throws SQLException, IOException {
        List<String> systemUser = generateCodeService.generateEntityCode("system_user");
        String javaHumpClassName = MysqlFieldConvertJavaHumpUtils.mysqlTableNameConvertJavaHump("system_user");
        StringBuilder outputBuilder = new StringBuilder();
        for (String s: systemUser) {
            outputBuilder.append(s);
            outputBuilder.append("\n");
        }
        try {
            response.setHeader("Content-Disposition", "attachment; filename="+ javaHumpClassName +".java");
            response.getOutputStream().write(outputBuilder.toString().getBytes());
            response.flushBuffer();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
