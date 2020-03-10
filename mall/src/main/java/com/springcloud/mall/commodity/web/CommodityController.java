package com.springcloud.mall.commodity.web;

import com.springcloud.common.enums.ExceptionEnums;
import com.springcloud.common.exception.ExceptionUtils;
import com.springcloud.common.upload.UploadUtils;
import com.springcloud.common.utils.ResultVOUtils;
import com.springcloud.common.vo.ResultVO;
import com.springcloud.mall.client.systemclient.SystemClient;
import com.springcloud.mall.commodity.entity.CommodityInfo;
import com.springcloud.mall.commodity.entity.SystemUser;
import com.springcloud.mall.commodity.service.CommodityService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author liaofuxing
 * @date 2019/03/08 2:40
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Resource
    private SystemClient systemClient;

    @Resource
    private CommodityService commodityService;

    /**
     * feign的实例方法
     *
     * @param id
     * @return
     */
    @GetMapping("/findSystemUserById")
    public ResultVO<SystemUser> findSystemUserById(@RequestParam String id){

        ResultVO<SystemUser> resultVO = systemClient.findSystemUserById(id);
        return resultVO;
    }

    /**
     *
     * 这是一个Feign示例方法
     *
     * Feign调用必须是全路径,而不是方法上的路径 (/systemUser/api/findSystemUserById)
     * @param systemUserInfo
     * @return
     */
    @PostMapping("/findSystemUser")
    public ResultVO<SystemUser> findSystemUser(@RequestBody SystemUser systemUserInfo) {
        try {
            ResultVO<SystemUser> resultVO = systemClient.findSystemUser(systemUserInfo);
            return resultVO;
        } catch (Exception e) {
            throw new ExceptionUtils(ExceptionEnums.SYSTEM_EXCEPTION);
        }
    }

    /**
     *  查询所有商品
     *  @return
     */
    @GetMapping("/findCommodityAll")
    public ResultVO<List<CommodityInfo>> findCommodityAll() {
        try {
            List<CommodityInfo> resultVO = commodityService.findCommodityAll();
            return ResultVOUtils.success(resultVO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionUtils(ExceptionEnums.SYSTEM_EXCEPTION);
        }
    }

    /**
     *  上传文件实例
     *  @return
     */
    @PostMapping("/upload")
    public ResultVO<String> findCommodityAll(@RequestParam("file") MultipartFile file) {
        UploadUtils uploadUtils = new UploadUtils();
        String result;
        try {
            result = uploadUtils.upload(file);
            return ResultVOUtils.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionUtils(ExceptionEnums.SYSTEM_EXCEPTION);
        }
    }

}