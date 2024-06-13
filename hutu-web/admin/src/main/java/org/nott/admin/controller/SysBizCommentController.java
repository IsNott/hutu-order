package org.nott.admin.controller;

import org.nott.common.ResponseEntity;
import org.nott.service.service.IBizCommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Nott
 * @date 2024-6-13
 */

@RestController
@RequestMapping("/sys/bizComment/")
public class SysBizCommentController {

    @Resource
    private IBizCommentService bizCommentService;

    @GetMapping("count")
    public ResponseEntity<?> count(){
        return ResponseEntity.successData(bizCommentService.count());
    }
}
