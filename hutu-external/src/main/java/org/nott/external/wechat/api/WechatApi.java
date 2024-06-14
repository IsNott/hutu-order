package org.nott.external.wechat.api;

import com.alibaba.fastjson.JSONObject;
import org.nott.common.ResponseEntity;
import org.nott.common.exception.HutuBizException;
import org.nott.common.http.HttpClientUtil;
import org.nott.external.wechat.config.WechatAppConfig;
import org.nott.external.wechat.constant.WechatApiConstant;
import org.nott.external.wechat.service.WechatService;
import org.nott.model.BizUser;
import org.nott.service.service.IBizUserService;
import org.nott.vo.UserLoginInfoVo;
import org.nott.vo.WechatUserInfoVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Nott
 * @date 2024-6-14
 */

@RestController
@RequestMapping("/external/wechat/")
public class WechatApi {

    @Resource
    private WechatAppConfig wechatAppConfig;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Resource
    private IBizUserService bizUserService;

    @Resource
    private WechatService wechatService;

    @GetMapping("oauth/{code}")
    public ResponseEntity<?> oauth(@PathVariable String code) {
        // 存在时登录，不存在时注册
        JSONObject respJson = wechatService.fetchAcTokenAndOpenIdByCode(code);
        String accessToken = respJson.getString("access_token");
        String openId = respJson.getString("openid");
        BizUser bizUser = bizUserService.getUserByOpenId(openId);
        WechatUserInfoVo wechatUserInfoVo = wechatService.fetchUserInfoByAcToken(openId, accessToken);
        UserLoginInfoVo vo = Objects.isNull(bizUser) ? bizUserService.registerUser(wechatUserInfoVo) : bizUserService.loginUser(wechatUserInfoVo);
        redisTemplate.opsForHash().put("WeChatLogInfo",vo.getLoginId() + "", accessToken);
        return ResponseEntity.successData(vo);
    }

}
