package com.myspringcloud.apigateway.security.social.wechat.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @author liaofuxing
 * @date 2019/03/14 18:21
 * @E-mail liaofuxing@outlook.com
 */

public class WeChatImpl extends AbstractOAuth2ApiBinding implements WeChat {

    private Logger logger = LoggerFactory.getLogger(WeChatImpl.class);

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%S";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%S&openid=%S";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();


    public WeChatImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        String url = String.format(URL_GET_OPENID, accessToken);
        String resutl =  getRestTemplate().getForObject(url, String.class);

        this.appId = appId;
        this.openId = StringUtils.substringBetween(resutl,"\"openid\":\"","\"}");

        logger.info("取到的openId: {}" ,appId);
        logger.info("取到的openId: {}" ,openId);

    }

    /**
     * 已近拿到token,用token取qq信息
     * @return
     */
    @Override
    public WeChatUserInfo getUserInfo() {

        String url = String.format(URL_GET_USERINFO, appId, openId);
        String resutl = getRestTemplate().getForObject(url, String.class);

        logger.info("用户信息是: {}", resutl);

        WeChatUserInfo weChatUserInfo = null;


        try {
            weChatUserInfo = objectMapper.readValue(resutl, WeChatUserInfo.class);
            weChatUserInfo.setOpenId(openId);
            return weChatUserInfo;
        } catch (IOException e) {
           throw  new RuntimeException("获取用户信息失败", e);
        }
    }
}
