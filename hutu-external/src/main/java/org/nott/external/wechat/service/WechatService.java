package org.nott.external.wechat.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.nott.common.exception.HutuBizException;
import org.nott.common.http.HttpClientUtil;
import org.nott.external.wechat.config.WechatAppConfig;
import org.nott.external.wechat.constant.WechatApiConstant;
import org.nott.vo.WechatUserInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Nott
 * @date 2024-6-14
 */

@Service
public class WechatService {

    @Resource
    private WechatAppConfig wechatAppConfig;

    public WechatUserInfoVo fetchUserInfoByAcToken(String openId, String accessToken) {
        String url = WechatApiConstant.GET_USERINFO_URL
                .replaceAll("\\{OPENID}", openId)
                .replaceAll("\\{ACCESS_TOKEN}", accessToken);
        String respStr = HttpClientUtil.doGet(url);
        JSONObject response = JSON.parseObject(respStr);
        if (!response.containsKey("openid")) {
            throw new HutuBizException("获取微信用户信息失败，请重试");
        }
        WechatUserInfoVo wechatUserInfoVo = JSON.parseObject(respStr, WechatUserInfoVo.class);
        wechatUserInfoVo.setOpenId(wechatUserInfoVo.getOpenid());
        wechatUserInfoVo.setNickName(wechatUserInfoVo.getNickname());
        wechatUserInfoVo.setAvatarUrl(wechatUserInfoVo.getHeadimgurl());
        return wechatUserInfoVo;
    }

    public JSONObject fetchAcTokenAndOpenIdByCode(String code){
        String url = WechatApiConstant.ACCESS_TOKEN_URL.replaceAll("\\{CODE}", code)
                .replaceAll("\\{APPID}", wechatAppConfig.getAppId())
                .replaceAll("\\{SECRET}", wechatAppConfig.getAppSecret());
        String respStr = HttpClientUtil.doGet(url);
        JSONObject responseJson = JSONObject.parseObject(respStr);
        if (!responseJson.containsKey("openid")) {
            throw new HutuBizException("访问微信失败，请稍后重试");
        }
        return responseJson;
    }
}
