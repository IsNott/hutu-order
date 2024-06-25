package org.nott.external.wechat.api;

import com.alibaba.fastjson.JSONObject;
import org.nott.common.ResponseEntity;
import org.nott.common.redis.RedisUtils;
import org.nott.common.utils.HutuUtils;
import org.nott.external.wechat.service.WechatService;
import org.nott.model.BizUser;
import org.nott.service.service.IBizUserService;
import org.nott.vo.UserLoginInfoVo;
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
    private IBizUserService bizUserService;
    @Resource
    private RedisUtils redisUtils;

    @Resource
    private WechatService wechatService;

    @GetMapping("miniProgram/{code}")
    public ResponseEntity<?> oauth(@PathVariable String code) {
        // 存在时登录
        JSONObject respJson = wechatService.fetchMiniProgramLoginInfo(code);
        String openId = respJson.getString("openid");
        BizUser bizUser = bizUserService.getUserByOpenId(openId);
        boolean hasRegister = HutuUtils.isNotEmpty(bizUser);
        UserLoginInfoVo info = new UserLoginInfoVo();
        info.setAlreadyRegister(hasRegister);
        if (hasRegister) {
            HutuUtils.copyProperties(bizUser, info);
            String token = bizUserService.login(openId, bizUser);
            info.setToken(token);
        }
        redisUtils.set(code,openId);
        return ResponseEntity.successData(info);
    }

}
