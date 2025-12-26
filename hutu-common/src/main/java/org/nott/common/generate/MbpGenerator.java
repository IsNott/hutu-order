package org.nott.common.generate;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.INameConvert;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.google.common.base.CaseFormat;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.nott.common.config.DbConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Nott
 * @date 2024-5-24
 */
@Component
public class MbpGenerator {

    @Resource
    private DbConfig dbConfig;

    @Getter
    enum TYPE {
        OSS,
        ADMIN,
        API;
        private String value;

        TYPE() {
            this.value = this.name().toLowerCase();
        }
    }

    public interface INFO {
        String URL = "localhost:3306";
        String DB = "hutu-order";
        String PASSWORD = "888888";
        String USERNAME = "root";
        String AUTHOR = "nott";
        String PACKAGE = "org.nott";
    }

    public static void main(String[] args) {
        List<String> tables = Arrays.asList("sys_menu", "sys_role_menu", "sys_user_role");
        doGenerateBackend(TYPE.ADMIN.value, tables);
//        doGenerateFrontend(tables);
    }

    public static void doGenerateFrontend( List<String> tables) {
        for (String table : tables) {
            System.out.println("Generating for table: " + table);
            generateFrontend(table);
        }
    }

    public static void doGenerateBackend(@NotNull String type, @NotNull List<String> tables) {
        for (String table : tables) {
            System.out.println("Generating for table: " + table);
            generateBackend(type, table);
        }
    }


