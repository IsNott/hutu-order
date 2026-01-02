package org.nott.common.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 支持多种日期格式的反序列化器
 */
public class MultiDateDeserializer extends JsonDeserializer<Date> {

    private static final List<String> FORMATS = new ArrayList<>();

    static {
        FORMATS.add("yyyy-MM-dd HH:mm:ss");
        FORMATS.add("yyyy-MM-dd");
        FORMATS.add("yyyy/MM/dd HH:mm:ss");
        FORMATS.add("yyyy/MM/dd");
        FORMATS.add("yyyy年MM月dd日 HH时mm分ss秒");
        FORMATS.add("yyyy年MM月dd日");
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        String dateStr = p.getText();
        if (!StringUtils.hasText(dateStr)) {
            return null;
        }

        // 尝试多种格式解析
        for (String format : FORMATS) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                sdf.setLenient(false);
                return sdf.parse(dateStr);
            } catch (ParseException e) {
                continue;
            }
        }
        throw new IllegalArgumentException(
                String.format("无法解析日期: %s, 支持的格式: %s", dateStr, FORMATS)
        );
    }
}