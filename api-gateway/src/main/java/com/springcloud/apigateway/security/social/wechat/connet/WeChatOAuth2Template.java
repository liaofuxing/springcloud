package com.springcloud.apigateway.security.social.wechat.connet;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;


/**
 * 微信OAuth2登录模板类
 *
 * @author liaofuxing
 * @date 2020/02/19 13:07
 * @E-mail liaofuxing@outlook.com
 */
public class WeChatOAuth2Template extends OAuth2Template {
    private String clientId;
    private String clientSecret;
    private String accessTokenUrl;
    private Logger logger = LoggerFactory.getLogger(WeChatOAuth2Template.class);

    /**
     * Constructs an OAuth2Template for a given set of connet credentials.
     * Assumes that the authorization URL is the same as the authentication URL.
     *
     * @param clientId       the connet ID
     * @param clientSecret   the connet secret
     * @param authorizeUrl   the base URL to redirect to when doing authorization code or implicit grant authorization
     * @param accessTokenUrl the URL at which an authorization code, refresh token, or user credentials may be exchanged for an access token.
     */
    public WeChatOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        //请求中添加client_id和client_secret参数
        setUseParametersForClientAuthentication(true);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /**
     * Creates the {@link RestTemplate} used to communicate with the provider's OAuth 2 API.
     * This implementation creates a RestTemplate with a minimal set of HTTP message converters ({@link //FormHttpMessageConverter} and {@link //MappingJackson2HttpMessageConverter}).
     * May be overridden to customize how the RestTemplate is created.
     * For example, if the provider returns data in some format other than JSON for form-encoded, you might override to register an appropriate message converter.
     *
     * @return a {@link RestTemplate} used to communicate with the provider's OAuth 2 API
     */
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        super.setUseParametersForClientAuthentication(true);

        return restTemplate;
    }

    /**
     * Posts the request for an access grant to the provider.
     * The default implementation uses RestTemplate to request the access token and expects a JSON response to be bound to a Map. The information in the Map will be used to create an {@link AccessGrant}.
     * Since the OAuth 2 specification indicates that an access token response should be in JSON format, there's often no need to override this method.
     * If all you need to do is capture provider-specific data in the response, you should override createAccessGrant() instead.
     * However, in the event of a provider whose access token response is non-JSON, you may need to override this method to request that the response be bound to something other than a Map.
     * For example, if the access token response is given as form-encoded, this method should be overridden to call RestTemplate.postForObject() asking for the response to be bound to a MultiValueMap (whose contents can then be used to create an AccessGrant).
     *
     * @param accessTokenUrl the URL of the provider's access token endpoint.
     * @param parameters     the parameters to post to the access token endpoint.
     * @return the access grant.
     */
    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {


        String respStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);

        logger.info("获取accessToken的字符串是:" + respStr);
        String[] items = respStr.split("&");

        String accessToken = StringUtils.substringAfterLast(items[0], "=");
        Long expiresIn = Long.parseLong(StringUtils.substringAfterLast(items[1], "="));
        String refreshToken = StringUtils.substringAfterLast(items[2], "=");
        AccessGrant accessGrant = new AccessGrant(accessToken, null, refreshToken, expiresIn);
        return accessGrant;
    }

    /**
     * 构建获取授权码的请求。也就是引导用户跳转到微信的地址。
     */
    public String buildAuthenticateUrl(OAuth2Parameters parameters) {
        String url = super.buildAuthenticateUrl(parameters);
        url = url + "&appid=" + clientId + "&scope=snsapi_login";
        return url;
    }

    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        return buildAuthenticateUrl(parameters);
    }


}
