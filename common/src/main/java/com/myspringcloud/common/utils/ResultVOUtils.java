package com.myspringcloud.common.utils;


import com.myspringcloud.common.enums.ResultStatusCodeEnums;
import com.myspringcloud.common.vo.ResultVO;

/**
 * 返回前端的统一工具类
 *  @author liaofuxing
 *  @date 2019/03/10 4:10
 */
public class ResultVOUtils {
    /**
     * 普通请求
     * @param object
     * @return
     */
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO(ResultStatusCodeEnums.OK.getCode(),ResultStatusCodeEnums.OK.getMessage(),object);
        return resultVO;
    }

    public static ResultVO error() {
        ResultVO resultVO = new ResultVO(ResultStatusCodeEnums.ERROR.getCode(),ResultStatusCodeEnums.ERROR.getMessage(),null);
        return resultVO;
    }

    /**
     * 登录请求
     *  TODO
     */
}
