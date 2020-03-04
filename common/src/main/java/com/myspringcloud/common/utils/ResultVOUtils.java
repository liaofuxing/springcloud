package com.myspringcloud.common.utils;


import com.myspringcloud.common.enums.ResultStatusCodeEnums;
import com.myspringcloud.common.enums.StatusCodeEnum;
import com.myspringcloud.common.vo.ResultVO;

/**
 * 返回前端的统一工具类
 *
 * @author liaofuxing
 * @date 2019/03/10 4:10
 */
public class ResultVOUtils {
    /**
     * 普通请求
     *
     * @param object
     * @return
     */
    /**
     * 请求成功 20001
     * @param object
     * @return
     */
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO(ResultStatusCodeEnums.OK.getCode(), ResultStatusCodeEnums.OK.getMessage(), object);
        return resultVO;
    }

    /**
     * 请求失败 50000
     * @param object
     * @return
     */
    public static ResultVO error(Object object) {
        ResultVO resultVO = new ResultVO(ResultStatusCodeEnums.ERROR.getCode(), ResultStatusCodeEnums.ERROR.getMessage(), object);
        return resultVO;
    }

    /**
     * 登录请求
     *
     */
    /**
     * 登录成功 20000
     * @param object
     * @return
     */
    public static ResultVO login_success(Object object) {
        ResultVO resultVO = new ResultVO(StatusCodeEnum.LOGIN_SUCCESS.getCode(), StatusCodeEnum.LOGIN_SUCCESS.getName(), object);
        return resultVO;
    }

    /**
     * 用户名或密码错误 50001
     * @param object
     * @return
     */
    public static ResultVO login_failure(Object object) {
        ResultVO resultVO = new ResultVO(StatusCodeEnum.LOGIN_FAILURE.getCode(), StatusCodeEnum.LOGIN_FAILURE.getName(), object);
        return resultVO;
    }

    /**
     * 注销成功 50002
     * @param object
     * @return
     */
    public static ResultVO logout_success(Object object) {
        ResultVO resultVO = new ResultVO(StatusCodeEnum.LOGOUT_SUCCESS.getCode(), StatusCodeEnum.LOGOUT_SUCCESS.getName(), object);
        return resultVO;
    }

    /**
     * 用户未登录 50003
     * @param object
     * @return
     */
    public static ResultVO not_login(Object object) {
        ResultVO resultVO = new ResultVO(StatusCodeEnum.NOT_LOGIN.getCode(), StatusCodeEnum.NOT_LOGIN.getName(), object);
        return resultVO;
    }

    /**
     * 未授权  50004
     * @param object
     * @return
     */
    public static ResultVO unauthorized(Object object) {
        ResultVO resultVO = new ResultVO(StatusCodeEnum.UNAUTHORIZED.getCode(), StatusCodeEnum.UNAUTHORIZED.getName(), object);
        return resultVO;
    }

}
