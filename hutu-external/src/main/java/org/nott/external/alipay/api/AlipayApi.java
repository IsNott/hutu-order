package org.nott.external.alipay.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.external.alipay.service.AlipayService;
import org.nott.model.BizUser;
import org.nott.service.service.IBizUserService;
import org.nott.vo.AlipayUserInfo;
import org.nott.vo.UserLoginInfoVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 支付宝api
 * @author Nott
 * @date 2024-6-14
 */
@Api(tags = "支付宝api")
@RestController
@RequestMapping("/external/alipay/")
public class AlipayApi {

    @Resource
    private AlipayService alipayService;
    @Resource
    private IBizUserService bizUserService;

    @ApiOperation("支付宝oauth登录")
    @GetMapping("oauth/{code}")
    public ResponseEntity<?> oauth(@PathVariable String code) {
        String accessToken = alipayService.getAccessTokenByCode(code);
        AlipayUserInfo alipayUserInfo = alipayService.fetchUserInfoByAcToken(accessToken);
        BizUser bizUser = bizUserService.getUserByOpenId(alipayUserInfo.getOpenId());
        UserLoginInfoVo vo = Objects.isNull(bizUser) ? bizUserService.registerUser(alipayUserInfo) : bizUserService.loginUser(alipayUserInfo);
//        redisTemplate.opsForHash().put("AlipayLogInfo",vo.getLoginId() + "", accessToken);
        return ResponseEntity.successData(vo);
    }
}
