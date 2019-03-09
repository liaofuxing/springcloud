package com.myspringcloud.common.utils;


import com.myspringcloud.common.enums.ResultStatusCodeEnums;
import com.myspringcloud.common.vo.ResultVO;

/**
 * 返回前端的统一类
 *  @author liaofuxing
 *  @date 2019/03/10 4:10
 */
public class ResultVOUtils {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(ResultStatusCodeEnums.OK.getCode());
        resultVO.setMsg("成功");
        return resultVO;
    }
}
