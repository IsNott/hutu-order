package org.nott.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.BizItemSkuOptionRequest;
import org.nott.vo.BizItemSkuOptionVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.model.BizItemSkuOption;
import org.nott.service.api.BizItemSkuOptionService;
import javax.annotation.Resource;
/**
* SKU规格选项表前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "SKU规格选项表接口", tags = "SKU规格选项表")

@RestController
@RequestMapping("/access/bizItemSkuOption")
    public class BizItemSkuOptionController {

    @Resource
    private BizItemSkuOptionService service;
}