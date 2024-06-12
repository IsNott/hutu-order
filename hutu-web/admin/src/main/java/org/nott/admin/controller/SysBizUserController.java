package org.nott.admin.controller;

import org.nott.common.ResponseEntity;
import org.nott.service.service.IBizUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Nott
 * @date 2024-6-12
 */

@RestController
@RequestMapping("/sys/bizUser/")
public class SysBizUserController {

    @Resource
    private IBizUserService bizUserService;

    @GetMapping
    public ResponseEntity<?> countBizUser(){
        return ResponseEntity.successData(bizUserService.count());
    }
}
