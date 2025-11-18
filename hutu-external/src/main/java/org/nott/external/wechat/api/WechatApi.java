package org.nott.external.wechat.api;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.redis.RedisUtils;
import org.nott.common.utils.HutuUtils;
import org.nott.dto.MiniProgramPhoneInfoDTO;
import org.nott.external.wechat.service.WechatService;
import org.nott.model.BizUser;
import org.nott.service.api.BizUserService;
import org.nott.vo.UserLoginInfoVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * 微信api
 * @author Nott
 * @date 2024-6-14
 */

@Api(tags = "微信api")
@RestController
@RequestMapping("/external/wechat/")
public class WechatApi {
    @Resource
    private BizUserService bizUserService;
    @Resource
    private RedisUtils redisUtils;

    @Resource
    private WechatService wechatService;

    @ApiOperation("微信小程序登录-授权码")
    @ApiImplicitParam(required = true)
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
        redisUtils.set(code,openId, TimeUnit.SECONDS.toSeconds(300L));
        return ResponseEntity.successData(info);
    }

    @ApiOperation("微信小程序登录-uniapp传递DTO")
    @PostMapping("miniProgramLogin")
    public ResponseEntity<?> miniProgramLogin(@Valid @RequestBody MiniProgramPhoneInfoDTO dto){
        UserLoginInfoVo info = wechatService.miniProgramLogin(dto);
        return ResponseEntity.successData(info);
    }

}
