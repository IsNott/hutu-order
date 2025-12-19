package ${reqPackage};

<#if useSwagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if useValidation>
import javax.validation.constraints.*;
</#if>
import lombok.Data;
import java.io.Serializable;
import ${dtoPackage}.${entity}DTO;
import java.math.BigDecimal;
import java.util.*;

/**
* ${table.comment!} DTO
*/
@Data
<#if useSwagger>
@ApiModel(value = "${entity}Request", description = "${table.comment!}访问参数")
</#if>
public class ${entity}Request extends Request<${entity}DTO> {

<#list fields as field>
    <#if field.comment!?length gt 0>
        <#if useSwagger>
    @ApiModelProperty(value = "${field.comment}")
        <#else>
            /**
            * ${field.comment}
            */
        </#if>
    </#if>
    private ${field.propertyType} ${field.propertyName};

</#list>
}