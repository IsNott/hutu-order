package org.nott.oss.controller;

import io.swagger.annotations.Api;
import org.nott.common.ResponseEntity;
import org.nott.feign.OssClient;
import org.nott.service.oss.IOssFileService;
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
    private IOssFileService ossFileService;

    @Override
    public ResponseEntity<OssFileVo> upload(@RequestBody MultipartFile file, @PathVariable("bizId") Long bizId) throws Exception {
        return ResponseEntity.successData(ossFileService.upload(file, bizId));
    }

    @Override
    public ResponseEntity<OssFileVo> upload(MultipartFile file) throws Exception {
        return ResponseEntity.successData(ossFileService.upload(file, null));
    }

    @Override
    public ResponseEntity<?> deleteByBizId(@PathVariable("bizId") Long bizId) {
        ossFileService.deleteByBizId(bizId);
        return ResponseEntity.success();
    }

    @Override
    public ResponseEntity<List<OssFileVo>> getByBizId(@PathVariable("bizId") Long bizId) {
        return ResponseEntity.successData(ossFileService.getByBizId(bizId));
    }
}
