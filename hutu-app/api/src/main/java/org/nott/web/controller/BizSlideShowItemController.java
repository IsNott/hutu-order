package org.nott.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.BizSlideShowItemRequest;
import org.nott.vo.BizSlideShowItemVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.model.BizSlideShowItem;
import org.nott.service.api.BizSlideShowItemService;
import javax.annotation.Resource;
import java.util.List;

/**
* 前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "接口", tags = "")

@RestController
@RequestMapping("/access/bizSlideShowItem")
    public class BizSlideShowItemController {

    @Resource
    private BizSlideShowItemService service;

    @ApiOperation("按类型查询轮播图")
    @GetMapping("/getByType/{type}")
    public ResponseEntity<List<BizSlideShowItemVo>> getByType(@PathVariable("type") Integer type) {
        List<BizSlideShowItemVo> vos = service.getByType(type);
        return ResponseEntity.successData(vos);
    }
}