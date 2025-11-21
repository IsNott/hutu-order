package org.nott.admin.controller.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class CommonController<T, Service extends ServiceImpl> {

    @Autowired
    private T service;
    
}
