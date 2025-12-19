package org.nott.common.common;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nott
 * @date 2025-12-01
 */
public abstract class CommonController<VO, T, S extends ServiceImpl> {

    @Autowired
    private S service;

    public S getService() {
        return service;
    }

    @ApiOperation("通用详情接口")
    @PostMapping("/details/{id}")
    public ResponseEntity<VO> details(@PathVariable("id") Long id) {
        VO vo = HutuUtils.toVO(service.getById(id), this);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("通用更新接口")
    @PutMapping("/update/{id}")
    public ResponseEntity<VO> details(@PathVariable("id") Long id, @RequestBody T entity) {
        service.updateById(entity);
        return ResponseEntity.successData(HutuUtils.toVO(entity, this));
    }

    @ApiOperation("通用删除接口")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.success();
    }





}
