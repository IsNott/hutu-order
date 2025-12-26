package org.nott.oss.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.dto.OssFileDTO;
import org.nott.feign.OssClient;
import org.nott.request.OssFileRequest;
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
    public ResponseEntity<OssFileVo> upload(@RequestParam MultipartFile file, @PathVariable("bizId") Long bizId) throws Exception {
        return ResponseEntity.successData(ossFileService.upload(file, bizId));
    }

    @Override
    @ApiOperation("上传文件")
    public ResponseEntity<OssFileVo> upload(@RequestParam MultipartFile file) throws Exception {
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
    public ResponseEntity<List<OssFileVo>> getByBizId(@RequestBody List<Long> bizId) {
        return ResponseEntity.successData(ossFileService.getByBizId(bizId));
    }

    @Override
    @ApiOperation("设置业务ID")
    public ResponseEntity<Void> relateOssFile(@RequestBody List<OssFileRequest> fileRequests,@PathVariable Long bizId) {
        ossFileService.relateFile(HutuUtils.transRequestsToDTOs(fileRequests, OssFileDTO.class), bizId);
        return ResponseEntity.success();
    }
}
