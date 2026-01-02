package org.nott.feign;

import org.nott.common.ResponseEntity;
import org.nott.request.OssFileRequest;
import org.nott.vo.OssFileVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(value = "ossAccess", url = "http://localhost:9901", path = "/access/oss")
public interface OssAccessClient {

    @ResponseBody
    @GetMapping("/getByBizId/{bizId}")
    ResponseEntity<List<OssFileVo>> getByBizId(@PathVariable("bizId") Long bizId);

    @ResponseBody
    @PostMapping("/getByBizId")
    ResponseEntity<List<OssFileVo>> getByBizId(@RequestBody List<Long> bizId);

}
