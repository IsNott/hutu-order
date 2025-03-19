package org.nott.feign;

import org.nott.common.ResponseEntity;
import org.nott.vo.OssFileVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(value = "oss", url = "http://localhost:9901", path = "/oss")
public interface OssClient {

    @ResponseBody
    @PostMapping(value = "/upload/{bizId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<OssFileVo> upload(@RequestBody MultipartFile file, @PathVariable("bizId") Long bizId) throws Exception;

    @ResponseBody
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<OssFileVo> upload(@RequestBody MultipartFile file) throws Exception;

    @ResponseBody
    @PutMapping("/deleteByBizId/{bizId}")
    ResponseEntity<?> deleteByBizId(@PathVariable("bizId") Long bizId);

    @ResponseBody
    @PutMapping("/getByBizId/{bizId}")
    ResponseEntity<List<OssFileVo>> getByBizId(@PathVariable("bizId") Long bizId);

}
