package org.nott.oss.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.feign.OssClient;
import org.nott.service.oss.OssFileService;
import org.nott.vo.OssFileVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Nott
 * @date 2025-3-18
 */

@Api("Oss文件服务")
@RestController
@RequestMapping("/oss")
public class OssController implements OssClient {

    @Resource
    private OssFileService ossFileService;

    @Override
    @ApiOperation("上传文件[附带业务ID]")
    public ResponseEntity<OssFileVo> upload(@RequestBody MultipartFile file, @PathVariable("bizId") Long bizId) throws Exception {
        return ResponseEntity.successData(ossFileService.upload(file, bizId));
    }

    @Override
    @ApiOperation("上传文件")
    public ResponseEntity<OssFileVo> upload(MultipartFile file) throws Exception {
        return ResponseEntity.successData(ossFileService.upload(file, null));
    }

    @Override
    @ApiOperation("删除文件[根据业务ID]")
    public ResponseEntity<?> deleteByBizId(@PathVariable("bizId") Long bizId) {
        ossFileService.deleteByBizId(bizId);
        return ResponseEntity.success();
    }

    @Override
    @ApiOperation("删除文件[根据ID]")
    public ResponseEntity<?> deleteById(Long id) {
        ossFileService.deleteById(id);
        return ResponseEntity.success();
    }

    @Override
    @ApiOperation("获取文件[根据业务ID]")
    public ResponseEntity<List<OssFileVo>> getByBizId(@PathVariable("bizId") Long bizId) {
        return ResponseEntity.successData(ossFileService.getByBizId(bizId));
    }

    @Override
    @ApiOperation("获取文件[根据业务ID列表]")
    public ResponseEntity<List<OssFileVo>> getByBizId(List<Long> bizId) {
        return ResponseEntity.successData(ossFileService.getByBizId(bizId));
    }
}
