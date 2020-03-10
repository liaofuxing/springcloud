package com.springcloud.apigateway.security.social.wechat.connet;

import com.springcloud.apigateway.security.social.wechat.api.WeChat;
import com.springcloud.apigateway.security.social.wechat.api.WeChatUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author liaofuxing
 * @date 2019/03/14 19:28
 * @E-mail liaofuxing@outlook.com
 */
public class WeChatAdapter implements ApiAdapter<WeChat> {
    private String openid;

    @Override
    public boolean test(WeChat api) {
        return true;
    }

    public WeChatAdapter() {
    }

    public WeChatAdapter(String openid) {
        this.openid = openid;
    }

    @Override
    public void setConnectionValues(WeChat weChat, ConnectionValues connectionValues) {
        WeChatUserInfo userInfo = weChat.getUserInfo();
        connectionValues.setDisplayName(userInfo.getNickname());
        connectionValues.setImageUrl(userInfo.getHeadimgurl());
        connectionValues.setProfileUrl(null);
        connectionValues.setProviderUserId(userInfo.getOpenid());
    }

    @Override
    public UserProfile fetchUserProfile(WeChat weChat) {
        return null;
    }

    @Override
    public void updateStatus(WeChat weChat, String s) {

    }


}