    public static void generateBackend(String type, String table) {
        final String entityName = formatTableNameToEntityName(table, type);

        String dir = System.getProperty("user.dir");
        System.out.println("Project dir: " + dir);

        FastAutoGenerator.create(String.format("jdbc:mysql://%s/%s?allowMultiQueries=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true",
                        INFO.URL, INFO.DB) + "&serverTimezone=GMT%2B8", INFO.USERNAME, INFO.PASSWORD)
                // 全局配置
                .globalConfig((scanner, builder) ->
                        builder.author(INFO.AUTHOR)
                                .dateType(DateType.ONLY_DATE)
                                .commentDate("yyyy")
                                .disableOpenDir())
                // 包配置
                .packageConfig((scanner, builder) -> {
                    Map<OutputFile, String> pathInfo = new HashMap<>();
                    pathInfo.put(OutputFile.mapper, dir + "/hutu-service/src/main/java/org/nott/service/mapper/" + type + "/");
                    pathInfo.put(OutputFile.serviceImpl, dir + "/hutu-service/src/main/java/org/nott/service/" + type + "/");
                    if ("api".equals(type)) {
                        pathInfo.put(OutputFile.controller, dir + "/hutu-app/" + type + "/src/main/java/org/nott/web/controller");
                    } else {
                        pathInfo.put(OutputFile.controller, dir + "/hutu-app/" + type + "/src/main/java/org/nott/" + type + "/controller");
                    }
                    pathInfo.put(OutputFile.xml, dir + "/hutu-service/src/main/resources/mapper/");
                    pathInfo.put(OutputFile.entity, dir + "/hutu-model/src/main/java/org/nott/model/");
                    pathInfo.put(OutputFile.other, dir + "/hutu-model/src/main/java/org/nott/");
                    builder.parent(INFO.PACKAGE)
                            .entity("model")
                            .mapper("service.mapper." + type)
                            .serviceImpl("service." + type)
                            .controller("api".equals(type) ? "web.controller" : type + ".controller")
                            .pathInfo(pathInfo);
                })
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(table))
                        .serviceBuilder().formatServiceImplFileName("%sService").formatServiceFileName("")
                        .entityBuilder().formatFileName(entityName)
                        .nameConvert(new INameConvert() {
                            @Override
                            public String entityNameConvert(TableInfo tableInfo) {
                                return entityName;
                            }

                            @Override
                            public String propertyNameConvert(TableField field) {
                                // 字段名下划线转小驼峰
                                return NamingStrategy.underlineToCamel(field.getName());
                            }
                        })
                        .enableLombok()
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE)
                        )
                        .logicDeleteColumnName("del_flag")
                        .logicDeletePropertyName("delFlag")
                        .mapperBuilder().formatMapperFileName(entityName + "Mapper").formatXmlFileName(entityName + "Mapper")
                        .controllerBuilder().enableRestStyle().formatFileName(entityName + "Controller")
                        .build())
                .injectionConfig(builder -> {
                    // 自定义配置
                    Map<String, Object> customMap = new HashMap<>();
                    customMap.put("commonPackage", "org.nott.common");
                    customMap.put("dtoPackage", "org.nott.dto");
                    customMap.put("voPackage", "org.nott.vo");
                    customMap.put("reqPackage", "org.nott.request");
                    customMap.put("useSwagger", true);
                    customMap.put("useValidation", false);
                    customMap.put("typeName", type);
                    builder.customMap(customMap);

                    Map<String, String> customFile = new HashMap<>();
                    customFile.put(entityName + "DTO.java", "/templates/custom-dto.java.ftl");
                    customFile.put(entityName + "Vo.java", "/templates/custom-vo.java.ftl");
                    customFile.put(entityName + "Request.java", "/templates/custom-req.java.ftl");

                    builder.customFile(customFile);

                    builder.beforeOutputFile((tableInfo, objectMap) -> {
                        objectMap.put("entity", entityName);
                        // 添加表注释到对象映射
                        objectMap.put("tableComment", tableInfo.getComment());
                        objectMap.put("fields", tableInfo.getFields());
                        objectMap.put("dtoPath", dir + "/src/main/java/org/nott/dto");
                        objectMap.put("voPath", dir + "/src/main/java/org/nott/vo");
                        objectMap.put("requestPath", dir + "/src/main/java/org/nott/request");
                    });

                })
                .templateConfig(builder -> {
                    // 使用自定义模板
                    builder.controller("/templates/custom-controller.java");
                    builder.serviceImpl("/templates/custom-service-impl.java");
                    builder.mapper("/templates/custom-mapper.java");
                    builder.disable(TemplateType.SERVICE);
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    public static void generateFrontend(String table) {
        final String entityName = formatTableNameToEntityName(table, TYPE.ADMIN.value);

        String dir = System.getProperty("user.dir");
        dir = dir.replaceAll("hutu-order", "hutu-order-admin");
        System.out.println("Project dir: " + dir);
        String finalDir = dir;
        FastAutoGenerator.create(String.format("jdbc:mysql://%s/%s?allowMultiQueries=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true",
                        INFO.URL, INFO.DB) + "&serverTimezone=GMT%2B8", INFO.USERNAME, INFO.PASSWORD)
                // 全局配置
                .globalConfig((scanner, builder) ->
                        builder.author(INFO.AUTHOR)
                                .dateType(DateType.ONLY_DATE)
                                .commentDate("yyyy"))
                // 包配置
                .packageConfig((scanner, builder) -> {
                    Map<OutputFile, String> pathInfo = new HashMap<>();
                    pathInfo.put(OutputFile.other, finalDir + "/auto-generated");
                    builder.parent(INFO.PACKAGE)
                            .entity(null)
                            .mapper(null)
                            .serviceImpl(null)
                            .controller(null)
                            .service(null)
                            .pathInfo(pathInfo);
                })
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(table)))
                // 策略配置
                .injectionConfig(builder -> {
                    // 自定义配置
                    Map<String, Object> customMap = new HashMap<>();
                    customMap.put("commonPackage", "org.nott.common");
                    customMap.put("dtoPackage", "org.nott.dto");
                    customMap.put("featureName", getFeatureName(entityName));


                    builder.customMap(customMap);

                    Map<String, String> customFile = new HashMap<>();
                    customFile.put("index.vue", "/templates/vue/index.vue.ftl");
                    customFile.put("components/EditForm.vue", "/templates/vue/edit-form.vue.ftl");
                    customFile.put("api/index.js", "/templates/vue/api.js.ftl");

                    builder.customFile(customFile);

                    builder.beforeOutputFile((tableInfo, objectMap) -> {
                        objectMap.put("entity", entityName);
                        // 添加表注释到对象映射
                        objectMap.put("tableComment", tableInfo.getComment());
                        objectMap.put("fields", tableInfo.getFields());
                        objectMap.put("featureZhName", getFeatureZhName(tableInfo.getComment()));
                    });
                })
                .templateConfig(builder -> {
                    // 只生成other
                    builder.disable(TemplateType.CONTROLLER);
                    builder.disable(TemplateType.MAPPER);
                    builder.disable(TemplateType.ENTITY);
                    builder.disable(TemplateType.SERVICE);
                    builder.disable(TemplateType.SERVICEIMPL);
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }

    private static String getFeatureName(String entityName) {
        // 去掉前缀 首字母小写
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, entityName.replaceFirst("Sys", "").replaceFirst("Api", ""));
    }

    private static String getFeatureZhName(String comment) {
        // 去掉表两个字
        if(StringUtils.isEmpty(comment)) {
            return " ";
        }
        return comment.replaceAll("表", "");
    }

    private static String formatTableNameToEntityName(String table, String type) {
        if (table.startsWith("biz_") && "admin".equals(type)) {
            String name = table.replaceFirst("biz_", "sys_");
            return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
        }
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table);
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
