package org.nott.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.dto.ItemSearchDTO;
import org.nott.model.BizItem;
import org.nott.service.service.IBizItemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
@Api(tags = "商品信息")
@RestController
@RequestMapping("/bizItem")
public class BizItemController {

    @Resource
    private IBizItemService bizItemService;

    @ApiOperation(value = "搜索商品")
    @PostMapping("search")
    public ResponseEntity<?> searchItem(@RequestBody @Valid ItemSearchDTO dto){
        List<BizItem> itemList = bizItemService.searchItemByName(dto.getKeyWord());
        return ResponseEntity.successData(itemList);
    }

    @ApiOperation(value = "热搜tag")
    @GetMapping("searchTags")
    public ResponseEntity<?> searchTags(){
        Set<String> tags = bizItemService.getSearchItemTags();
        return ResponseEntity.successData(tags);
    }
}
