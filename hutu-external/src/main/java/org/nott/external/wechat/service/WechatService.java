package org.nott.external.wechat.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.nott.common.exception.HutuBizException;
import org.nott.common.http.HttpClientUtil;
import org.nott.common.redis.RedisUtils;
import org.nott.common.utils.HutuUtils;
import org.nott.dto.MiniProgramPhoneInfoDTO;
import org.nott.dto.UserLoginDTO;
import org.nott.external.wechat.config.WechatAppConfig;
import org.nott.external.wechat.constant.WechatApiConstant;
import org.nott.service.api.IBizUserService;
import org.nott.vo.UserLoginInfoVo;
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

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private IBizUserService bizUserService;

    public String generateStableToken() {
        Object hget = redisUtils.hget(WechatApiConstant.redisKey.API_NAME, WechatApiConstant.redisKey.STABLE_TOKEN);
        if (HutuUtils.isNotEmpty(hget)) {
            return (String) hget;
        }

        String url = WechatApiConstant.STABLE_TOKEN;

        JSONObject param = new JSONObject();
        param.put("appid", wechatAppConfig.getAppId());
        param.put("secret", wechatAppConfig.getAppSecret());
        param.put("grant_type", "client_credential");
        JSONObject responseJson = HttpClientUtil.doPost(url,param);
        if (!responseJson.containsKey("access_token")) {
            throw new HutuBizException("访问微信失败，请稍后重试");
        }
        String accessToken = responseJson.getString("access_token");
        Integer expiresIn = responseJson.getInteger("expires_in");
        redisUtils.hset(WechatApiConstant.redisKey.API_NAME, WechatApiConstant.redisKey.STABLE_TOKEN,accessToken,expiresIn);
        return accessToken;
    }

    public String getPhoneByCode(String code,String accessToken){
        String url = WechatApiConstant.PHONE_NUM_URL
                .replaceAll("\\{ACCESS_TOKEN}", accessToken);

        JSONObject param = new JSONObject();
        param.put("code", code);
        JSONObject respJson = HttpClientUtil.doPost(url, param);
        if(!"ok".equals(respJson.getString("errmsg"))){
            throw new HutuBizException("获取微信用户手机信息失败，请重试");
        }
        JSONObject phoneInfo = respJson.getJSONObject("phone_info");
        return phoneInfo.getString("purePhoneNumber");
    }

    public JSONObject fetchMiniProgramLoginInfo(String code){
        String url = WechatApiConstant.MINI_PROGRAM_LOGIN.replaceAll("\\{CODE}", code)
                .replaceAll("\\{APPID}", wechatAppConfig.getAppId())
                .replaceAll("\\{SECRET}", wechatAppConfig.getAppSecret());
        String respStr = HttpClientUtil.doGet(url);
        JSONObject responseJson = JSONObject.parseObject(respStr);
        if (!responseJson.containsKey("openid")) {
            throw new HutuBizException("访问微信失败，请稍后重试");
        }
        return responseJson;
    }


    public UserLoginInfoVo miniProgramLogin(MiniProgramPhoneInfoDTO dto) {
        String code = dto.getCode();
        String stableToken = generateStableToken();
        String phone = getPhoneByCode(code, stableToken);

        UserLoginDTO loginDTO = new UserLoginDTO();
        loginDTO.setPhone(phone);
        return bizUserService.loginByPhone(loginDTO);
    }
}
