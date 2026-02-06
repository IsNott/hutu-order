package org.nott.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.request.BizProductRequest;
import org.nott.vo.BizProductVo;
import org.nott.vo.SysProductVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.service.api.BizProductService;
import javax.annotation.Resource;
/**
* 商品表前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "商品表接口", tags = "商品表")

@RestController
@RequestMapping("/access/bizProduct")
    public class BizProductController {

    @Resource
    private BizProductService service;

    @PostMapping("query/{page}/{size}")
    @ApiOperation("分页查询商品表")
    public ResponseEntity<IPage<BizProductVo>> query(@RequestBody BizProductRequest request, @PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        IPage<BizProductVo> voPage = service.queryPage(request.toDTO(), page ,size);
        return ResponseEntity.successData(voPage);
    }

    @ApiOperation("详情")
    @GetMapping("/details/{id}")
    public ResponseEntity<BizProductVo> details(@PathVariable("id") Long id) {
        return ResponseEntity.successData(service.details(id));
    }
}