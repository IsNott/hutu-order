package org.nott.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.dto.PayWayQueryDTO;
import org.nott.service.api.BizPayWayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
@Api(tags = "支付方式")
@RestController
@RequestMapping("/bizPayWay")
public class BizPayWayController {

    @Resource
    private BizPayWayService bizPayWayService;

    @ApiOperation("查询支付方式列表")
    @PostMapping("list")
    public ResponseEntity<?> listPayWay(@RequestBody PayWayQueryDTO dto){
        return ResponseEntity.successData(bizPayWayService.listPayWay(dto));
    }
}
