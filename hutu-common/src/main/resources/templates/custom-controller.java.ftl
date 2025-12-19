package ${package.Controller};

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import ${commonPackage}.ResponseEntity;
import ${commonPackage}.utils.HutuUtils;
import ${reqPackage}.${entity}Request;
import ${voPackage}.${entity}Vo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import ${package.Entity}.${entity};
import ${package.Service}.${typeName}.${entity}Service;
import javax.annotation.Resource;
<#-- 注释 -->
/**
* ${table.comment!}前端控制器
*
* @author ${author}
* @date ${date}
* @version 1.0
* @description auto generated
*/
<#if useSwagger>
@Api(value = "${table.comment!}<#if typeName == "admin">管理</#if>接口", tags = "${table.comment!}<#if typeName == "admin">管理</#if>")
</#if>
@RestController
@RequestMapping("/${entity?uncap_first}")
<#if commonControllerPath??>
    public class ${table.controllerName} extends ${superControllerClass}<${entity}Vo, ${entity}, ${entity}Service> {
<#else>
    public class ${table.controllerName} {
</#if>

    @Resource
    private ${entity}Service service;

    @ApiOperation("分页查询")
    @PostMapping("/page/{page}/{size}")
    public ResponseEntity<IPage<${entity}Vo>> page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody ${entity}Request req) {
        IPage<${entity}Vo> result = service.queryPage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("详情")
    @PostMapping("/details/{id}")
    public ResponseEntity<${entity}Vo> details(@PathVariable("id") Long id) {
        ${entity}Vo vo = HutuUtils.toVO(service.getById(id), ${entity}Vo.class);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("更新")
    @PutMapping("/update/{id}")
    public ResponseEntity<${entity}Vo> details(@PathVariable("id") Long id, @RequestBody ${entity} entity) {
        service.updateById(entity);
        return ResponseEntity.successData(HutuUtils.toVO(entity, ${entity}Vo.class));
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.success();
    }

}