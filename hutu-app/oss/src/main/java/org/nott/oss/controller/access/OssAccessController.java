package org.nott.oss.controller.access;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.feign.OssAccessClient;
import org.nott.service.oss.OssFileService;
import org.nott.vo.OssFileVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author tasteTheWorld
 * @date 1月
 * version 1.0.0
 */

@Api("Oss开放访问接口")
@RestController
@RequestMapping("/access/oss")
public class OssAccessController implements OssAccessClient {

    @Resource
    private OssFileService ossFileService;

    @Override
    @ApiOperation("获取文件[根据业务ID]")
    public ResponseEntity<List<OssFileVo>> getByBizId(@PathVariable("bizId") Long bizId) {
        return ResponseEntity.successData(ossFileService.getByBizId(bizId));
    }

    @Override
    @ApiOperation("获取文件[根据业务ID列表]")
    public ResponseEntity<List<OssFileVo>> getByBizId(@RequestBody List<Long> bizId) {
        return ResponseEntity.successData(ossFileService.getByBizId(bizId));
    }
}
