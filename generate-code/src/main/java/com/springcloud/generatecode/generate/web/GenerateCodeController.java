package com.springcloud.generatecode.generate.web;

import com.springcloud.common.utils.ResultVOUtils;
import com.springcloud.common.vo.ResultVO;
import com.springcloud.common.vo.SelectFormatVO;
import com.springcloud.generatecode.common.properties.GenerateProperties;
import com.springcloud.generatecode.generate.dto.GenerateCodeDto;
import com.springcloud.generatecode.generate.entity.FieldInfo;
import com.springcloud.generatecode.generate.service.GenerateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/generateCode")
public class GenerateCodeController {

    @Autowired
    private GenerateCodeService generateCodeService;

    @Autowired
    private GenerateProperties generateProperties;


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
        File file = new File(generateProperties.getGenerateFileRootPath() + token + "zip"+ "/"+generateCodeDto.getTableName()+".zip");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        if (file.exists()) {
            // 设置强制下载不打开  
            response.setHeader("Content-Disposition", "attachment; filename="+ generateCodeDto.getTableName()+".zip");
            byte[] buffer = new byte[1024];

            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    response.getOutputStream().write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                response.flushBuffer();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }finally {
                assert fis != null;
                fis.close();
                assert bis != null;
                bis.close();
            }
        }
    }
}
