package ${package.Service}.${typeName};

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import ${package.Entity}.${entity};
import ${package.Service}.mapper.${typeName}.${entity}Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import ${dtoPackage}.${entity}DTO;
import ${voPackage}.${entity}Vo;
import ${commonPackage}.utils.HutuUtils;
import ${commonPackage}.exception.HutuBizException;
import javax.annotation.Resource;
/**
* ${table.comment!} Service
*/
@Service
public class ${entity}Service extends ServiceImpl<${entity}Mapper, ${entity}>  {

    @Resource
    private ${entity}Mapper ${entity?uncap_first}Mapper;

    public IPage<${entity}Vo> queryPage(Integer page, Integer size, ${entity}DTO dto) {
        MPJLambdaWrapper<${entity}> wrapper = new MPJLambdaWrapper<${entity}>()
            .selectAll(${entity}.class)
            .orderByDesc(${entity}::getCreateTime);
        return ${entity?uncap_first}Mapper.selectJoinPage(new Page<>(page, size), ${entity}Vo.class, wrapper);
    }


    public ${entity}Vo save(${entity}DTO dto) {
        ${entity} entity = HutuUtils.transToObject(dto, ${entity}.class);
        entity.setDelFlag(false);
        this.save(entity);
        return HutuUtils.transToObject(entity, ${entity}Vo.class);
    }

    public ${entity}Vo update(${entity}DTO dto) {
        Long id = dto.getId();
        ${entity} entity = this.getById(id);
        if(HutuUtils.isEmpty(entity)){
            throw new HutuBizException("Entity not found.");
        }
        HutuUtils.copyProperties(dto, entity);
        this.updateById(entity);
        return HutuUtils.transToObject(entity, ${entity}Vo.class);
    }
}