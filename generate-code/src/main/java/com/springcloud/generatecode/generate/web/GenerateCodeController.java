package com.springcloud.generatecode.generate.web;

import com.springcloud.common.utils.ResultVOUtils;
import com.springcloud.common.vo.ResultVO;
import com.springcloud.common.vo.SelectFormatVO;
import com.springcloud.generatecode.generate.dto.GenerateCodeDto;
import com.springcloud.generatecode.generate.entity.FieldInfo;
import com.springcloud.generatecode.generate.service.GenerateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/generateCode")
public class GenerateCodeController {

    @Autowired
    private GenerateCodeService generateCodeService;


    @GetMapping("/getMysqlTableSelect")
    @ResponseBody
    public ResultVO<List<SelectFormatVO>> getMysqlTableSelect() throws SQLException {
        List<SelectFormatVO> mysqlTableSelect = generateCodeService.getMysqlTableSelect();
        return ResultVOUtils.success(mysqlTableSelect);
    }

    @GetMapping("/getTableField")
    @ResponseBody
    public ResultVO<List<FieldInfo>> getTableField(String tableName) throws SQLException {
        List<FieldInfo> tableField = generateCodeService.getTableField(tableName);
        return ResultVOUtils.success(tableField);
    }

    @PostMapping("/generateCode")
    @ResponseBody
    public void generateCode(@RequestBody GenerateCodeDto generateCodeDto, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String token = request.getHeader("token");
        generateCodeService.generateCode(generateCodeDto, token);
//        String javaHumpClassName = MysqlFieldConvertJavaHumpUtils.mysqlTableNameConvertJavaHump(generateCodeDto.getTableName());
//        StringBuilder outputBuilder = new StringBuilder();
//        for (String code: codeList) {
//            outputBuilder.append(code);
//            outputBuilder.append("\n");
//        }
//        try {
//            response.setHeader("Content-Disposition", "attachment; filename="+ javaHumpClassName +".java");
//            response.getOutputStream().write(outputBuilder.toString().getBytes());
//            response.flushBuffer();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
    }
}
