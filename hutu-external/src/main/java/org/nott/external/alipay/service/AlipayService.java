package org.nott.external.alipay.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import org.nott.common.exception.HutuBizException;
import org.nott.external.alipay.config.AlipayConfig;
import org.nott.vo.AlipayBaseUserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Nott
 * @date 2024-6-14
 */

@Service
public class AlipayService {

    @Resource
    private AlipayConfig alipayConfig;

    private AlipayClient alipayClient;

    public AlipayClient getAlipayClient() {
        if(this.alipayClient == null){
            try {
                this.alipayClient = new DefaultAlipayClient(getAlipayConfig());
            } catch (AlipayApiException e) {
                throw new HutuBizException("获取AlipayClient信息失败");
            }
        }
        return alipayClient;
    }

    private com.alipay.api.AlipayConfig getAlipayConfig() {
        String privateKey = alipayConfig.getPrivateKey();
        String alipayPublicKey = alipayConfig.getPublicKey();
        com.alipay.api.AlipayConfig alipayConfig = new com.alipay.api.AlipayConfig();
        alipayConfig.setServerUrl("https://openapi.alipay.com/gateway.do");
        alipayConfig.setAppId(alipayConfig.getAppId());
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF-8");
        alipayConfig.setSignType("RSA2");
        return alipayConfig;
    }

    public String getAccessTokenByCode(String code){
        AlipayClient client = getAlipayClient();
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(code);
        AlipaySystemOauthTokenResponse response;
        try {
             response = client.execute(request);
            if(!response.isSuccess()){
                throw new HutuBizException("获取支付宝AcToken失败");
            }
        } catch (AlipayApiException e) {
            throw new HutuBizException(e.getMessage(),e);
        }
        return response.getAccessToken();
    }

    public AlipayBaseUserInfo fetchUserInfoByAcToken(String accessToken) {
        AlipayClient alipayClient = getAlipayClient();
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        AlipayBaseUserInfo alipayUserInfo = new AlipayBaseUserInfo();
        try {
            AlipayUserInfoShareResponse response = alipayClient.execute(request,accessToken);
            BeanUtils.copyProperties(response,alipayUserInfo);
        } catch (AlipayApiException e) {
            throw new HutuBizException("获取支付宝用户信息失败");
        }

        return alipayUserInfo;
    }
}
