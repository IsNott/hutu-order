package org.nott.common.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.nott.common.deserializer.MultiDateDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Jackson 配置类，注册自定义的日期反序列化器和序列化器
 */
@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        // 注册自定义反序列化器
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Date.class, new MultiDateDeserializer());

        module.addSerializer(Date.class, new JsonSerializer<Date>() {
            private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            @Override
            public void serialize(Date value, JsonGenerator gen, SerializerProvider provider)
                    throws IOException {
                gen.writeString(sdf.format(value));
            }
        });

        objectMapper.registerModule(module);
        return objectMapper;
    }
}
