package com.springcloud.common.utils;


import com.springcloud.common.enums.ResultStatusCodeEnums;
import com.springcloud.common.enums.StatusCodeEnums;
import com.springcloud.common.vo.ResultVO;

/**
 * 返回前端的统一工具类
 *
 * @author liaofuxing
 * @date 2019/03/10 4:10
 */
public class ResultVOUtils {

    // 普通请求
    /**
     * 请求成功 20001
     * @param object object
     *
     * @return <T> ResultVO<T>
     */
    public static <T> ResultVO<T> success(T object) {
        return new ResultVO<>(ResultStatusCodeEnums.OK.getCode(), ResultStatusCodeEnums.OK.getMessage(), object);
    }

    /**
     * 请求失败 50000
     * @param object object
     *
     * @return <T> ResultVO<T>
     */
    public static <T> ResultVO<T> error(T object) {
        return new ResultVO<>(ResultStatusCodeEnums.ERROR.getCode(), ResultStatusCodeEnums.ERROR.getMessage(), object);
    }


    // 登录请求
    /**
     * 登录成功 20000
     * @param object object
     *
     * @return <T> ResultVO<T>
     */
    public static <T> ResultVO<T> login_success(T object) {
        return new ResultVO<>(StatusCodeEnums.LOGIN_SUCCESS.getCode(), StatusCodeEnums.LOGIN_SUCCESS.getName(), object);
    }

    /**
     * 用户名或密码错误 50001
     * @param object object
     *
     * @return <T> ResultVO<T>
     */
    public static <T> ResultVO<T> login_failure(T object) {
        return new ResultVO<>(StatusCodeEnums.LOGIN_FAILURE.getCode(), StatusCodeEnums.LOGIN_FAILURE.getName(), object);
    }

    /**
     * 注销成功 50002
     * @param object object
     *
     * @return <T> ResultVO<T>
     */
    public static <T> ResultVO<T> logout_success(T object) {
        return new ResultVO<>(StatusCodeEnums.LOGOUT_SUCCESS.getCode(), StatusCodeEnums.LOGOUT_SUCCESS.getName(), object);
    }

    /**
     * 用户未登录 50003
     * @param object object
     *
     * @return <T> ResultVO<T>
     */
    public static <T> ResultVO<T> credentials_expired(T object) {
        return new ResultVO<>(StatusCodeEnums.CREDENTIALS_EXPIRED.getCode(), StatusCodeEnums.CREDENTIALS_EXPIRED.getName(), object);
    }

    /**
     * 未授权  50004
     * @param object object
     *
     * @return <T> ResultVO<T>
     */
    public static <T> ResultVO<T> unauthorized(T object) {
        return  new ResultVO<>(StatusCodeEnums.UNAUTHORIZED.getCode(), StatusCodeEnums.UNAUTHORIZED.getName(), object);
    }

    //接口请求

}
