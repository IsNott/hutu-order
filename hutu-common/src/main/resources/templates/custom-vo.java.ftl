package ${voPackage};

<#if useSwagger>
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
</#if>
<#if useValidation>
    import javax.validation.constraints.*;
</#if>
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
* ${table.comment!} VO
*/
@Data
<#if useSwagger>
@ApiModel(value = "${entity}Vo", description = "${table.comment!}页面对象")
</#if>
public class ${entity}Vo {

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