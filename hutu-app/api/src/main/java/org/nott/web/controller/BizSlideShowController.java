package org.nott.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.BizSlideShowRequest;
import org.nott.vo.BizSlideShowItemVo;
import org.nott.vo.BizSlideShowVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.model.BizSlideShow;
import org.nott.service.api.BizSlideShowService;
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
@RequestMapping("/access/bizSlideShow")
    public class BizSlideShowController {

    @Resource
    private BizSlideShowService service;
}