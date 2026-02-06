package org.nott.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.BizItemSkuSpecRequest;
import org.nott.vo.BizItemSkuSpecVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.model.BizItemSkuSpec;
import org.nott.service.api.BizItemSkuSpecService;
import javax.annotation.Resource;
/**
* 商品SKU规格表前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "商品SKU规格表接口", tags = "商品SKU规格表")

@RestController
@RequestMapping("/access/bizItemSkuSpec")
    public class BizItemSkuSpecController {

    @Resource
    private BizItemSkuSpecService service;
}