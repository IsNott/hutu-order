package org.nott.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.service.api.BizBusinessConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nott
 * @since 2025-04-15
 */
@Api(tags = "业务配置")
@RestController
@RequestMapping("/bizConfig")
public class BizBusinessConfigController {

    @Resource
    private BizBusinessConfigService bizBusinessConfigService;

    @ApiOperation("根据业务key查询业务配置")
    @RequestMapping("/getByKey/{key}")
    public ResponseEntity<?> getByKey(@PathVariable(name = "key") String key){
        return ResponseEntity.successData(bizBusinessConfigService.getByBizKey(key));
    }

    @ApiOperation("查询Redis中的业务配置")
    @GetMapping("/getRedisConfig")
    public ResponseEntity<?> getRedisConfig() {
        return ResponseEntity.successData(bizBusinessConfigService.getRedisConfig());
    }


}
