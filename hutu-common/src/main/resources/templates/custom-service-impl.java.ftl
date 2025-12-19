package ${package.Service}.${typeName};

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import ${package.Entity}.${entity};
import ${package.Service}.mapper.${typeName}.${entity}Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import ${dtoPackage}.${entity}DTO;
import ${voPackage}.${entity}Vo;
import ${commonPackage}.utils.HutuUtils;

/**
* ${table.comment!} Service
*/
@Service
public class ${entity}Service extends ServiceImpl<${entity}Mapper, ${entity}>  {

    public IPage<${entity}Vo> queryPage(Integer page, Integer size, ${entity}DTO dto) {
        LambdaQueryWrapper<SysShopInfo> wrapper = new LambdaQueryWrapper<>();
        IPage<${entity}Vo> voPage = this.page(new Page<>(page, size), wrapper)
            .convert(item -> HutuUtils.transToObject(item, ${entity}Vo.class));
            return voPage;
    }
}