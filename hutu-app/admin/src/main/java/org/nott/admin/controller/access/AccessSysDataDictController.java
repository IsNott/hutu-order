package org.nott.admin.controller.access;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.nott.service.admin.SysDataDictService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author tasteTheWorld
 * @date 12月
 * version 1.0.0
 */

@RestController
@RequestMapping("/access/sysDataDict")
@Api(value = "AccessSysDataDictController", tags = "免鉴权数据字典控制器")
public class AccessSysDataDictController {

    @Resource
    private SysDataDictService service;

}
