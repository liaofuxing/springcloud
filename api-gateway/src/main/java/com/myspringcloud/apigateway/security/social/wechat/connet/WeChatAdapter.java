package com.myspringcloud.apigateway.security.social.wechat.connet;

import com.myspringcloud.apigateway.security.social.wechat.api.WeChat;
import com.myspringcloud.apigateway.security.social.wechat.api.WeChatUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author liaofuxing
 * @date 2019/03/14 19:28
 * @E-mail liaofuxing@outlook.com
 */
public class WeChatAdapter implements ApiAdapter<WeChat> {
    @Override
    public boolean test(WeChat api) {
        return true;
    }

    @Override
    public void setConnectionValues(WeChat weChat, ConnectionValues connectionValues) {
        WeChatUserInfo userInfo = weChat.getUserInfo();
        connectionValues.setDisplayName(userInfo.getNickname());
        connectionValues.setImageUrl(userInfo.getFigureurl_qq_1());
        connectionValues.setProfileUrl(null);
        connectionValues.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(WeChat weChat) {
        return null;
    }

    @Override
    public void updateStatus(WeChat weChat, String s) {

    }

}
