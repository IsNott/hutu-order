package org.nott.admin.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.enums.BaseField;
import org.nott.enums.YesOrNoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;

/**
 * @author Nott
 * @date 2025-6-30
 */
@Api(tags = "基础数据操作接口")
public class BaseController<T, E extends IService<T>> {

    @Autowired
    private E service;

    @ApiOperation(value = "根据id获取数据", notes = "根据id获取数据")
    @GetMapping("/getById/{id}")
    public ResponseEntity<T> getById(@PathVariable("id") Long id) {
        return ResponseEntity.successData(service.getById(id));
    }

    @ApiOperation(value = "保存数据", notes = "保存数据")
    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody T entity) {
        boolean save = service.save(entity);
        if (!save) {
            return ResponseEntity.failure("保存失败");
        }
        return ResponseEntity.success();
    }

    @ApiOperation(value = "更新数据", notes = "根据ID更新数据")
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody T entity) {
        boolean update = service.updateById(entity);
        if (!update) {
            return ResponseEntity.failure("保存失败");
        }
        return ResponseEntity.success();
    }

    @ApiOperation(value = "删除数据", notes = "根据ID删除数据")
    @DeleteMapping("/delete/{id}")
    @SuppressWarnings("all")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        ResponseEntity<T> entity = this.getById(id);
        T data = entity.getData();
        if (data == null) {
            return ResponseEntity.failure("数据不存在");
        }
        Class<?> aClass = data.getClass();
        boolean remove;
        try {
            Field delFlag = aClass.getDeclaredField(BaseField.DELETE_FLAG.getFieldName());
            if(delFlag == null) {
                remove = service.removeById(id);
            }else {
                delFlag.setAccessible(true);
                delFlag.set(data, YesOrNoEnum.YES.getValue());
                remove = service.updateById(data);
            }
        } catch (Exception e) {
            return ResponseEntity.failure("删除失败，发生异常：" + e.getMessage());
        }
        if (!remove) {
            return ResponseEntity.failure("删除失败");
        }
        return ResponseEntity.success();
    }


}
