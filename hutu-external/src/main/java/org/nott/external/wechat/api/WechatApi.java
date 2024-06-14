package org.nott.external.wechat.api;

import com.alibaba.fastjson.JSONObject;
import org.nott.common.ResponseEntity;
import org.nott.common.exception.HutuBizException;
import org.nott.common.http.HttpClientUtil;
import org.nott.external.wechat.config.WechatAppConfig;
import org.nott.external.wechat.constant.WechatApiConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author Nott
 * @date 2024-6-14
 */

@RestController
@RequestMapping("/external/wechat/")
public class WechatApi {

    @Resource
    private WechatAppConfig wechatAppConfig;

    @GetMapping("oauth/{code}")
    public ResponseEntity<?> oauth(@PathVariable String code) {
        String url = WechatApiConstant.ACCESS_TOKEN_URL.replaceAll("\\{CODE}", code)
                .replaceAll("\\{APPID}", wechatAppConfig.getAppId())
                .replaceAll("\\{SECRET}", wechatAppConfig.getAppSecret());
        String respStr = HttpClientUtil.doGet(url);
        JSONObject responseJson = JSONObject.parseObject(respStr);
        if (!responseJson.containsKey("openId")) {
            throw new HutuBizException("访问微信失败，请稍后重试");
        }
        // TODO 微信登录逻辑
        return ResponseEntity.success();
    }

}
