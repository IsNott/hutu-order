package org.nott.common.generate;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Nott
 * @date 2024-5-24
 */

public class MbpGenerator {

    public interface INFO {
        String URL = "localhost:3306";
        String DB = "hutu-order";
        String PASSWORD = "123456";
        String USERNAME = "root";
        String AUTHOR = "nott";
        String PACKAGE = "org.nott";
    }

    public static void main(String[] args) {

        FastAutoGenerator.create(String.format("jdbc:mysql://%s/%s?allowMultiQueries=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true",
                        INFO.URL, INFO.DB) + "&serverTimezone=GMT%2B8", INFO.USERNAME, INFO.PASSWORD)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(INFO.AUTHOR))
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(INFO.PACKAGE))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables("biz_play_image"))
                        .entityBuilder()
                        .enableLombok()
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT)
                        )
                        .build())
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
